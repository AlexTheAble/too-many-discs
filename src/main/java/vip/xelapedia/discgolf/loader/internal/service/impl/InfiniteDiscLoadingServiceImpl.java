package vip.xelapedia.discgolf.loader.internal.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vip.xelapedia.discgolf.loader.dto.events.CatalogLoadEvent;
import vip.xelapedia.discgolf.loader.internal.clients.InfiniteDiscsClientProxy;
import vip.xelapedia.discgolf.loader.internal.clients.contract.InfiniteDiscsNavData;
import vip.xelapedia.discgolf.loader.internal.entity.Source;
import vip.xelapedia.discgolf.loader.internal.entity.SourcePage;
import vip.xelapedia.discgolf.loader.internal.enums.InfiniteDiscsPageType;
import vip.xelapedia.discgolf.loader.internal.eventing.LoaderEventPublisher;
import vip.xelapedia.discgolf.loader.internal.repository.PageRepository;
import vip.xelapedia.discgolf.loader.internal.repository.SourceRepository;
import vip.xelapedia.discgolf.loader.internal.service.LoadingService;
import vip.xelapedia.discgolf.loader.internal.service.SourceService;
import vip.xelapedia.discgolf.loader.internal.util.HtmlParserUtil;
import vip.xelapedia.discgolf.loader.internal.util.KeyGenerator;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class InfiniteDiscLoadingServiceImpl implements LoadingService {

    //TODO Bug where flight numbers are not consistently being captured
    //TODO get dimension data
    private final InfiniteDiscsClientProxy infiniteDiscsClientProxy;
    private final SourceRepository sourceRepository;
    private final PageRepository pageRepository;
    private final SourceService sourceService;
    private final LoaderEventPublisher eventPublisher;

    private static final String SOURCE_KEY = "INFINITEDISCS";
    private static final int PAGE_SIZE = 10;

    @Override
    public void preformLoad(final boolean buildPages) {
        log.debug(">> InfiniteDiscLoadingServiceImpl.preformLoad");
        final Source source = sourceRepository.findByKey(SOURCE_KEY);
        if (buildPages) {
            log.debug("Loading pages for source: {}...", source.getName());
            sourceService.clearPageDataForSource(source);
            buildAndPersistPages(source);
            log.debug("Pages loaded for source: {}", source.getName());
        }
        log.debug("Creating events for source: {}...", source.getName());
        buildAndSendCatalogEvents(source);
        log.debug("Events created for source: {}...", source.getName());
        log.debug("<< InfiniteDiscLoadingServiceImpl.preformLoad");
    }

    private void buildAndPersistPages(final Source source) {
        final List<InfiniteDiscsNavData.Brand> brands = infiniteDiscsClientProxy.getBrands();
        brands.stream()
                .map(brand -> buildMoldPagesForBrand(source, brand.link()))
                .flatMap(List::stream)
                .forEach(sourceService::persistPage);
    }

    private void buildAndSendCatalogEvents(final Source source) {
        Page<SourcePage> page = pageRepository.findAllBySourceAndType(source, InfiniteDiscsPageType.MOLD.name(), Pageable.ofSize(PAGE_SIZE).withPage(0));
        page.stream().parallel()
                .map(SourcePage::getHtml)
                .map(HtmlParserUtil::parseHtmlString)
                .forEach(this::sendCatalogLoadEvent);

        while (page.hasNext()) {
            page = pageRepository.findAllBySourceAndType(source, InfiniteDiscsPageType.MOLD.name(), page.nextPageable());
            page.stream().parallel()
                    .map(ele -> HtmlParserUtil.fetchDocument(source.getBaseUrl() + ele.getPath()))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .forEach(this::sendCatalogLoadEvent);
        }
    }

    private void sendCatalogLoadEvent(final Document document) {
        final String manufactureName = extractBrandName(document);
        final CatalogLoadEvent.ManufacturerLoad manufacturerLoad = new CatalogLoadEvent.ManufacturerLoad(KeyGenerator.generateKey(manufactureName), manufactureName);

        final CatalogLoadEvent.MoldLoad moldLoad = buildMoldLoadEvent(document);

        extractPlastics(document).stream()
                .map(plasticName -> new CatalogLoadEvent.PlasticLoad(KeyGenerator.generateKey(plasticName), plasticName))
                .map(plastic -> new CatalogLoadEvent(manufacturerLoad.key() + moldLoad.key() + plastic.key(), plastic, manufacturerLoad, moldLoad))
                .forEach(eventPublisher::publishCatalogLoadEvent);
    }

    private CatalogLoadEvent.MoldLoad buildMoldLoadEvent(final Document document) {
        final String name = extractName(document);
        final List<Double> flightNumbers = extractFlightNumbers(document);

        final CatalogLoadEvent.MoldLoad.MoldLoadBuilder builder = CatalogLoadEvent.MoldLoad.builder()
                .key(KeyGenerator.generateKey(name))
                .name(name);
        if (Objects.nonNull(flightNumbers)) {
            builder.speed(flightNumbers.get(0))
                    .glide(flightNumbers.get(1))
                    .turn(flightNumbers.get(2))
                    .fade(flightNumbers.get(3));
        }
        return builder.build();
    }

    private static List<SourcePage> buildMoldPagesForBrand(final Source source, final String brandPageLink) {
        final Optional<Document> optDoc = HtmlParserUtil.fetchDocument(source.getBaseUrl() + brandPageLink);
        return optDoc.map(elements -> elements
                        .select("h4")
                        .select("a")
                        .stream()
                        .map(ele -> ele.attribute("href"))
                        .filter(Objects::nonNull)
                        .map(Attribute::getValue)
                        .map(link -> buildBuildPage(source, link))
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .toList())
                .orElse(Collections.emptyList());
    }

    private static Optional<SourcePage> buildBuildPage(final Source source, final String link) {
        final Optional<Document> html = HtmlParserUtil.fetchDocument(source.getBaseUrl() + link);
        return html.map(document -> SourcePage.builder()
                .path(link)
                .type(InfiniteDiscsPageType.MOLD.name())
                .isActive(true)
                .lastVisitedDT(LocalDateTime.now())
                .source(source)
                .html(document.html())
                .build());
    }

    private static List<String> extractPlastics(final Document document) {
        return document.select("#rev_plastic_type")
                .select("option")
                .stream()
                .filter(element ->
                        Objects.nonNull(element.attribute("value")) && Strings.isNotBlank(element.attribute("value").getValue())
                )
                .map(Element::text)
                .toList();
    }

    private static String extractName(final Document document) {
        return document.select(".product-title > h1").text();
    }

    private static List<Double> extractFlightNumbers(final Document document) {

        final List<Double> flightNumbers = Arrays.stream(document.select("div:nth-child(1) > h5 > strong").text().split("/"))
                .filter(StringUtils::isNumeric)
                .map(Double::parseDouble)
                .toList();

        if (flightNumbers.size() != 4) {
            return null;
        }
        return flightNumbers;
    }

    private static String extractBrandName(final Document document) {
        return document.select("div.jumbo-display > div > div > div > div > ul > li:nth-child(2) > a").text();
    }
}

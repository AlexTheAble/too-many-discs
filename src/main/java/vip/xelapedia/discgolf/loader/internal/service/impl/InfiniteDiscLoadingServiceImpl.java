package vip.xelapedia.discgolf.loader.internal.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import vip.xelapedia.discgolf.discs.dto.request.ManufacturerUpsert;
import vip.xelapedia.discgolf.loader.dto.events.ManufacturerUpsertEvent;
import vip.xelapedia.discgolf.loader.internal.clients.InfiniteDiscsClientProxy;
import vip.xelapedia.discgolf.loader.internal.clients.contract.InfiniteDiscsNavData;
import vip.xelapedia.discgolf.loader.internal.entity.Source;
import vip.xelapedia.discgolf.loader.internal.repository.PageRepository;
import vip.xelapedia.discgolf.loader.internal.repository.SourceRepository;
import vip.xelapedia.discgolf.loader.internal.service.LoadingService;
import vip.xelapedia.discgolf.loader.internal.util.KeyGenerator;

import java.util.List;

@Service
@AllArgsConstructor
public class InfiniteDiscLoadingServiceImpl implements LoadingService {
    private final InfiniteDiscsClientProxy infiniteDiscsClientProxy;
    private final SourceRepository sourceRepository;
    private final PageRepository pageRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    private static final String SOURCE_KEY = "INFINITEDISCS";

    @Override
    @Transactional
    public void preformLoad(final boolean buildPages) {
        final Source source = sourceRepository.findByKey(SOURCE_KEY);
        final List<InfiniteDiscsNavData.Brand> brands = infiniteDiscsClientProxy.getBrands();
        publishManufacturerUpsertEvents(brands.parallelStream().map(InfiniteDiscsNavData.Brand::title).toList());
        if (buildPages) {

        }

    }

    private void publishManufacturerUpsertEvents(final List<String> brands) {
        brands.parallelStream()
                .map(brand -> new ManufacturerUpsertEvent(KeyGenerator.generateKey(brand), brand))
                .forEach(applicationEventPublisher::publishEvent);
    }
}

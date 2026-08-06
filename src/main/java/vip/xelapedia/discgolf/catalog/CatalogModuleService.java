package vip.xelapedia.discgolf.catalog;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vip.xelapedia.discgolf.catalog.dto.request.DiscUpsert;
import vip.xelapedia.discgolf.catalog.dto.request.ManufacturerUpsert;
import vip.xelapedia.discgolf.catalog.dto.request.MoldUpsert;
import vip.xelapedia.discgolf.catalog.dto.request.PlasticUpsert;
import vip.xelapedia.discgolf.catalog.dto.response.DiscResponse;
import vip.xelapedia.discgolf.catalog.dto.response.ManufacturerResponse;
import vip.xelapedia.discgolf.catalog.dto.response.MoldResponse;
import vip.xelapedia.discgolf.catalog.dto.response.PlasticResponse;
import vip.xelapedia.discgolf.catalog.internal.entity.CatalogDisc;
import vip.xelapedia.discgolf.catalog.internal.entity.Manufacturer;
import vip.xelapedia.discgolf.catalog.internal.entity.Mold;
import vip.xelapedia.discgolf.catalog.internal.entity.Plastic;
import vip.xelapedia.discgolf.catalog.internal.service.DiscService;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
@Slf4j
public class CatalogModuleService {
    private DiscService discService;

    public List<DiscResponse> getDiscsByIds(final Set<UUID> discIds) {
        log.debug(">> DiscsModuleService.getDiscsByIds");
        final Iterable<CatalogDisc> iterableDiscs = discService.getDiscs(discIds);
        final List<DiscResponse> discs =
                StreamSupport.stream(iterableDiscs.spliterator(), true).map(DiscResponse::new).toList();
        log.debug("<< DiscsModuleService.getDiscsByIds");
        return discs;
    }

    public List<PlasticResponse> getPlasticsByIds(final Set<UUID> plasticIds) {
        log.debug(">> DiscsModuleService.getPlasticsByIds");
        final Iterable<Plastic> iterablePlastics = discService.getPlastics(plasticIds);
        final List<PlasticResponse> plastics =
                StreamSupport.stream(iterablePlastics.spliterator(), true).map(PlasticResponse::new).toList();
        log.debug("<< DiscsModuleService.getPlasticsByIds");
        return plastics;
    }

    public List<ManufacturerResponse> getManufacturesByIds(final Set<UUID> manufacturerIds) {
        log.debug(">> DiscsModuleService.getManufacturesByIds");
        final Iterable<Manufacturer> iterableManufacturers = discService.getManufacturers(manufacturerIds);
        final List<ManufacturerResponse> manufacturers =
                StreamSupport.stream(iterableManufacturers.spliterator(), true).map(ManufacturerResponse::new).toList();
        log.debug("<< DiscsModuleService.getManufacturesByIds");
        return manufacturers;
    }

    public List<MoldResponse> getMoldsByIds(final Set<UUID> moldIds) {
        log.debug(">> DiscsModuleService.getMoldsByIds");
        final Iterable<Mold> iterableMods = discService.getMolds(moldIds);
        final List<MoldResponse> molds =
                StreamSupport.stream(iterableMods.spliterator(), true).map(MoldResponse::new).toList();
        log.debug("<< DiscsModuleService.getMoldsByIds");
        return molds;
    }

    public DiscResponse upsertDisc(final DiscUpsert discUpsert) {
        log.debug(">> DiscsModuleService.upsertDisc key: {}", discUpsert.key());
        final CatalogDisc disc = discService.persistDisc(discUpsert.toEntity());
        log.debug("<< DiscsModuleService.upsertDisc key: {}", discUpsert.key());
        return new DiscResponse(disc);
    }

    public PlasticResponse upsertPlastic(final PlasticUpsert plasticUpsert) {
        log.debug(">> DiscsModuleService.upsertPlastic key: {}", plasticUpsert.key());
        final Plastic plastic = discService.persistPlastic(plasticUpsert.toEntity());
        log.debug("<< DiscsModuleService.upsertPlastic key: {}", plasticUpsert.key());
        return new PlasticResponse(plastic);
    }

    public ManufacturerResponse upsertManufacturer(final ManufacturerUpsert manufacturerUpsert) {
        log.debug(">> DiscsModuleService.upsertManufacturer key: {}", manufacturerUpsert.key());
        final Manufacturer manufacturer = discService.persistManufacturer(manufacturerUpsert.toEntity());
        log.debug("<< DiscsModuleService.upsertManufacturer key: {}", manufacturerUpsert.key());
        return new ManufacturerResponse(manufacturer);
    }

    public MoldResponse moldUpsert(final MoldUpsert moldUpsert) {
        log.debug(">> DiscsModuleService.moldUpsert key: {}", moldUpsert.key());
        final Mold mold = discService.persistMold(moldUpsert.toEntity());
        log.debug("<< DiscsModuleService.moldUpsert key: {}", moldUpsert.key());
        return new MoldResponse(mold);
    }

    public void clearAllData() {
        discService.clearAllData();
    }
}

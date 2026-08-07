package vip.xelapedia.discgolf.catalog.internal.eventing;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;
import vip.xelapedia.discgolf.catalog.internal.entity.CatalogDisc;
import vip.xelapedia.discgolf.catalog.internal.entity.Manufacturer;
import vip.xelapedia.discgolf.catalog.internal.entity.Mold;
import vip.xelapedia.discgolf.catalog.internal.entity.Plastic;
import vip.xelapedia.discgolf.catalog.internal.service.CatalogDiscService;
import vip.xelapedia.discgolf.loader.dto.events.CatalogLoadEvent;

import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class LoaderEventListener {
    private CatalogDiscService discService;

    @TransactionalEventListener
    @Async
    public void handleCatalogLoadEvent(final CatalogLoadEvent event) {
        log.debug(">> LoaderEventListener.handleCatalogLoadEvent key: {}", event.key());
        final Manufacturer manufacturer = upsertManufacturer(event.manufacturer());
        final Plastic plastic = upsertPlastic(event.plastic());
        final Mold mold = upsertMold(event.mold());

        final Optional<CatalogDisc> optionalCatalogDisc = discService.getDiscByKey(event.key());
        final CatalogDisc catalogDisc;
        if (optionalCatalogDisc.isEmpty()) {
            catalogDisc = CatalogDisc.builder()
                    .key(event.key())
                    .plastic(plastic)
                    .mold(mold)
                    .manufacturer(manufacturer)
                    .build();

        } else {
            catalogDisc = optionalCatalogDisc.get();
            catalogDisc.setPlastic(plastic);
            catalogDisc.setMold(mold);
            catalogDisc.setManufacturer(manufacturer);
        }

        discService.persistDisc(catalogDisc);
        log.debug("<< LoaderEventListener.handleCatalogLoadEvent key: {}", event.key());
    }

    private Manufacturer upsertManufacturer(final CatalogLoadEvent.ManufacturerLoad manufacturerLoad) {
        final Optional<Manufacturer> manufacturerOptional = discService.getManufacturerByKey(manufacturerLoad.key());

        final Manufacturer manufacturer;
        if (manufacturerOptional.isEmpty()) {
            manufacturer = Manufacturer.builder()
                    .key(manufacturerLoad.key())
                    .name(manufacturerLoad.name())
                    .build();
        } else {
            manufacturer = manufacturerOptional.get();
            manufacturer.setName(manufacturerLoad.name());
        }

        try {
            return discService.persistManufacturer(manufacturer);
        } catch (final DataIntegrityViolationException e) {
            final Optional<Manufacturer> optionalManufacturer = discService.getManufacturerByKey(manufacturer.getKey());
            if (optionalManufacturer.isPresent()) {
                return optionalManufacturer.get();
            }
            throw e;
        }
    }

    private Plastic upsertPlastic(final CatalogLoadEvent.PlasticLoad plasticLoad) {
        final Optional<Plastic> plasticOptional = discService.getPlasticByKey(plasticLoad.key());

        final Plastic plastic;
        if (plasticOptional.isEmpty()) {
            plastic = Plastic.builder()
                    .key(plasticLoad.key())
                    .name(plasticLoad.name())
                    .build();
        } else {
            plastic = plasticOptional.get();
            plastic.setName(plasticLoad.name());
        }

        try {
            return discService.persistPlastic(plastic);
        } catch (final DataIntegrityViolationException e) {
            final Optional<Plastic> optionalPlastic = discService.getPlasticByKey(plasticLoad.key());
            if (optionalPlastic.isPresent()) {
                return optionalPlastic.get();
            }
            throw e;
        }
    }


    private Mold upsertMold(final CatalogLoadEvent.MoldLoad moldLoad) {
        final Optional<Mold> optionalMold = discService.getMoldByKey(moldLoad.key());

        final Mold mold;
        if (optionalMold.isEmpty()) {
            mold = Mold.builder()
                    .key(moldLoad.key())
                    .name(moldLoad.name())
                    .rimWidth(moldLoad.rimWidth())
                    .rimDepth(moldLoad.rimDepth())
                    .height(moldLoad.height())
                    .diameter(moldLoad.height())
                    .fade(moldLoad.fade())
                    .glide(moldLoad.glide())
                    .speed(moldLoad.speed())
                    .turn(moldLoad.turn())
                    .build();
        } else {
            mold = optionalMold.get();
            mold.setName(moldLoad.name());
            mold.setRimWidth(moldLoad.rimWidth());
            mold.setRimDepth(moldLoad.rimDepth());
            mold.setDiameter(moldLoad.diameter());
            mold.setHeight(moldLoad.height());
            mold.setFade(moldLoad.fade());
            mold.setGlide(moldLoad.glide());
            mold.setSpeed(moldLoad.speed());
            mold.setTurn(moldLoad.turn());
        }

        try {
            return discService.persistMold(mold);
        } catch (final DataIntegrityViolationException e) {
            final Optional<Mold> optMold = discService.getMoldByKey(moldLoad.key());
            if (optMold.isPresent()) {
                return optMold.get();
            }
            throw e;
        }
    }
}

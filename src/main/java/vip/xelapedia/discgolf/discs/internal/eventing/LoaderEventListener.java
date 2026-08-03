package vip.xelapedia.discgolf.discs.internal.eventing;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Strings;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;
import vip.xelapedia.discgolf.discs.internal.entity.Manufacturer;
import vip.xelapedia.discgolf.discs.internal.service.DiscService;
import vip.xelapedia.discgolf.loader.dto.events.ManufacturerUpsertEvent;

import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class LoaderEventListener {
    private DiscService discService;

    @TransactionalEventListener
    @Async
    public void handleManufacturerUpsertEvent(final ManufacturerUpsertEvent event) {
        log.debug(">> LoaderEventListener.handleManufacturerUpsertEvent key: {}", event.key());
        final Optional<Manufacturer> manufacturerOptional = discService.getManufactuerersByKey(event.key());

        if (manufacturerOptional.isEmpty()) {
            discService.persistManufacturer(Manufacturer.builder()
                    .key(event.key())
                    .name(event.name())
                    .build());
        } else {
            final Manufacturer manufacturer = manufacturerOptional.get();
            if (Strings.CS.equals(event.name(), manufacturer.getName())) {
                manufacturer.setName(event.name());
                discService.persistManufacturer(manufacturer);
            }
        }
        log.debug("<< LoaderEventListener.handleManufacturerUpsertEvent key: {}", event.key());
    }
}

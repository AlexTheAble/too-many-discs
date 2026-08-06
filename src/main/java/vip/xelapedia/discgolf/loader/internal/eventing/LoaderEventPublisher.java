package vip.xelapedia.discgolf.loader.internal.eventing;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import vip.xelapedia.discgolf.loader.dto.events.CatalogLoadEvent;

import java.util.Objects;

@Component
@AllArgsConstructor
public class LoaderEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    public void publishCatalogLoadEvent(final CatalogLoadEvent event) {
        publishEvent(event);
    }

    private void publishEvent(final Object event) {
        if (Objects.nonNull(event)) {
            applicationEventPublisher.publishEvent(event);
        }
    }

}

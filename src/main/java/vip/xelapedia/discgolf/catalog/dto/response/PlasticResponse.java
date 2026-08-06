package vip.xelapedia.discgolf.catalog.dto.response;

import vip.xelapedia.discgolf.catalog.internal.entity.Plastic;

import java.time.LocalDateTime;
import java.util.UUID;

public record PlasticResponse(UUID id,
                              String name,
                              LocalDateTime createdDT,
                              LocalDateTime modifiedDT) {

    public PlasticResponse(final Plastic entity) {
        this(
                entity.getId(),
                entity.getName(),
                entity.getCreatedDT(),
                entity.getModifiedDT()
        );
    }
}

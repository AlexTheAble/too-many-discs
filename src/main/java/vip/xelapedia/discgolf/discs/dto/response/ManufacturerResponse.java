package vip.xelapedia.discgolf.discs.dto.response;

import vip.xelapedia.discgolf.discs.internal.entity.Manufacturer;

import java.time.LocalDateTime;
import java.util.UUID;

public record ManufacturerResponse(UUID id,
                                   String name,
                                   LocalDateTime createdDT,
                                   LocalDateTime modifiedDT) {

    public ManufacturerResponse(final Manufacturer entity) {
        this(
                entity.getId(),
                entity.getName(),
                entity.getCreatedDT(),
                entity.getModifiedDT()
        );
    }
}

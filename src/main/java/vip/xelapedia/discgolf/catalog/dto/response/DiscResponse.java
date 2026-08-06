package vip.xelapedia.discgolf.catalog.dto.response;

import vip.xelapedia.discgolf.catalog.internal.entity.CatalogDisc;

import java.time.LocalDateTime;
import java.util.UUID;

public record DiscResponse(UUID id,
                           String key,
                           PlasticResponse plastic,
                           ManufacturerResponse manufacturer,
                           MoldResponse mold,
                           LocalDateTime createdDT,
                           LocalDateTime modifiedDT) {

    public DiscResponse(CatalogDisc entity) {
        this(
                entity.getId(),
                entity.getKey(),
                new PlasticResponse(entity.getPlastic()),
                new ManufacturerResponse(entity.getManufacturer()),
                new MoldResponse(entity.getMold()),
                entity.getCreatedDT(),
                entity.getModifiedDT()
        );
    }
}

package vip.xelapedia.discgolf.catalog.dto.request;

import lombok.Builder;
import vip.xelapedia.discgolf.catalog.internal.entity.Manufacturer;

import java.util.UUID;

@Builder
public record ManufacturerUpsert(UUID id,
                                 String key,
                                 String name) {

    public Manufacturer toEntity() {
        return Manufacturer.builder()
                .id(this.id)
                .key(this.key)
                .name(this.name)
                .build();
    }
}

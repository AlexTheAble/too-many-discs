package vip.xelapedia.discgolf.catalog.dto.request;

import lombok.Builder;
import vip.xelapedia.discgolf.catalog.internal.entity.CatalogDisc;
import vip.xelapedia.discgolf.catalog.internal.entity.Manufacturer;
import vip.xelapedia.discgolf.catalog.internal.entity.Plastic;

import java.util.UUID;

@Builder
public record DiscUpsert(UUID id,
                         String key,
                         UUID plasticId,
                         UUID manufacturerId,
                         UUID moldId) {

    public CatalogDisc toEntity() {
        return CatalogDisc.builder()
                .id(this.id)
                .key(this.key)
                .plastic(Plastic.builder().id(this.plasticId).build())
                .manufacturer(Manufacturer.builder().id(this.manufacturerId).build())
                .build();
    }
}

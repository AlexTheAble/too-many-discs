package vip.xelapedia.discgolf.discs.dto.request;

import lombok.Builder;
import vip.xelapedia.discgolf.discs.internal.entity.Disc;
import vip.xelapedia.discgolf.discs.internal.entity.Manufacturer;
import vip.xelapedia.discgolf.discs.internal.entity.Plastic;

import java.util.UUID;

@Builder
public record DiscUpsert(UUID id,
                         String key,
                         double weight,
                         UUID plasticId,
                         UUID manufacturerId,
                         UUID moldId) {

    public Disc toEntity() {
        return Disc.builder()
                .id(this.id)
                .key(this.key)
                .weight(this.weight)
                .plastic(Plastic.builder().id(this.plasticId).build())
                .manufacturer(Manufacturer.builder().id(this.manufacturerId).build())
                .build();
    }
}

package vip.xelapedia.discgolf.discs.dto.request;

import lombok.Builder;
import vip.xelapedia.discgolf.discs.internal.entity.Plastic;

import java.util.UUID;

@Builder
public record PlasticUpsert(UUID id,
                            String key,
                            String name) {

    public Plastic toEntity() {
        return Plastic.builder()
                .id(this.id)
                .key(this.key)
                .name(this.name)
                .build();
    }
}

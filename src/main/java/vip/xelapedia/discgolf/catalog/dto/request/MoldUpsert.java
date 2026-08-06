package vip.xelapedia.discgolf.catalog.dto.request;

import lombok.Builder;
import vip.xelapedia.discgolf.catalog.internal.entity.Mold;

import java.util.UUID;

@Builder
public record MoldUpsert(UUID id,
                         String key,
                         String name,
                         int speed,
                         int glide,
                         int turn,
                         int fade,
                         double diameter,
                         double height,
                         double rimDepth,
                         double rimWidth) {

    public Mold toEntity() {
        return Mold.builder()
                .id(this.id)
                .key(this.key)
                .name(this.name)
                .speed(this.speed)
                .glide(this.glide)
                .turn(this.turn)
                .fade(this.fade)
                .diameter(this.diameter)
                .height(this.height)
                .rimDepth(this.rimDepth)
                .rimWidth(this.rimWidth)
                .build();
    }
}

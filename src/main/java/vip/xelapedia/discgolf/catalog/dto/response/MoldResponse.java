package vip.xelapedia.discgolf.catalog.dto.response;

import vip.xelapedia.discgolf.catalog.internal.entity.Mold;

import java.time.LocalDateTime;
import java.util.UUID;

public record MoldResponse(UUID id,
                           String key,
                           String name,
                           double speed,
                           double glide,
                           double turn,
                           double fade,
                           double diameter,
                           double height,
                           double rimDepth,
                           double rimWidth,
                           LocalDateTime createdDT,
                           LocalDateTime modifiedDT) {

    public MoldResponse(final Mold entity) {
        this(
                entity.getId(),
                entity.getKey(),
                entity.getName(),
                entity.getSpeed(),
                entity.getGlide(),
                entity.getTurn(),
                entity.getFade(),
                entity.getDiameter(),
                entity.getHeight(),
                entity.getRimDepth(),
                entity.getRimWidth(),
                entity.getCreatedDT(),
                entity.getModifiedDT()
        );
    }
}

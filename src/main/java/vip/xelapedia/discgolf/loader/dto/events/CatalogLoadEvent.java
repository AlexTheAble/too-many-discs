package vip.xelapedia.discgolf.loader.dto.events;

import lombok.Builder;
import lombok.NonNull;


public record CatalogLoadEvent(@NonNull String key,
                               @NonNull PlasticLoad plastic,
                               @NonNull ManufacturerLoad manufacturer,
                               @NonNull MoldLoad mold) {

    public record PlasticLoad(String key,
                              String name) {

    }

    public record ManufacturerLoad(String key,
                                   String name) {

    }

    @Builder
    public record MoldLoad(@NonNull String key,
                           @NonNull String name,
                           double speed,
                           double glide,
                           double turn,
                           double fade,
                           double diameter,
                           double height,
                           double rimDepth,
                           double rimWidth) {

    }
}

package vip.xelapedia.discgolf.catalog.controller.dto.response;


import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record MoldWebResponse(UUID id,
                              String name,
                              double speed,
                              double glide,
                              double turn,
                              double fade,
                              double diameter,
                              double height,
                              double rimDepth,
                              double rimWidth,
                              List<ManufacturerWebResponse> manufacturers,
                              List<PlasticWebResponse> plastics) {
}

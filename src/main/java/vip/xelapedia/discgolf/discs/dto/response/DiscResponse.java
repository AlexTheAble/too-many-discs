package vip.xelapedia.discgolf.discs.dto.response;

import vip.xelapedia.discgolf.discs.internal.entity.Disc;

import java.time.LocalDateTime;
import java.util.UUID;

public record DiscResponse(UUID id,
                           String key,
                           double weight,
                           PlasticResponse plastic,
                           ManufacturerResponse manufacturer,
                           MoldResponse mold,
                           LocalDateTime createdDT,
                           LocalDateTime modifiedDT) {

    public DiscResponse(Disc entity) {
        this(
                entity.getId(),
                entity.getKey(),
                entity.getWeight(),
                new PlasticResponse(entity.getPlastic()),
                new ManufacturerResponse(entity.getManufacturer()),
                new MoldResponse(entity.getMold()),
                entity.getCreatedDT(),
                entity.getModifiedDT()
        );
    }
}

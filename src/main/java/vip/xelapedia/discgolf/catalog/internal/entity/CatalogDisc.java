package vip.xelapedia.discgolf.catalog.internal.entity;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import vip.xelapedia.discgolf.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "catalog_disc", schema = "catalog")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class CatalogDisc extends BaseEntity {
    private String key;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @ManyToOne
    @JoinColumn(name = "plastic_id")
    private Plastic plastic;

    @ManyToOne
    @JoinColumn(name = "mold_id")
    private Mold mold;
}

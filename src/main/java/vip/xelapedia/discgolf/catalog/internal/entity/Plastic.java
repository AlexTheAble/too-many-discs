package vip.xelapedia.discgolf.catalog.internal.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import vip.xelapedia.discgolf.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "plastic", schema = "catalog")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Plastic extends BaseEntity {
    private String key;
    private String name;

    @OneToMany(mappedBy = "plastic")
    private List<CatalogDisc> catalogDiscs;
}

package vip.xelapedia.discgolf.catalog.internal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import vip.xelapedia.discgolf.common.entity.BaseEntity;

import java.util.List;

@Entity
@Table(name = "mold", schema = "catalog")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Mold extends BaseEntity {
    private String key;
    private String name;
    private String pageLink;

    private double speed;
    private double glide;
    private double turn;
    private double fade;

    private double diameter;
    private double height;
    private double rimDepth;
    private double rimWidth;

    @OneToMany(mappedBy = "mold")
    private List<CatalogDisc> catalogDiscs;
}

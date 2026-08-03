package vip.xelapedia.discgolf.discs.internal.entity;

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
@Table(name = "mold", schema = "discs")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Mold extends BaseEntity {
    private String key;
    private String name;

    private int speed;
    private int glide;
    private int turn;
    private int fade;

    private double diameter;
    private double height;
    private double rimDepth;
    private double rimWidth;

    @OneToMany(mappedBy = "mold")
    private List<Disc> discs;
}

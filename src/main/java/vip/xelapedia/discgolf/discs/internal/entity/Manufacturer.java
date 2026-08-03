package vip.xelapedia.discgolf.discs.internal.entity;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import vip.xelapedia.discgolf.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "manufacturer", schema = "discs")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Manufacturer extends BaseEntity {
    private String key;
    private String name;

    @OneToMany(mappedBy = "manufacturer")
    private List<Disc> discs;
}

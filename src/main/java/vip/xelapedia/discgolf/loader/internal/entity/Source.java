package vip.xelapedia.discgolf.loader.internal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import vip.xelapedia.discgolf.common.entity.BaseEntity;

import java.net.URL;
import java.util.List;

@Entity
@Table(name = "source", schema = "loader")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Source extends BaseEntity {
    private String name;
    private String key;
    @Column(name = "baseurl")
    private URL baseUrl;
    private int priority;

    @OneToMany(mappedBy = "source")
    private List<SourcePage> sourcePages;
}

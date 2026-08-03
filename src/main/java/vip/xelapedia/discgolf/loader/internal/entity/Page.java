package vip.xelapedia.discgolf.loader.internal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import vip.xelapedia.discgolf.common.entity.BaseEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "page", schema = "loader")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Page extends BaseEntity {
    private String path;
    private LocalDateTime lastVisitedDT;
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "source_id")
    private Source source;
}

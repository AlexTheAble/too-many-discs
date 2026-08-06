package vip.xelapedia.discgolf.loader.internal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.jsoup.nodes.Document;
import vip.xelapedia.discgolf.common.entity.BaseEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "page", schema = "loader")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class SourcePage extends BaseEntity {
    private String path;
    private String type;
    private LocalDateTime lastVisitedDT;
    private boolean isActive;
    private String html;

    @ManyToOne
    @JoinColumn(name = "source_id")
    private Source source;
}

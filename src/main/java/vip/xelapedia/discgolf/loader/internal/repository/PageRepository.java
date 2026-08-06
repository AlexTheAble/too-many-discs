package vip.xelapedia.discgolf.loader.internal.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vip.xelapedia.discgolf.loader.internal.entity.Source;
import vip.xelapedia.discgolf.loader.internal.entity.SourcePage;

import java.util.UUID;
import java.util.stream.Stream;

@Repository
public interface PageRepository extends CrudRepository<SourcePage, UUID> {
    Stream<SourcePage> findAllBySourceAndType(Source source, String type);

    Page<SourcePage> findAllBySourceAndType(Source source, String type, Pageable pageable);

    boolean existsByPathAndSource(String path, Source source);

    void deleteBySource(Source source);
}

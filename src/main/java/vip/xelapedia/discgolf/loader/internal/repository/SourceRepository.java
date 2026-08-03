package vip.xelapedia.discgolf.loader.internal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vip.xelapedia.discgolf.loader.internal.entity.Source;

import java.util.UUID;

@Repository
public interface SourceRepository extends CrudRepository<Source, UUID> {
    Source findByKey(final String key);
}

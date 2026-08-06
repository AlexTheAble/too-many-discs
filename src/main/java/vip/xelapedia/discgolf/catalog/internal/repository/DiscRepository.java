package vip.xelapedia.discgolf.catalog.internal.repository;

import vip.xelapedia.discgolf.catalog.internal.entity.CatalogDisc;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DiscRepository extends CrudRepository<CatalogDisc, UUID> {
    Optional<CatalogDisc> findByKey(String key);
}

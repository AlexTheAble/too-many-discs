package vip.xelapedia.discgolf.catalog.internal.repository;

import vip.xelapedia.discgolf.catalog.internal.entity.Plastic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlasticRepository extends CrudRepository<Plastic, UUID> {
    Optional<Plastic> findByKey(String key);
}

package vip.xelapedia.discgolf.catalog.internal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vip.xelapedia.discgolf.catalog.internal.entity.Mold;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MoldRepository extends CrudRepository<Mold, UUID> {
    Optional<Mold> findByKey(String key);
}

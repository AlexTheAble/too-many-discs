package vip.xelapedia.discgolf.discs.internal.repository;

import vip.xelapedia.discgolf.discs.internal.entity.Manufacturer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ManufacturerRepository extends CrudRepository<Manufacturer, UUID> {
    Optional<Manufacturer> findByKey(String key);
}

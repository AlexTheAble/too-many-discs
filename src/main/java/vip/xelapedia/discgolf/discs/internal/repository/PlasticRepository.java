package vip.xelapedia.discgolf.discs.internal.repository;

import vip.xelapedia.discgolf.discs.internal.entity.Plastic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlasticRepository extends CrudRepository<Plastic, UUID> {
}

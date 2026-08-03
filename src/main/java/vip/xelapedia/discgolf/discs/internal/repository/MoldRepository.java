package vip.xelapedia.discgolf.discs.internal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vip.xelapedia.discgolf.discs.internal.entity.Mold;

import java.util.UUID;

@Repository
public interface MoldRepository extends CrudRepository<Mold, UUID> {
}

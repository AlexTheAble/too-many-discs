package vip.xelapedia.discgolf.discs.internal.repository;

import vip.xelapedia.discgolf.discs.internal.entity.Disc;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DiscRepository extends CrudRepository<Disc, UUID> {
}

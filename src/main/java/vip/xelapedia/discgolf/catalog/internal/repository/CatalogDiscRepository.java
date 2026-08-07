package vip.xelapedia.discgolf.catalog.internal.repository;

import vip.xelapedia.discgolf.catalog.internal.entity.CatalogDisc;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vip.xelapedia.discgolf.catalog.internal.entity.Mold;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CatalogDiscRepository extends CrudRepository<CatalogDisc, UUID> {
    Optional<CatalogDisc> findByKey(String key);

    List<CatalogDisc> findByMold(Mold mold);

    List<CatalogDisc> findByMoldIn(List<Mold> molds);
}

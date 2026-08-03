package vip.xelapedia.discgolf.loader.internal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vip.xelapedia.discgolf.loader.internal.entity.Page;

import java.util.UUID;

@Repository
public interface PageRepository extends CrudRepository<Page, UUID> {
}

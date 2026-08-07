package vip.xelapedia.discgolf.catalog.internal.search;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import vip.xelapedia.discgolf.catalog.internal.entity.Mold;
import vip.xelapedia.discgolf.common.search.SearchCriteria;

import java.util.List;


@AllArgsConstructor
public class MoldSearchSpecificationBuilder {
    private final List<SearchCriteria> criteria;

    public Specification<Mold> build() {
        if (criteria.isEmpty()) {
            return null;
        }

        Specification<Mold> spec = criteria.stream().findFirst().map(MoldSearchSpecification::new).orElse(null);

        for (int i = 1; i < criteria.size(); i++) {
            spec = Specification.where(spec).and(new MoldSearchSpecification(criteria.get(i)));
        }

        return spec;
    }
}

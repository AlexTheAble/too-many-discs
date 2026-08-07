package vip.xelapedia.discgolf.common.search;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

@AllArgsConstructor
public class SearchSpecificationBuilder {
    private final List<SearchCriteria> criteria;

    public <T> Specification<T> build() {
        if (criteria.isEmpty()) {
            return null;
        }

        Specification<T> spec = new SearchSpecification<T>(criteria.stream().findFirst().get());

        for (int i = 1; i < criteria.size(); i++) {
            spec = Specification.where(spec).and(new SearchSpecification<>(criteria.get(i)));
        }

        return spec;
    }
}

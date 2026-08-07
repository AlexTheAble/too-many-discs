package vip.xelapedia.discgolf.catalog.internal.search;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import lombok.AllArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;
import vip.xelapedia.discgolf.catalog.internal.entity.Mold;
import vip.xelapedia.discgolf.common.search.SearchCriteria;

@AllArgsConstructor
public class MoldSearchSpecification implements Specification<Mold> {
    private final SearchCriteria criteria;

    @Override
    public @Nullable Predicate toPredicate(@NonNull final Root<Mold> root,
                                           @NonNull final CriteriaQuery<?> query,
                                           @NonNull final CriteriaBuilder criteriaBuilder) {
        switch (criteria.operation()) {
            case EQUAL -> {
                return criteriaBuilder.equal(root.get(criteria.key()), criteria.value());
            }
            case NOT_EQUAL -> {
                return criteriaBuilder.notEqual(root.get(criteria.key()), criteria.value());
            }
            case LESS_THAN -> {
                return criteriaBuilder.lessThan(root.get(criteria.key()), criteria.value().toString());
            }
            case LESS_THAN_OR_EQUAL -> {
                return criteriaBuilder.lessThanOrEqualTo(root.get(criteria.key()), criteria.value().toString());
            }
            case GREATER_THAN -> {
                return criteriaBuilder.greaterThan(root.get(criteria.key()), criteria.value().toString());
            }
            case GREATER_THAN_OR_EQUAL -> {
                return criteriaBuilder.greaterThanOrEqualTo(root.get(criteria.key()), criteria.value().toString());
            }
        }
        return null;
    }
}

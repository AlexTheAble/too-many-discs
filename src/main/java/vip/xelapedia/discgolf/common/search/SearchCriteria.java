package vip.xelapedia.discgolf.common.search;

import vip.xelapedia.discgolf.common.search.enums.SearchOperation;

public record SearchCriteria(String key,
                             SearchOperation operation,
                             Object value) {
}

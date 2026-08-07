package vip.xelapedia.discgolf.catalog.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.*;
import vip.xelapedia.discgolf.catalog.controller.dto.response.MoldWebResponse;
import vip.xelapedia.discgolf.catalog.internal.service.CatalogDiscService;
import vip.xelapedia.discgolf.common.search.SearchCriteria;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/catalog")
public class CatalogController {
    private final CatalogDiscService catalogDiscService;

    @GetMapping("/mold/{id}")
    public MoldWebResponse getMoldDetails(@PathVariable final UUID id) {
        return catalogDiscService.getMoldDetailsById(id);
    }

    @PostMapping("/mold/search")
    public PagedModel<MoldWebResponse> searchMolds(@RequestBody final List<SearchCriteria> search,
                                                   @PageableDefault final Pageable pageable) {
        return catalogDiscService.searchMolds(search, pageable);
    }
}

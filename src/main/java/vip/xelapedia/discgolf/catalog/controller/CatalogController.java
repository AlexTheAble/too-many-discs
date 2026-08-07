package vip.xelapedia.discgolf.catalog.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.xelapedia.discgolf.catalog.controller.dto.response.MoldWebResponse;
import vip.xelapedia.discgolf.catalog.internal.service.CatalogDiscService;

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
}

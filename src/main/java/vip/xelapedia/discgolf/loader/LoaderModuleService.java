package vip.xelapedia.discgolf.loader;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vip.xelapedia.discgolf.loader.internal.loader.CatalogLoader;

@Service
@AllArgsConstructor
@Slf4j
public class LoaderModuleService {
    private final CatalogLoader catalogLoader;

    public void doLoad(final boolean reloadPages) {
        catalogLoader.preformLoad(reloadPages);
    }
}

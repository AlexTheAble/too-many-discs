package vip.xelapedia.discgolf;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import vip.xelapedia.discgolf.catalog.CatalogModuleService;
import vip.xelapedia.discgolf.loader.LoaderModuleService;
import vip.xelapedia.discgolf.loader.internal.clients.InfiniteDiscsClientProxy;

@Component
@Slf4j
@AllArgsConstructor
public class ApplicationDataLoader implements CommandLineRunner {
    private final CatalogModuleService discsService;
    private final LoaderModuleService loaderModuleService;
    private final InfiniteDiscsClientProxy infiniteDiscsClientProxy;

    @Override
    public void run(String... args) {
        loaderModuleService.doLoad();
    }
}

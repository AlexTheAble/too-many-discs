package vip.xelapedia.discgolf;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import vip.xelapedia.discgolf.loader.LoaderModuleService;

@Component
@Slf4j
@AllArgsConstructor
public class ApplicationDataLoader implements CommandLineRunner {
    private final LoaderModuleService loaderModuleService;

    @Override
    public void run(String... args) {
//        loaderModuleService.doLoad(false);
    }
}

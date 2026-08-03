package vip.xelapedia.discgolf.loader;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vip.xelapedia.discgolf.loader.internal.service.LoadingService;

@Service
@AllArgsConstructor
@Slf4j
public class LoaderModuleService {
    private final LoadingService infiniteDiscLoader;

    public void doLoad() {
        infiniteDiscLoader.preformLoad(false);
    }
}

package vip.xelapedia.discgolf.loader.internal.clients;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;
import vip.xelapedia.discgolf.loader.internal.clients.contract.InfiniteDiscsNavData;

import java.util.List;

@Component
@AllArgsConstructor
public class InfiniteDiscsClientProxy {
    private final InfiniteDiscsClient client;

    public List<InfiniteDiscsNavData.Brand> getBrands() {
        return client.getNavData(Strings.EMPTY).headerData().brandMenu();
    }
}

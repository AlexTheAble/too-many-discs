package vip.xelapedia.discgolf.loader.internal.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vip.xelapedia.discgolf.loader.internal.clients.contract.InfiniteDiscsNavData;

@FeignClient(name = "InfiniteDiscClient", url = "https://infinitediscs.com")
public interface InfiniteDiscsClient {
    @RequestMapping(method = RequestMethod.POST, value = "/Home/LoadNavbarData")
    InfiniteDiscsNavData getNavData(String body);
}

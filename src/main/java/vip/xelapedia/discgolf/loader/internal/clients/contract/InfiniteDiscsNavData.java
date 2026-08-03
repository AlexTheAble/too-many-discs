package vip.xelapedia.discgolf.loader.internal.clients.contract;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record InfiniteDiscsNavData(@JsonProperty("header_data") HeaderData headerData) {

    public record HeaderData(@JsonProperty("BrandMenu") List<Brand> brandMenu) {

    }

    public record Brand(@JsonProperty("ItemTitle") String title,
                        @JsonProperty("ItemLink") String link) {
    }
}

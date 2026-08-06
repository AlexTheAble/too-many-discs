package vip.xelapedia.discgolf.loader.internal.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Optional;

public final class HtmlParserUtil {

    public static Optional<Document> fetchDocument(final String url) {
        final Document doc;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            return Optional.empty();
        }

        return Optional.ofNullable(doc);
    }

    public static Document parseHtmlString(final String html) {
        return Jsoup.parse(html);
    }
}

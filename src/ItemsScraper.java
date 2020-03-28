import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ItemsScraper {

    public List<String> getItemsList(String baseUrl, String url) throws InterruptedException, IOException {
        List<String> items = new ArrayList<>();
        Connection connect = Jsoup.connect(baseUrl+url);
        Document document = connect.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                .referrer("http://www.google.com")
                .timeout(6000)
                .ignoreContentType(true)
                .get();
        Elements elementByClass = document.getElementsByClass("mw-category-group");
        for (Element e: elementByClass) {
            Elements things = e.getElementsByTag("a");
            for (Element t: things) items.add(t.attr("href"));
//            System.out.println(e.getElementsByTag("a").attr("href"));
        }

        return items;
    }
}

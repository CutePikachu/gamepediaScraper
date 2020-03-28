import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
        String baseUrl = "https://townshiptale.gamepedia.com";
        String imageDir = "/Users/tina/learning/java/src/images";
        List<String> itemNames = new ItemsScraper().getItemsList(baseUrl, "/Category:Items");
        List<Item> items = new ArrayList<>();
        for (String item: itemNames) {
            items.add(ItemScraper.obtainInformation(baseUrl, item, imageDir));
        }
        CsvWriter.writeItems("/Users/tina/learning/java/src/items.csv", items);
    }
}

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
        String baseUrl = "https://townshiptale.gamepedia.com";
        String imageDir = args[0];
        List<String> itemNames = new ItemsScraper().getItemsList(baseUrl, "/Category:Items");
        List<Item> items = new ArrayList<>();
        for (String item: itemNames) {
            items.add(ItemScraper.obtainInformation(baseUrl, item, imageDir));
        }
        CsvWriter.writeItems(args[1], items);
    }
}

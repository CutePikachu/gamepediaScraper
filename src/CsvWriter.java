import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvWriter {

    public static void writeItems(String filePath, List<Item> items)
    {

        File file = new File(filePath);
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            // adding header to csv
            String[] header = { "Name", "Description", "Image", "link"};
            writer.writeNext(header);

            // add data to csv
            for (Item item: items) {
                String[] data = { item.getName(), item.getDescription(), item.getImage(), item.getLink() };
                writer.writeNext(data);
            }
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

class ItemScraper {
    private static String downloadImage(String strImageURL, String folder) {

        //get file name from image path
        String strImageName =
                strImageURL.substring(strImageURL.lastIndexOf("/") + 1).split("[?]")[0];
        strImageName = strImageName.replaceAll("[0-9]{2,3}px-", "");
        System.out.println("Saving: " + strImageName + ", from: " + strImageURL);

        try {
            //open the stream from URL
            URL urlImage = new URL(strImageURL);
            InputStream in = urlImage.openStream();
            byte[] buffer = new byte[4096];
            int n = -1;

            OutputStream os = new FileOutputStream(folder + "/" + strImageName);
            //write bytes to the output stream
            while ((n = in.read(buffer)) != -1) {
                os.write(buffer, 0, n);
            }
            //close the stream
            os.close();
            System.out.println("Image saved");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return folder + "/" + strImageName;
    }

    static Item obtainInformation(String baseUrl, String url, String imageDir) throws IOException, InterruptedException {
        Item item = new Item();
        Connection connect = Jsoup.connect(baseUrl+url);

        Document document = connect.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                .referrer("http://www.google.com")
                .timeout(6000)
                .ignoreContentType(true)
                .get();
        Element el = document.getElementById("mw-content-text");
        // get description
        item.setDescription(el.getElementsByTag("p").get(0).text());
        item.setName(url.substring(1));
        // get image
        Elements images = el.getElementsByTag("img");
        if (images.size() < 1) return item;
        if (el.getElementsByClass("infobox").size() < 1) return item;
        String imageUrl = images.get(0).attr("src");
        String image = downloadImage(imageUrl, imageDir);
        item.setImage((image));
        return item;
    }
}

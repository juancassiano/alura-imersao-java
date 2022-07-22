import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        // String url = "https://imdb-api.com/en/API/Top250Movies/k_27f42loh";
        // String url = "https://api.mocki.io/v2/549a5d8b/Top250Movies"; // MOCK
        // ExtractContent extractContent = new ExtractImdbContent();

        String url = "https://api.nasa.gov/planetary/apod?api_key=l81Fp2A9hjCWEk6TzGSlp159moFZ9zvKAeKHObgc&start_date=2022-07-20&end_date=2022-07-21";
        ExtractContent extractContent = new ExtractNasaContent();

        var http = new ClientHttp();
        String json = http.buscaDados(url);
        List<Content> contents = extractContent.extractContents(json);

        var stickersGenerator = new StickersGenerator();

        for (int i = 0; i < 10; i++) {
            Content content = contents.get(i);

            InputStream inputStream = new URL(content.getUrlImage()).openStream();

            String fileName = content.getTitle() + ".png";
            stickersGenerator.create(inputStream, fileName);

            System.out.println(content.getTitle());
            System.out.println();
        }

    }
}

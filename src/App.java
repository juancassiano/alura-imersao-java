import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // String url = "https://imdb-api.com/en/API/Top250Movies/k_27f42loh";
        String url = "https://api.mocki.io/v2/549a5d8b/Top250Movies"; // MOCK
        URI address = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(address).GET().build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        for (Map<String, String> filme : listaDeFilmes) {
            String urlImage = filme.get("image");
            String title = filme.get("title");

            InputStream inputStream = new URL(urlImage).openStream();

            String fileName = title + ".png";
            StickersGenerator stickersGenerator = new StickersGenerator();
            stickersGenerator.create(inputStream, fileName);

            System.out.println(title);
            System.out.println(filme.get("imDbRating"));
            System.out.println();
        }

    }
}

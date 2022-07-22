import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtractImdbContent implements ExtractContent {

  public List<Content> extractContents(String json) {
    var parser = new JsonParser();
    List<Map<String, String>> contentList = parser.parse(json);

    List<Content> contents = new ArrayList<>();

    for (Map<String, String> values : contentList) {
      String title = values.get("title");
      String urlImage = values.get("image")
          .replaceAll("(@+)(.*).jpg$", "$1.jpg");

      var content = new Content(title, urlImage);

      contents.add(content);

    }

    return contents;
  }
}

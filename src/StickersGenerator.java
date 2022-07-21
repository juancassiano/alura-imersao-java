import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.awt.Color;
import java.awt.Font;

import javax.imageio.ImageIO;

public class StickersGenerator {

  public void create(InputStream inputStream, String fileName) throws Exception {
    // InputStream inputStream = new FileInputStream(new File("assets/wololo.jpg"));
    // InputStream inputStream = new
    // URL("https://imersao-java-apis.s3.amazonaws.com/TopMovies_2.jpg").openStream();
    BufferedImage originalImage = ImageIO.read(inputStream);

    int width = originalImage.getWidth();
    int height = originalImage.getHeight();
    int newHeight = height + 200;
    File outDirFile = new File("./assets/" + fileName);

    BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

    Graphics2D graphics = (Graphics2D) newImage.getGraphics();
    graphics.drawImage(originalImage, 0, 0, null);

    Font font = new Font(Font.SANS_SERIF, Font.BOLD, 64);
    graphics.setColor(Color.YELLOW);
    graphics.setFont(font);

    graphics.drawString("IMERSAO JAVA ALURA", 100, newHeight - 100);

    ImageIO.write(newImage, "png", outDirFile);

  }

}

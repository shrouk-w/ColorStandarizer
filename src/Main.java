import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Main {


    public static void main(String[] args) {
        try {

            File input = new File(args[0]);
            BufferedImage image = ImageIO.read(input);


            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    int argb = image.getRGB(x, y);

                    int red = (argb >> 16) & 0xC0;
                    int green = (argb >> 8) & 0xC0;
                    int blue = argb & 0xC0;

                    int newArgb = (red << 16) | (green << 8) | blue;

                    image.setRGB(x, y, newArgb);
                }
            }


            File output = new File("output.png");
            ImageIO.write(image, "png", output);

            System.out.println("Image processed and saved as output.png");
        } catch (IOException e) {
            System.err.println("Error processing the image: " + e.getMessage());
        }
    }
}
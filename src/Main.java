import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    // Define a list of commonly used colors
    private static final List<int[]> colorPalette = new ArrayList<>();

    static {
        // Primary Colors
        colorPalette.add(new int[]{0, 0, 0});       // Black
        colorPalette.add(new int[]{255, 255, 255}); // White
        colorPalette.add(new int[]{255, 0, 0});     // Red
        colorPalette.add(new int[]{0, 255, 0});     // Green
        colorPalette.add(new int[]{0, 0, 255});     // Blue

        // Secondary Colors
        colorPalette.add(new int[]{255, 255, 0});   // Yellow
        colorPalette.add(new int[]{0, 255, 255});   // Cyan
        colorPalette.add(new int[]{255, 0, 255});   // Magenta

        // Grayscale Shades
        //colorPalette.add(new int[]{211, 211, 211}); // Light Gray
        //colorPalette.add(new int[]{128, 128, 128}); // Gray
        //colorPalette.add(new int[]{64, 64, 64});    // Dark Gray

        // Pastel Shades
        colorPalette.add(new int[]{255, 182, 193}); // Light Pink
        colorPalette.add(new int[]{173, 216, 230}); // Light Blue
        colorPalette.add(new int[]{144, 238, 144}); // Light Green
        colorPalette.add(new int[]{255, 218, 185}); // Peach
        colorPalette.add(new int[]{255, 255, 224}); // Light Yellow

        // Rich Colors
        colorPalette.add(new int[]{255, 165, 0});   // Orange
        colorPalette.add(new int[]{128, 0, 128});   // Purple
        colorPalette.add(new int[]{255, 20, 147});  // Deep Pink
        colorPalette.add(new int[]{34, 139, 34});   // Forest Green
        colorPalette.add(new int[]{0, 0, 128});     // Navy Blue

        // Bright Colors
        colorPalette.add(new int[]{255, 69, 0});    // Bright Red
        colorPalette.add(new int[]{50, 205, 50});   // Bright Green
        colorPalette.add(new int[]{30, 144, 255});  // Bright Blue
        colorPalette.add(new int[]{0, 255, 0});     // Lime

        // Earth Tones
        colorPalette.add(new int[]{165, 42, 42});   // Brown
        colorPalette.add(new int[]{255, 215, 0});   // Gold
        colorPalette.add(new int[]{128, 128, 0});   // Olive
        colorPalette.add(new int[]{0, 128, 128});   // Teal
        colorPalette.add(new int[]{128, 0, 0});     // Maroon
    }

    public static void main(String[] args) {
        try {
            // Load the image
            File input = new File("C:\\Users\\User\\Desktop\\javaprojects\\colorchanger\\src\\vvv.png");
            BufferedImage image = ImageIO.read(input);

            // Process each pixel in the image
            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    int rgb = image.getRGB(x, y);

                    // Extract RGB components
                    int red = (rgb >> 16) & 0xFF;
                    int green = (rgb >> 8) & 0xFF;
                    int blue = rgb & 0xFF;

                    // Find the closest color from the palette
                    int[] closestColor = findClosestColor(red, green, blue);

                    // Set the pixel to the closest color
                    int newRgb = (closestColor[0] << 16) | (closestColor[1] << 8) | closestColor[2];
                    image.setRGB(x, y, newRgb);
                }
            }

            // Save the modified image
            File output = new File("output.png");
            ImageIO.write(image, "png", output);

            System.out.println("Image processed and saved as output.png");
        } catch (IOException e) {
            System.err.println("Error processing the image: " + e.getMessage());
        }
    }

    // Find the closest color from the palette using Euclidean distance
    private static int[] findClosestColor(int red, int green, int blue) {
        int[] closestColor = null;
        double closestDistance = Double.MAX_VALUE;

        for (int[] color : colorPalette) {
            double distance = Math.sqrt(
                    Math.pow(red - color[0], 2) +
                            Math.pow(green - color[1], 2) +
                            Math.pow(blue - color[2], 2)
            );
            if (distance < closestDistance) {
                closestDistance = distance;
                closestColor = color;
            }
        }

        return closestColor;
    }
}

package Labs.Lab_4;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class JImageDisplay extends JComponent {
    private BufferedImage image;

    JImageDisplay(int width, int height) {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        super.setPreferredSize(new Dimension(width, height));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
    }

    public void clearImage()
    {
        int[] array = new int[getWidth() * getHeight()];
        image.setRGB(0, 0, getWidth(), getHeight(), array, 0, 1);
    }

    public void drawPixel(int x, int y, int rgbColor) {
        image.setRGB(x,y,rgbColor);
    }
}

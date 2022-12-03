package Labs.Lab_4;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public class FractalExplorer
{
    private int displaySize;
    private JImageDisplay display;
    private FractalGenerator fractal;
    private Rectangle2D.Double range;


    public FractalExplorer(int displaySize) {
        this.displaySize = displaySize;

        fractal = new MandelBrot();
        range = new Rectangle2D.Double();
        fractal.getInitialRange(range);
        display = new JImageDisplay(displaySize, displaySize);

    }


    public void createAndShowGUI()
    {
        JFrame frame = new JFrame("Fractal Explorer");
        frame.add(display, BorderLayout.CENTER);

        JButton resetButton = new JButton("Сброс фрактала");
        frame.add(resetButton, BorderLayout.SOUTH);

        ButtonHandler resetHandler = new ButtonHandler(); // событие по кнопке
        resetButton.addActionListener(resetHandler);

        MouseHandler click = new MouseHandler(); // событие по щелчку мыши
        display.addMouseListener(click);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // закрытие окна

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);

    }


    public void drawFractal()
    {
        for (int x = 0; x < displaySize; x++){
            for (int y = 0; y < displaySize; y++){
                double xCoord = fractal.getCoord(range.x, range.x + range.width, displaySize, x);
                double yCoord = fractal.getCoord(range.y, range.y + range.height, displaySize, y);

                int count = fractal.numIterations(xCoord, yCoord);
                if (count == -1)
                    display.drawPixel(x, y, 0);
                else {
                    float hue = 0.6f + (float) count / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    display.drawPixel(x, y, rgbColor);
                }
            }
        }
        display.repaint();
    }

    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("Сброс фрактала")) {
                fractal.getInitialRange(range);
                drawFractal();
            }
        }
    }

    private class MouseHandler extends MouseAdapter
    {
        @Override
        public void mouseClicked(MouseEvent e)
        {
            int x = e.getX();
            double xCoord = fractal.getCoord(range.x,
                    range.x + range.width, displaySize, x);

            int y = e.getY();
            double yCoord = fractal.getCoord(range.y,
                    range.y + range.height, displaySize, y);

            fractal.recenterAndZoomRange(range, xCoord, yCoord, 0.5);
            drawFractal();
        }
    }

    public static void main(String[] args)
    {
        FractalExplorer displayExplorer = new FractalExplorer(800);
        displayExplorer.createAndShowGUI();
        displayExplorer.drawFractal();
    }
}
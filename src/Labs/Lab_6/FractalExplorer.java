package Labs.Lab_6;

import Labs.Lab_4.FractalGenerator;
import Labs.Lab_4.JImageDisplay;
import Labs.Lab_4.MandelBrot;
import Labs.Lab_5.BurningShip;
import Labs.Lab_5.Tricorn;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class FractalExplorer {

    private int displaySize;
    private JImageDisplay display;
    private FractalGenerator fractal;
    private Rectangle2D.Double range;

    int rowsRemaining;

    public FractalExplorer(int displaySize) {
        this.displaySize = displaySize;

        fractal = new MandelBrot();
        range = new Rectangle2D.Double();
        fractal.getInitialRange(range);
        display = new JImageDisplay(displaySize, displaySize);
    }

    JButton saveButton = new JButton("save");
    JButton resetButton = new JButton("Сброс фрактала");
    JComboBox<FractalGenerator> myComboBox = new JComboBox<>();
    public void createAndShowGUI() {
        JLabel label = new JLabel("Fractal");
        JFrame frame = new JFrame("Fractal Explorer");

        JPanel panelNorth = new JPanel();
        JPanel panelSouth = new JPanel();

        myComboBox.addItem(new MandelBrot());
        myComboBox.addItem(new Tricorn());
        myComboBox.addItem(new BurningShip());

        ButtonHandler resetHandler = new ButtonHandler(); // событие по кнопке
        resetButton.addActionListener(resetHandler);
        saveButton.addActionListener(resetHandler);
        myComboBox.addActionListener(resetHandler);

        MouseHandler click = new MouseHandler(); // событие по щелчку мыши
        display.addMouseListener(click);

        panelNorth.add(label);
        panelNorth.add(myComboBox);
        panelSouth.add(saveButton);
        panelSouth.add(resetButton);

        frame.add(panelSouth, BorderLayout.SOUTH);
        frame.add(panelNorth, BorderLayout.NORTH);
        frame.add(display, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("Сброс фрактала")) {
                fractal.getInitialRange(range);
                drawFractal();
            } else if (command.equals("save")) {
                JFileChooser fileChooser = new JFileChooser();
                FileFilter filter = new FileNameExtensionFilter("PNG Images", "png");
                fileChooser.setFileFilter(filter);
                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.setDialogTitle("Сохранение файла");
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int result = fileChooser.showSaveDialog(display);

                if (result == JFileChooser.APPROVE_OPTION) {
                    JOptionPane.showMessageDialog(display, "Выбран файл: "
                            + fileChooser.getSelectedFile());
                    try {
                        BufferedImage image = display.image;
                        ImageIO.write(image, "png", fileChooser.getSelectedFile());
                    }
                    catch (Exception exception) {
                        JOptionPane.showMessageDialog(display,
                                exception.getMessage(), "Cannot Save Image",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else if (e.getSource() instanceof JComboBox) {
                JComboBox name = (JComboBox) e.getSource();
                fractal = (FractalGenerator) name.getSelectedItem();
                assert fractal != null;
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

    void enableUI(boolean value) {
        myComboBox.setEnabled(value);
        resetButton.setEnabled(value);
        saveButton.setEnabled(value);
    }
    public void drawFractal() {
        enableUI(false);
        rowsRemaining = displaySize;
        for (int y = 0; y < displaySize; y++){
            FractalWorker fractalWorker = new FractalWorker(y);
            fractalWorker.execute();
        }
        display.repaint();
    }



    private class FractalWorker extends SwingWorker<Object, Object>  {
        int y;
        int[] rgbColors;


        public FractalWorker(int y) {
            this.y = y;
        }

        @Override
        protected Object doInBackground() throws Exception {
            rgbColors = new int[displaySize];
            for (int x = 0; x < displaySize; x++) {
                double xCoord = fractal.getCoord(range.x, range.x + range.width, displaySize, x);
                double yCoord = fractal.getCoord(range.y, range.y + range.height, displaySize, y);

                int count = fractal.numIterations(xCoord, yCoord);
                if (count == -1)
                    rgbColors[x] = 0;
                else {
                    float hue = 0.6f + (float) count / 200f;
                    rgbColors[x] = Color.HSBtoRGB(hue, 1f, 1f);
                }
            }
            return null;
        }

        protected void done() {
            for (int i = 0; i < rgbColors.length; i++) {
                display.drawPixel(i, y, rgbColors[i]);
            }
            display.repaint(0, 0, y, displaySize, 1 );
            rowsRemaining--;
            if (rowsRemaining == 0) {
                enableUI(true);
            }
        }
    }


    public static void main(String[] args) {
        FractalExplorer displayExplorer = new FractalExplorer(800);
        displayExplorer.createAndShowGUI();
        displayExplorer.drawFractal();
    }
}

package Labs.Lab_5;

import Labs.Lab_4.ComplexNumber;
import Labs.Lab_4.FractalGenerator;

import java.awt.geom.Rectangle2D;

import static Labs.Lab_4.MandelBrot.MAX_ITERATIONS;

public class BurningShip extends FractalGenerator {
    @Override
    public void getInitialRange(Rectangle2D.Double range) {
        range.x = - 2;
        range.y = - 2.5;

        range.width = 4;
        range.height = 4;
    }

    @Override
    public int numIterations(double x, double y) {
        int count = 0;
        ComplexNumber z = new ComplexNumber(0, 0);
        ComplexNumber c = new ComplexNumber(x, y);
        double zreal = 0;
        double zimaginary = 0;

        while (count < MAX_ITERATIONS && zreal * zreal + zimaginary * zimaginary < 4) {
            double zrealNew = zreal * zreal - zimaginary * zimaginary + x;
            double zimaginaryNew = 2 * Math.abs(zreal) * Math.abs(zimaginary) + y;
            zreal = zrealNew;
            zimaginary = zimaginaryNew;

            count++;
        }

        if (count == MAX_ITERATIONS)
            return - 1;
        return count;
    }

    public String toString() {
        return "Burning Ship";
    }
}

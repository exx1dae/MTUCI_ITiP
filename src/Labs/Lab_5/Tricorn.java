package Labs.Lab_5;

import Labs.Lab_4.ComplexNumber;
import Labs.Lab_4.FractalGenerator;

import java.awt.geom.Rectangle2D;

import static Labs.Lab_4.MandelBrot.MAX_ITERATIONS;

public class Tricorn extends FractalGenerator {

    @Override
    public void getInitialRange(Rectangle2D.Double range) {
        range.x = - 2;
        range.y = - 1.5;

        range.width = 3;
        range.height = 3;
    }

    @Override
    public int numIterations(double x, double y) {
        ComplexNumber z = new ComplexNumber(0, 0);
        ComplexNumber c = new ComplexNumber(x, y);

        int count = 0;

        while (count < MAX_ITERATIONS && z.getRe() * z.getRe() + z.getIm() * z.getIm() < 4) {
            z = ComplexNumber.add(z.conjugate().square(), c);
            count++;
        }

        if (count == MAX_ITERATIONS)
            return - 1;
        return count;
    }

    public String toString() {
        return "Tricorn";
    }
}

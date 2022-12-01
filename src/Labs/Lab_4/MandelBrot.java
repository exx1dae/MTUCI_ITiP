package Labs.Lab_4;

import java.awt.geom.Rectangle2D;

public class MandelBrot extends FractalGenerator {

    public static final int MAX_ITERATIONS = 2000;


    @Override
    public void getInitialRange(Rectangle2D.Double range) {
        range.x = -2;
        range.y = -1.5;

        range.width = 3;
        range.height = 3;
    }

    @Override
    public int numIterations(double x, double y)
    {
        int count = 0;
        ComplexNumber z = new ComplexNumber(0,0);
        ComplexNumber c = new ComplexNumber(x,y);


        while (count < MAX_ITERATIONS &&
                z.getRe() * z.getRe() + z.getIm() * z.getIm() < 4)
        {
            z = ComplexNumber.add(z.square(), c);
            count += 1;
        }
        if (count == MAX_ITERATIONS)
        {
            return -1;
        }
        return count;
    }
}
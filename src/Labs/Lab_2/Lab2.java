package Labs.Lab_2;

import java.util.Scanner;

public class Lab2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Введите координаты 1 точки: ");
        Point3d first_point = new Point3d(input.nextDouble(), input.nextDouble(), input.nextDouble());
        System.out.println("Введите координаты 2 точки: ");
        Point3d second_point = new Point3d(input.nextDouble(), input.nextDouble(), input.nextDouble());
        System.out.println("Введите координаты 3 точки: ");
        Point3d third_point = new Point3d(input.nextDouble(), input.nextDouble(), input.nextDouble());
        if (first_point.equals(second_point) || second_point.equals(third_point) || first_point.equals(third_point))
            System.out.println("Точки равны");
        else
            System.out.println(computeArea(first_point, second_point, third_point));
    }
    public static double computeArea(Point3d first_point, Point3d second_point, Point3d third_point) {
        double p = (first_point.distanceTo(second_point) + second_point.distanceTo(third_point)
                + first_point.distanceTo(third_point)) / 2;
        return Math.round(Math.sqrt(p * (p - first_point.distanceTo(second_point)) * (p - second_point.distanceTo(third_point))
                * (p - first_point.distanceTo(third_point))) * 100.0) / 100.0 ;
    }
}


package Tasks;

public class Task_1 {

    public static void main(String[] args) {
        System.out.println("--------------");
        System.out.println("Task 1_1: " + remainder(1, 3));
        System.out.println("--------------");
        System.out.println("Task 1_2: " + triArea(3, 2));
        System.out.println("--------------");
        System.out.println("Task 1_3: " + animals(2, 3, 5));
        System.out.println("--------------");
        System.out.println("Task 1_4: " + profitableGamble(0.2, 50, 9));
        System.out.println("--------------");
        System.out.println("Task 1_5: " + operation(24, 15, 9));
        System.out.println("--------------");
        System.out.println("Task 1_6: " + ctoa('A'));
        System.out.println("--------------");
        System.out.println("Task 1_7: " + addUpTo(3));
        System.out.println("--------------");
        System.out.println("Task 1_8: " + nextEdge(8, 10));
        System.out.println("--------------");
        System.out.println("Task 1_9: " + sumOfCubes(new int[] {1, 5, 9}));
        System.out.println("--------------");
        System.out.println("Task 1_10: " + abcMath(42, 5, 10));
        System.out.println("--------------");
    }

    /*
        В Java есть единственный оператор, способный обеспечить остаток от операции деления.
    Два числа передаются в качестве параметров. Первый параметр, разделенный на второй параметр, будет иметь остаток,
    возможно, ноль. Верните это значение.
     */

    public static int remainder(int a, int b) {
        return a % b;
    }

    /*
        Напишите функцию, которая принимает основание и высоту треугольника и возвращает его площадь.
     */

    public static double triArea(double b, double h) {
        return (b * h) / 2;
    }

    /*
    В этой задаче фермер просит вас сказать ему, сколько ног можно сосчитать среди всех его животных.
        Фермер разводит три вида:
    chickens = 2 legs cows = 4 legs pigs = 4 legs
        Фермер подсчитал своих животных, и он дает вам промежуточный итог для каждого вида. Вы должны реализовать
    функцию, которая возвращает общее количество ног всех животных.
     */

    public static int animals(int chickens, int cows, int pigs) {
        return chickens * 2 + cows * 4 + pigs * 4;
    }

    /*
        Создайте функцию, которая принимает три аргумента (prob, prize, pay) и возвращает true, если prob * prize > pay;
    в противном случае возвращает false.
        Чтобы проиллюстрировать это: profitableGamble (0,2, 50, 9) должен выдать значение true,
    поскольку 1 (0,2 * 50 - 9), а 1> 0.
     */

    public static boolean profitableGamble(double prob, double prize, double pay) {
        return prob * prize > pay;
    }

    /*
        Напишите функцию, которая принимает 3 числа и возвращает, что нужно сделать с a и b: они должны быть сложены,
    вычитаны, умножены или разделены, чтобы получить N. Если ни одна из операций не может дать N, верните "none".
    3 числа – это N, a и b.
     */

    public static String operation(int N, int a, int b) {
        try {
            if (a + b == N) {
                return "Added";
            } else if (a - b == N) {
                return "Subtracted";
            } else if (a * b == N) {
                return "Multiplied";
            } else if ((a / b == N) && (a % b == 0)) {
                return "Division";
            }
            else {
                return "None";
            }
        }
        catch (ArithmeticException exception) {
            return "None";
        }
    }

    /*
        Создайте функцию, которая возвращает значение ASCII переданного символа.
     */

    public static int ctoa(char symbol) {
        return symbol;
    }

    /*
        Напишите функцию, которая берет последнее число из последовательного списка чисел и
    возвращает сумму всех чисел до него и включая его.
    */

    public static int addUpTo(int number) {
        int sum = 0;
        for (int i = 0; i <= number; i++) {
            sum += i;
        }
        return sum;
    }

    /*
        Создайте функцию, которая находит максимальное значение третьего ребра треугольника,
    где длины сторон являются целыми числами.
     */

    public static int nextEdge(int firstEdge, int secondEdge) {
        return firstEdge + secondEdge - 1;
    }

    /*
        Создайте функцию, которая принимает массив чисел и возвращает сумму его кубов.
     */

    public static int sumOfCubes(int[] arrayOfNumbers) {
        int sum = 0;
        for (int number : arrayOfNumbers) {
            sum += (int) Math.pow(number, 3);
        }
        return sum;
    }

    /*
        Создайте функцию, которая будет для данного a, b, c выполнять следующие действия:
            – Добавьте A к себе B раз.
            – Проверьте, делится ли результат на C.
     */

    public static boolean abcMath(int a, int b, int c) {
        for (int i = 0; i < b; i++) {
            a += b;
        }
        return a % c == 0;
    }
}

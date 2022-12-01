package Tasks;

import java.util.Arrays;

public class Task_2 {
    public static void main(String[] args) {
        System.out.println("--------------");
        System.out.println("Task 2_1: " + repeat("mice", 5));
        System.out.println("--------------");
        System.out.println("Task 2_2: " + differenceMaxMin(new int[] {10, 4, 1, 4, -10, -50, 32, 21}));
        System.out.println("--------------");
        System.out.println("Task 2_3: " + isAvgWhole(new int[] {1, 3}));
        System.out.println("--------------");
        System.out.println("Task 2_4: " + Arrays.toString(cumulativeSum(new int[]{1, 2, 3})));
        System.out.println("--------------");
        System.out.println("Task 2_5: " + getDecimalPlaces("400.00"));
        System.out.println("--------------");
        System.out.println("Task 2_6: " + Fibonacci(7));
        System.out.println("--------------");
        System.out.println("Task 2_7: " + isValid("853a7 "));
        System.out.println("--------------");
        System.out.println("Task 2_8: " + isStrangePair("ratio", "orator"));
        System.out.println("--------------");
        System.out.println("Task 2_9_1: " + isPrefix("automation", "auto-"));
        System.out.println("Task 2_9_2: " + isSuffix("arachnophobia", "-phobia"));
        System.out.println("--------------");
        System.out.println("Task 2_10: " + boxSeq(2));
        System.out.println("--------------");
    }

    /*
        Создайте функцию, которая повторяет каждый символ в строке n раз.
     */

    public static String repeat(String string, int numberOfRepeat) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < string.length(); i++) {
            result.append(String.valueOf(string.charAt(i)).repeat(Math.max(0, numberOfRepeat)));
        }

        return result.toString();
    }

    /*
            Создайте функцию, которая принимает массив и возвращает разницу между самыми большими и
        самыми маленькими числами.
     */

    public static int differenceMaxMin(int[] numbers) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        
        for (int number : numbers) {
            if (number > max) {
                max = number;
            } else if (number < min) {
                min = number;
            }
        }

        return max - min;
    }

    /*
            Создайте функцию, которая принимает массив в качестве аргумента и возвращает true или
        false в зависимости от того, является ли среднее значение всех элементов массива целым числом или нет.
     */

    public static boolean isAvgWhole(int[] numbers) {
        int sum = 0;

        for (int number : numbers) {
            sum += number;
        }

        return sum % numbers.length == 0;
    }

    /*
            Создайте метод, который берет массив целых чисел и возвращает массив,
        в котором каждое целое число является суммой самого себя + всех предыдущих чисел в массиве.
     */

    public static int[] cumulativeSum(int[] numbers) {
        int[] result = new int[numbers.length];
        int sum = 0;

        for (int i = 0; i < result.length; i++) {
            sum += numbers[i];
            result[i] += sum;
        }

        return result;
    }

    /*
            Создайте функцию, которая возвращает число десятичных знаков, которое имеет число (заданное в виде строки).
        Любые нули после десятичной точки отсчитываются в сторону количества десятичных знаков.
     */

    public static int getDecimalPlaces(String string) {
        String[] splitSides = string.split("\\.");

        if (!string.contains("."))
            return 0;
        return splitSides[1].length();
    }

    /*
            Создайте функцию, которая при заданном числе возвращает соответствующее
        число Фибоначчи.
     */

    public static int Fibonacci(int number) {
        if (number == 0)
            return 0;
        else if (number == 1)
            return 1;
        return Fibonacci(number - 1) + Fibonacci(number - 2);
    }

    /*
            Почтовые индексы состоят из 5 последовательных цифр. Учитывая строку, напишите функцию, чтобы определить,
        является ли вход действительным почтовым индексом. Действительный почтовый
        индекс выглядит следующим образом:
            – Должно содержать только цифры (не допускается использование нецифровых цифр).
            – Не должно содержать никаких пробелов.
            – Длина не должна превышать 5 цифр.
     */

    public static boolean isValid(String string) {
        return string.length() == 5 && string.matches("\\d+");
    }

    /*
        Пара строк образует странную пару, если оба из следующих условий истинны:
            – Первая буква 1-й строки = последняя буква 2-й строки.
            – Последняя буква 1-й строки = первая буква 2-й строки.
            – Создайте функцию, которая возвращает true, если пара строк представляет собой странную пару,
                и false в противном случае.
     */

    public static boolean isStrangePair(String firstString, String secondString) {
        try {
            return ((firstString.charAt(0) == secondString.charAt(secondString.length() - 1)) &&
                    (firstString.charAt(firstString.length() - 1) == secondString.charAt(0)));
        }
        catch (StringIndexOutOfBoundsException exception) {
            return true;
        }
    }

    /*
            Создайте две функции: isPrefix(word, prefix-) и isSuffix (word, -suffix).
                – isPrefix должен возвращать true, если он начинается с префиксного аргумента.
                – isSuffix должен возвращать true, если он заканчивается аргументом суффикса.
                – В противном случае верните false.
     */

    public static boolean isPrefix(String word, String prefix) {
        return word.startsWith(prefix.substring(0, prefix.length() - 1));
    }

    public static boolean isSuffix(String word, String suffix) {
        return word.endsWith(suffix.substring(1));
    }

    public static int boxSeq(int step) {
        if (step == 0) // при 0 = 0
            return 0;
        else if (step == 1) //при 1 = boxSeq(0) + 3
            return boxSeq(0) + 3;
        else if (step == 2)
            return boxSeq(1) - 1; // при 2 = boxSeq(1) - 1
        else if (step % 2 == 0) // если четное и больше > 2 = boxSeq(предыдущ) - 1
            return boxSeq(step - 1) - 1 ;

        return boxSeq(step - 1) + 3; // если нечетное и больше > 2 = boxSeq(предыдущ) + 3
    }
}

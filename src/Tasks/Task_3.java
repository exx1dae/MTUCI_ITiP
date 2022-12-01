package Tasks;

import java.util.HashSet;

public class Task_3 {
    public static void main(String[] args) {
        System.out.println("--------------");
        System.out.println("Task 3_1: " + solutions(196, 28, 1));
        System.out.println("--------------");
        System.out.println("Task 3_2: " + findZip("all zip files are zipped"));
        System.out.println("--------------");
        System.out.println("Task 3_3: " + checkPerfect(28));
        System.out.println("--------------");
        System.out.println("Task 3_4: " + flipEndChars("mouse"));
        System.out.println("--------------");
        System.out.println("Task 3_5: " + isValidHexCode("#CD5C5C"));
        System.out.println("--------------");
        System.out.println("Task 3_6: " + same(new int[] {5, 3, 4, 4, 4}, new int[] {1, 2, 3, 2, 2}));
        System.out.println("--------------");
        System.out.println("Task 3_7: " + isKaprekar(297));
        System.out.println("--------------");
        System.out.println("Task 3_8: " + longestZero("1010010"));
        System.out.println("--------------");
        System.out.println("Task 3_9: " + nextPrime(0));
        System.out.println("--------------");
        System.out.println("Task 3_10: " + rightTriangle(3, 4, 5));
        System.out.println("--------------");
    }

    /*
        Квадратное уравнение ax2 + bx + c = 0 имеет либо 0, либо 1,либо 2 различных
    решения для действительных значений x. учитывая a, b и c, вы должны вернуть
    число решений в уравнение.
    */
    public static int solutions(int a, int b, int c) {
        int D = b * b - 4 * a * c;
        if (D > 0) {
            return 2;
        } else if (D == 0) {
            return 1;
        } else
            return 0;
    }

    /*
        Напишите функцию, которая возвращает позицию второго вхождения " zip " в
    строку, или -1, если оно не происходит по крайней мере дважды. Ваш код должен
    быть достаточно общим, чтобы передать все возможные случаи, когда "zip" может
    произойти в строке.
     */

    public static int findZip(String string) {
        int count = 0;
        int result = 0;
        char[] charArray = string.toCharArray();

        for (int i = 0; i < charArray.length - 2; i++) {
            if (charArray[i] == 'z' && charArray[i + 1] == 'i' && charArray[i + 2] == 'p') {
                count += 1;
                if (count == 2)
                    result = i;
                else if (count < 2)
                    result = -1;
            }
        }
        return result;
    }


    /*
        Создайте функцию, которая проверяет, является ли целое число совершенным
    числом или нет. Совершенное число - это число, которое можно записать как
    сумму его множителей, исключая само число.
     */
    public static boolean checkPerfect(int num) {
        int sum = 0;
        for (int i = 1; i < num; i++) {
            if (num % i == 0)
                sum += i;
        }
        return sum == num;
    }

/*
        Создайте функцию, которая принимает строку и возвращает новую строку
    с заменой ее первого и последнего символов, за исключением трех условий:
    – Если длина строки меньше двух, верните "несовместимо".".
    – Если первый и последний символы совпадают, верните "два-это пара.".
 */

    public static String flipEndChars(String string) {
        char[] charArray = string.toCharArray();
        if (charArray.length < 2)
            return "Incompatible.";
        else if (charArray[0] == charArray[charArray.length - 1]) {
            return "Two's a pair.";
        }
        char firstElement = charArray[charArray.length - 1];
        charArray[charArray.length - 1] = charArray[0];
        charArray[0] = firstElement;
        return String.valueOf(charArray);
    }


/*
    Создайте функцию, которая определяет, является ли строка допустимым
шестнадцатеричным кодом.
Шестнадцатеричный код должен начинаться с фунтового ключа # и иметь длину ровно 6
символов. Каждый символ должен быть цифрой от 0-9 или буквенным символом от A-F.
все буквенные символы могут быть прописными или строчными.
 */

    public static boolean isValidHexCode(String string) {
        return (string.length() == 7 && string.matches("^#[a-fA-F[0-9]]+"));
    }


/*
    Напишите функцию, которая возвращает true, если два массива имеют одинаковое
количество уникальных элементов, и false в противном случае.
 */

    public static boolean same(int[] numOfArray1, int[] numOfArray2) {
        // HashSet - оставляет только уникальные числа
        HashSet<Integer> forArray1 = new HashSet<>();
        HashSet<Integer> forArray2 = new HashSet<>();
        for (int j : numOfArray1) {
            forArray1.add(j);
        }
        for (int j : numOfArray2) {
            forArray2.add(j);
        }
        return forArray1.size() == forArray2.size();
    }


/*
    Число Капрекара-это положительное целое число, которое после возведения в
квадрат и разбиения на две лексикографические части равно сумме
двух полученных новых чисел:
– Если количество цифр квадратного числа четное, то левая и правая части будут
    иметь одинаковую длину.
– Если количество цифр квадратного числа нечетно, то правая часть будет самой длинной
    половиной, а левая-самой маленькой или равной нулю, если количество цифр равно 1.
– Учитывая положительное целое число number, реализуйте функцию, которая возвращает true,
    если это число Капрекара, и false, если это не так.
 */

    public static boolean isKaprekar(int number) {
        String leftSideOfNum = "";
        String rightSideOfNum = "";
        boolean Flag = false;
        String squareOfN = String.valueOf(number * number);


        if (squareOfN.length() == 1) {
            rightSideOfNum = squareOfN;
            if (Integer.parseInt(rightSideOfNum) == number)
                Flag = true;
        }
        else {
            leftSideOfNum = squareOfN.substring(0, squareOfN.length() / 2);
            rightSideOfNum = squareOfN.substring(squareOfN.length() / 2);
            if (Integer.parseInt(leftSideOfNum) + Integer.parseInt(rightSideOfNum) == number)
                Flag = true;
        }
        return Flag;
    }


/*
    Напишите функцию, которая возвращает самую длинную последовательность
последовательных нулей в двоичной строке.
 */

    public static String longestZero(String string) {
        String[] split = string.split("1"); // делим строку (до 1 и после);
        String maxLenght = "";

        for (int i = 0; i < split.length; i++) {
            if (split[i].length() > maxLenght.length())
                maxLenght = split[i];
        }
        if (!(string.contains("0")))
            maxLenght = "";
        return maxLenght;
    }


/*
    Если задано целое число, создайте функцию, которая возвращает следующее
простое число. Если число простое, верните само число.
 */

    public static int nextPrime(int number) {
        int result = 0;
        if (number == 1 || number == 0) {
            return 3;
        }
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                number = number + 1;
                i = 2;
            } else {
                result = number;
            }
        }
        return result;
    }


/*
    Учитывая три числа, x, y и z, определите, являются ли они ребрами
    прямоугольного треугольника.
 */

    public static boolean rightTriangle(int x, int y, int z) {
        if (x > 0 && y > 0 && z > 0)
            return (x * x + y * y == z * z) || (z * z + y * y == x * x) || (z * z + y * y == y * y);
        return false;
    }
}

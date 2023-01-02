package Tasks;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Task_5 {
    public static void main(String[] args) {
        System.out.println("-------------------------------------------");
        System.out.println("Task 5.1 - " + Arrays.toString(enCrypt("Hello")));
        System.out.println("Task 5.1 - " + deCrypt(new int[]{72, 33, -73, 84, -12, -3, 13, -13, -68}));
        System.out.println("-------------------------------------------");
        System.out.println("Task 5.2 - " + canMove("Queen", "C4", "D5"));
        System.out.println("-------------------------------------------");
        System.out.println("Task 5.3 - " + canComplete("butl", "beautiful"));
        System.out.println("-------------------------------------------");
        System.out.println("Task 5.4 - " + sumDigProd(new int[]{1, 2, 3, 4, 5, 6}));
        System.out.println("-------------------------------------------");
        System.out.println("Task 5.5 - " + (sameVowelGroup(new String[]{"many", "carriage", "emit", "apricot", "animal"})));
        System.out.println("-------------------------------------------");
        System.out.println("Task 5.6 - " + validateCard(1234567890123452L));
        System.out.println("-------------------------------------------");
        System.out.println("Task 5.7 - " + numToEng(110));
        System.out.println("-------------------------------------------");
        System.out.println("Task 5.8 - " + getSHA256Hash("password123"));
        System.out.println("-------------------------------------------");
        System.out.println("Task 5.9 - " + correctTitle("jOn SnoW, kINg IN thE noRth."));
        System.out.println("-------------------------------------------");
        System.out.println("Task 5.10 - \n\n" + hexLattice(19));
        System.out.println("-------------------------------------------");
    }

    public static int[] enCrypt(String string) {
        int[] result = new int[string.length()];
        for (int i = 0; i < string.length(); i++) {
            if (i == 0)
                result[i] = string.charAt(i);
            else
                result[i] = string.charAt(i) - string.charAt(i - 1);
        }
        return result;
    }

    public static String deCrypt(int[] array) {
        StringBuilder string = new StringBuilder();

        for (int i = 0; i < array.length; i++) {
            if (i == 0)
                string.append((char) array[i]);
            else {
                array[i] = array[i - 1] + array[i];
                string.append((char) array[i]);
            }
        }
        return string.toString();
    }

    public static boolean canMove(String figure, String startPos, String targetPos) {
        String letters = "ABCDEFGH";
        String digits = "12345678";


        switch (figure) {
            case "Queen":
                if (startPos.charAt(1) == targetPos.charAt(1) || startPos.charAt(0) == targetPos.charAt(0))
                    return true;
                if (Math.abs(letters.indexOf(targetPos.charAt(0)) - letters.indexOf(startPos.charAt(0))) ==
                        Math.abs(digits.indexOf(targetPos.charAt(1)) - digits.indexOf(startPos.charAt(1))))
                    return true;

            case "King":
                return (Math.abs(letters.indexOf(targetPos.charAt(0)) - letters.indexOf(startPos.charAt(0))) < 2) &&
                        (Math.abs(digits.indexOf(targetPos.charAt(1)) - digits.indexOf(startPos.charAt(1))) < 2);

            case "Bishop":
                return Math.abs(letters.indexOf(targetPos.charAt(0)) - letters.indexOf(startPos.charAt(0))) ==
                        Math.abs(digits.indexOf(targetPos.charAt(1)) - digits.indexOf(startPos.charAt(1)));

            case "Knight":
                return Math.abs(letters.indexOf(targetPos.charAt(0)) - letters.indexOf(startPos.charAt(0))) == 1 &&
                        Math.abs(digits.indexOf(targetPos.charAt(1)) - digits.indexOf(startPos.charAt(1))) == 2;

            case "Pawn":
                return digits.indexOf(targetPos.charAt(1)) - digits.indexOf(startPos.charAt(1)) < 3 &&
                        letters.indexOf(targetPos.charAt(0)) - letters.indexOf(startPos.charAt(0)) == 0;

            case "Rook":
                return startPos.charAt(1) == targetPos.charAt(1) || startPos.charAt(0) == targetPos.charAt(0);

        }
        return false;
    }

    static boolean canComplete(String word1, String word2) {
        char[] lettersWord1 = word1.toCharArray();
        char[] lettersWord2 = word2.toCharArray();
        int count = 0;

        for (int i = 0; i < lettersWord1.length; i++) {
            for (int j = 0; j < lettersWord2.length; j++) {
                if (lettersWord2[j] == lettersWord1[i]) {
                    count++;
                    break;
                }
            }
        }
        return count == lettersWord1.length;
    }

    static int sumDigProd(int[] inputNumbers) {
        int[] sum = String.valueOf(Arrays.stream(inputNumbers).sum()).chars().map(Character::getNumericValue).toArray();

        while (sum.length != 1)
            for (int i = 0; i < sum.length - 1; i++)
                sum = String.valueOf(sum[i] * sum[i + 1])
                        .chars().map(Character::getNumericValue).toArray();
        return sum[0];
    }

    public static List<String> sameVowelGroup(String[] words) {
        List<String> result = new ArrayList<>();
        HashSet<Character> vowelsOfFirstWord = new HashSet<>();
        HashSet<Character> temp = new HashSet<>();
        HashSet<Character> allVowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

        char[] firstWord = words[0].toCharArray();


        for (char letter : firstWord) { // проходим по буквам 1 - го слова
            if (allVowels.contains(letter)) { // если буква является элементов всех гласных,
                // то добавляем ее в множество гласных 1 - го слова
                vowelsOfFirstWord.add(letter);
            }
        }

        for (String word : words) { // проходимся по словам массива
            for (char letter : word.toCharArray()) { // проходимся по буквам слова
                if (vowelsOfFirstWord.contains(letter))  // если буква является элементом множества гласных 1 - го слова
                    temp.add(letter); // добавляем эту гласную в наше временное множество
                else if (allVowels.contains(letter)) {  // иначе если принадлежит всем гласным
                    temp.clear(); // очищаем наше множество
                    break; // брейкуем
                }
            }
            if (temp.size() == vowelsOfFirstWord.size()) // если наши множества равны
                result.add(word); // добавляем в наш arrayList слово
            temp.clear(); // очищаем временное множество
        }
        return result;
    }

    public static boolean validateCard(long number) {
        int[] digits = String.valueOf(number).chars().map(Character::getNumericValue).toArray();// число в цифры
        List<Integer> digitsSet = new ArrayList<>();

        for (int digit : digits) { // добавим наши цифры в лист
            digitsSet.add(digit);
        }

        int checkDigit = digitsSet.get(digitsSet.size() - 1); // инициализируем наше число проверки

        Collections.reverse(digitsSet); // "перевернем" цифры

        for (int i = 0; i < digitsSet.size(); i++) {
            if (i % 2 == 0) {
                digitsSet.set(i, digitsSet.get(i) * 2); // цифры на нечетных позициях удвоим
            } else if (String.valueOf(digitsSet.get(i) * 2).length() > 1) // если число на позиции не однозначное
                digitsSet.set(i, String.valueOf(digitsSet).chars().sum()); // на позицию i запишем сумму цифр числа
        }

        int sum = digitsSet.stream().reduce(0, Integer::sum); // просуммируем наш лист

        return (10 - sum % 10) == checkDigit; // на вывод проверку условия с числом проверки
    }

    public static String numToEng(int number) {
        String result;
        List<String> fromZeroToNineteen = new ArrayList<>(Arrays.asList(
                " zero", " one", " two", " three", " four", " five", " six", " seven",
                " eight", " nine", " ten", " eleven", " twelve", " thirteen", " fourteen",
                " fifteen", " sixteen", " seventeen", " eighteen", " nineteen"));
        List<String> fromTwentyToNinety = new ArrayList<>(Arrays.asList(
                "", " ten", " twenty", " thirty", " forty", " fifty", " sixty",
                " seventy", " eighty", " ninety"));

        if (number % 100 < 20) { // 225 % 100 = 25 > 20 || 19 % 100 = 19 < 20
            result = fromZeroToNineteen.get(number % 100); // "nineteen"
            number = number / 100; // 19 / 100 = 0
        } else {
            result = fromZeroToNineteen.get(number % 10); // получаем единицы числа 120 % 10 = 0
            if (number % 10 == 0) {
                number = number / 10; // 120 / 10 = 12

                result = fromTwentyToNinety.get(number % 10); // 12 % 10 = 2
                number = number / 10; // получаем сотые
            } else {
                number = number / 10;

                result = fromTwentyToNinety.get(number % 10) + result; // десятые + единицы в виде строки
                number = number / 10; // 22 / 10 = 2 (для наших сотых)
            }
        }

        if (number == 0)
            return result.replace(" ", "");
        return fromZeroToNineteen.get(number).replace(" ", "") + " hundred" + result;
    }

    /*
        Этот метод принимает входную строку String и возвращает хэш SHA-256 этой строки в виде строки String.
    Стоит отметить, что метод getSHA256Hash выбрасывает RuntimeException, если алгоритм "SHA-256" недоступен.
    Это может произойти, если базовая платформа Java не поддерживает этот алгоритм.
     */
    public static String getSHA256Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256"); // создает объект MessageDigest с помощью
            // алгоритма "SHA-256".
            byte[] hash = md.digest(input.getBytes()); // вызывает метод digest у объекта MessageDigest,
            // передавая в качестве аргумента входную строку String в виде массива byte
            StringBuilder sb = new StringBuilder(); // для хранения значения хэша
            for (int i = 0; i < hash.length; i++) { // итерация по массиву и добавления каждого byte в виде 2 - значного
                // шестнадцатеричного числа
                sb.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    static String correctTitle(String inputString) {
        List<String> lowercaseWords = new ArrayList<>(Arrays.asList(
                "and", "the", "of", "in"));
        String[] words = inputString.split(" "); // делим строку на слова
        StringBuilder outputString = new StringBuilder();

        for (String word : words) {
            if (lowercaseWords.contains(word.toLowerCase()))
                outputString.append(word.toLowerCase().concat(" "));
            else
                outputString.append(word.substring(0, 1)
                        .toUpperCase().concat(word.substring(1).toLowerCase()).concat(" "));
        }
        return outputString.toString().trim();
    }

    /*
    Функция сначала проверяет, равно ли n 1. Если это так,
    то он возвращает строку, содержащую один шестиугольник.
    Если n больше 1, функция уменьшает n на 1, а затем входит в цикл,
    который продолжается до тех пор, пока n не станет меньше или равно 0.
    Цикл увеличивает переменную numOfSix на 1 на каждой итерации и вычитает numOfSix * 6 из n.

    После завершения цикла функция проверяет, меньше ли n 0. Если это так,
    то функция возвращает "Недопустимый". В противном случае он создает строковое представление
    решетки с помощью StringBuilder. Решетка выстраивается ряд за рядом,
    начиная с верхних рядов и продвигаясь вниз к нижним. Каждый ряд состоит из ряда шестиугольников,
    между которыми, а также в начале и конце ряда вставлены пробелы,
    необходимые для правильного выравнивания шестиугольников.
    Количество шестиугольников в каждой строке и
    количество пробелов в начале и конце каждой строки вычисляются на основе значения numOfSix.
    Как только все строки будут добавлены в StringBuilder, функция вернет результирующую строку.
     */
    public static String hexLattice(int n) {
        if (n == 1) {
            return " o ";
        } else {
            n -= 1;
            int numOfSix = 1;
            while (n > 0) {
                n -= numOfSix * 6;
                numOfSix += 1;
            }
            if (n < 0) {
                return "Invalid";
            } else {
                StringBuilder result = new StringBuilder();
                int centre = numOfSix * 2 - 1;
                int secCountOfSix = numOfSix;
                int numOfSpace = numOfSix;
                for (int i = 0; i < numOfSix; i++) {
                    result.append(" ".repeat(Math.max(0, numOfSpace)));
                    result.append("o ".repeat(numOfSix));
                    result.append(" ".repeat(Math.max(0, numOfSpace - 1)));
                    result.append("\n");
                    numOfSpace -= 1;
                    numOfSix += 1;
                    if (centre == numOfSix) {
                        break;
                    }
                }

                result.append(" ");
                result.append("o ".repeat(Math.max(0, centre)));
                result.append('\n');

                numOfSix = centre - 1;
                numOfSpace = numOfSpace + 1;
                for (int i = 0; i < secCountOfSix - 1; i++) {
                    result.append(" ".repeat(Math.max(0, numOfSpace)));
                    result.append("o ".repeat(Math.max(0, numOfSix)));
                    result.append(" ".repeat(Math.max(0, numOfSpace - 1)));
                    result.append("\n");
                    numOfSix -= 1;
                    numOfSpace += 1;
                }
                return result.toString();
            }
        }
    }
}
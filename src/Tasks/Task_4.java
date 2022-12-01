package Tasks;

import java.util.HashSet;
import java.util.Set;

public class Task_4 {
    public static void main(String[] args) {
        System.out.println("--------------");
        System.out.println("Task 4_1: " + "\n" +bessie(10, 7, "hello my name is Bessie and this is my essay"));
        System.out.println("--------------");
        System.out.println(split("()()()"));
        System.out.println("--------------");
        System.out.println("Task 4_3_1: " +  toCamelCase("nice_to_meet_you"));
        System.out.println("Task 4_3_2: " +  toSnakeCase("NiceToMeetYou"));
        System.out.println("--------------");
        System.out.println("Task 4_4: " +  overTime(9, 18, 40, 1.5));
        System.out.println("--------------");
        System.out.println("Task 4_5: " +  BMI("154 pounds", "2 metres"));
        System.out.println("--------------");
        System.out.println("Task 4_6: " +  bugger(999));
        System.out.println("--------------");
        System.out.println("Task 4_7: " +  toStarShortHand("77777geff"));
        System.out.println("--------------");
        System.out.println("Task 4_8: " +  doesRhyme("Sam I am!", "Green eggs and HAM."));
        System.out.println("--------------");
        System.out.println("Task 4_9: " +  trouble(1234445, 55566144));
        System.out.println("--------------");
        System.out.println("Task 4_10: " +  countUniqueBooks("AZYWABBCATTTA", 'A'));
        System.out.println("--------------");
    }

    public static String bessie(int ignoredN, int k, String string) {
        String[] str = string.split(" "); // сплитим строку по словам
        StringBuilder stringBuilder = new StringBuilder();
        int length = 0;
        for (String s : str) { // для каждого слова
            length += s.length(); // длина += длина слова
            if (length == k) { // если длина = условию к
                stringBuilder.append(s).append("\n"); // добавляем слово
                length = 0;
            } else if (length > k) { // иначе если больше условия
                stringBuilder.append("\n").append(s).append(" "); // переносим
                length = s.length(); // длина равна длине слова

            } else
                stringBuilder.append(s).append(" "); // иначе добавляем пробел
        }
        return stringBuilder.toString();
    }

    public static char[] split(String str) {
        char[] string = str.toCharArray();
        StringBuilder res = new StringBuilder(); // результат
        StringBuilder tmp = new StringBuilder(); // временная строка
        int count = 0;
        for (char ch : string) { // для каждого элемента в исходной строке
            if (ch == '(') { // если (
                tmp.append(ch); // добавляем его во временное
                count++;
            }
            else if (ch == ')') {
                // добавляем )
                tmp.append(ch);
                count--;
                if (count == 0) {
                    res.append(tmp);
                    tmp.delete(0, tmp.length());
                }
            }
        }
        if (tmp.isEmpty()) {
            res.append(tmp);
        }
        return res.toString().toCharArray();
    }

    public static String toCamelCase(String string) {
        char currentCharacter;
        boolean nextCharacterUpperFlag = false;
        StringBuilder rez = new StringBuilder();

        for (int i = 0; i < string.length(); i++) {
            currentCharacter = string.charAt(i);
            if (currentCharacter == '_')
                nextCharacterUpperFlag = true;
            else if (nextCharacterUpperFlag) {
                rez.append(Character.toUpperCase(currentCharacter));
                nextCharacterUpperFlag = false;
            }
            else
                rez.append(currentCharacter);
        }
        return rez.toString();
    }


    public static String toSnakeCase(String string) {
        char currentCharacter;
        StringBuilder rez = new StringBuilder();

        for (int i = 0; i < string.length(); i++) {
            currentCharacter = string.charAt(i);
            if (Character.isUpperCase(currentCharacter))
                rez.append("_").append(Character.toLowerCase(currentCharacter));
            else
                rez.append(currentCharacter);
        }
        return rez.toString();
    }

    public static String overTime(double start, double finish, double hourPay, double coefficient) {
        double rez;
        double overTimeCash;
        double normalCash;
        if (finish > 17) {
            overTimeCash = (finish - 17) * hourPay * coefficient;
            normalCash = (17 - start) * hourPay;
            rez = overTimeCash + normalCash;
        } else
            rez = (finish - start) * hourPay;
        return "$".concat(String.format("%.2f", rez));
    }

    public static String BMI(String weight, String height) {
        double weight_double = Double.parseDouble(weight.substring(0, weight.indexOf(" ")));
        double height_double = Double.parseDouble(height.substring(0, height.indexOf(" ")));
        double rez;
        String result;
        if (weight.substring(weight.indexOf(' ') + 1).equalsIgnoreCase("pounds")) {
            weight_double = weight_double * 0.45359237;
        }
        if (height.substring(height.indexOf(' ') + 1).equalsIgnoreCase("inches")) {
            height_double = height_double * 0.0254;
        }
        rez = weight_double / (height_double * height_double);
        result = String.format("%.1f", rez);
        if (rez < 18.5) {
            result = (result).concat(" ").concat("Overweight");
        } else if (rez >= 18.5 && rez <= 24.9) {
            result = (result).concat(" ").concat("Normal weight");
        } else if (rez > 25) {
            result = result.concat(" ").concat("Underweight");
        }
        return result;
    }

    public static int bugger(int num) {
        String[] array = String.valueOf(num).split("");
        int multiplier = 1;
        int count = 0;
        while (array.length != 1) {
            for (String s : array) {
                multiplier *= Integer.parseInt(s);
            }
            array = String.valueOf(multiplier).split("");
            multiplier = 1;
            count++;
        }
        return count;
    }

    public static String toStarShortHand(String str) {
        int count = 1;
        StringBuilder result = new StringBuilder();
        char[] array = str.toCharArray();
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (j == array.length - 1 && array[j] == array[i]) {
                    result.append(array[i]).append("*").append(count + 1);
                }
                if (j == array.length - 1 && array[j] != array[i]) {
                    result.append(array[i]);
                    result.append(array[j]);
                }
                else {
                    if (array[j] == array[i]) {
                        count += 1;
                    } else {
                        if (count == 1) {
                            result.append(array[i]);
                        } else {
                            result.append(array[i]).append("*").append(count);
                            count = 1;
                        }
                    }
                    break;
                }
            }
        }
        return result.toString();
    }

    public static boolean doesRhyme(String str1, String str2) {

        /*
            В хэшсетах будем хранить наши гласные в последнем слове
         */
        HashSet <Character> vowel1 = new HashSet<>();
        HashSet <Character> vowel2 = new HashSet<>();


        /*
            Очищаем нашу строку от знаков
         */
        String string1 = str1.replaceAll("\\W", " ");
        String string2 = str2.replaceAll("\\W", " ");

        /*
            Сплитим строку по словам
         */
        String[] words1 = string1.split(" ");
        String[] words2 = string2.split(" ");

        /*
            Получаем последнее слово и представляем его в виде массива
         */
        String lastWord1 = words1[words1.length - 1];
        char[] lastWordChar1 = lastWord1.toCharArray();

        String lastWord2 = words2[words2.length - 1];
        char[] lastWordChar2 = lastWord2.toCharArray();

        /*
            Проходимся по массиву и добавляем гласные в наш хэшсет
         */
        for (char ch : lastWordChar1) {
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' || ch == 'A' || ch == 'E' ||
                    ch == 'I' || ch == 'O' || ch == 'U') {
                vowel1.add(ch);
            }
        }

        for (char ch : lastWordChar2) {
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' || ch == 'A' || ch == 'E' ||
                    ch == 'I' || ch == 'O' || ch == 'U') {
                vowel2.add(ch);
            }
        }

        /*
            сравниваем с помощью equals.ignoreCase которому плевать на регистр
         */
        return vowel1.toString().equalsIgnoreCase(vowel2.toString());
    }

    public static boolean trouble(int num1, int num2) {
        int[] array1 = String.valueOf(num1).chars().map(Character::getNumericValue).toArray();
        int[] array2 = String.valueOf(num2).chars().map(Character::getNumericValue).toArray();

        int count = 0;
        boolean Flag = false;
        int targetNumber = 0;

        for (int i = 0; i < array1.length - 1; i++) {
            if (array1[i + 1] == array1[i]) {
                count++;
                if (count == 2) {
                    targetNumber = array1[i];
                    break;
                }
            }
            else {
                count = 0;
            }
        }

        for (int i = 0; i < array2.length - 1; i++) {
            if ((array2[i+1] == array2[i]) && (array2[i] == targetNumber)) {
                count++;
                if (count == 1) {
                    Flag = true;
                    break;
                }
            }
            else {
                count = 0;
            }
        }
        return Flag;
    }

    public static int countUniqueBooks(String stringSequence, char bookEnd){
        Set<Character> books = new HashSet<>();
        char[] mayBeBooks = stringSequence.toCharArray();
        int flag = 0;
        Set<Character> temp = new HashSet<>();
        for (char mayBeBook : mayBeBooks) {
            if (mayBeBook == bookEnd) {
                flag += 1;
            }
            if (bookEnd != mayBeBook && flag == 1) {
                temp.add(mayBeBook);
            }
            if (flag == 2) {
                books.addAll(temp);
                temp.clear();
                flag = 0;
            }
        }
        return books.size();
    }
}

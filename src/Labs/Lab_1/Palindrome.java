package Labs.Lab_1;

public class Palindrome {
    public static void main(String[] args) {
        for (String arg : args) {
            System.out.print(arg + ":" + isPalindrome(arg) + " ");
        }
    }

    public static String reverseString(String s) {
        StringBuilder reverseString = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            reverseString.append(s.charAt(s.length() - 1 - i));
        }
        return reverseString.toString();
    }

    public static boolean isPalindrome(String s) {
        return s.equals(reverseString(s));
    }
}


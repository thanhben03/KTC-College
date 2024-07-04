package Bai1;

import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        String s = "defied";
        Predicate<String> isPalindrome = isPalindrome();

        System.out.println(s + " is a palindrome? " + isPalindrome.test(s));
    }

    public static Predicate<String> isPalindrome() {

        return (s1) -> {
            int length = s1.length();
            for (int i = 0; i < length/2; i++) {
                if (s1.charAt(i) != s1.charAt(length - i - 1)) {
                    return false;
                }
            }

            return true;
        };
    }
}

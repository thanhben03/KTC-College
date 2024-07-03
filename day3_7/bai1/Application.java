package bai1;

import bai1.interfaces.ICalculator;

import java.util.function.BiFunction;

public class Application {
    public static void main(String[] args) {
        ICalculator calculator = (a) -> {
            int max = 0;
            // Neu no la so nguyen to thi return ve chinh no
            if (isSoNguyenTo(a))
                return a;
            for (int i = 1; i <= a; i++) {
                // Neu i vua la uoc vua la so nguyen to thi gan cho max
                if (a % i == 0 && isSoNguyenTo(i)) {
                    max = i;
                }
            }
            return max;
        };


        int b = calculator.findLargestFactor(23);

        System.out.println("Largest prime factor: " + b);
    }

    public static boolean isSoNguyenTo(int n) {
        if (n < 2) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }


}

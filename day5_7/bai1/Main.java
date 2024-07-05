package bai1;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        List<Integer> a = Arrays.asList(1,2,3,4,5,6,7,8,9,10,12,14);

        int sumOfEven = a.stream()
                .filter(integer -> integer % 2 == 0)
                .reduce(Integer::sum)
                .get();

        int sumOfOdd = a.stream()
                .filter(integer -> integer % 2 != 0)
                .reduce(Integer::sum)
                .get();

        System.out.println(sumOfEven);
        System.out.println(sumOfOdd);
    }
}

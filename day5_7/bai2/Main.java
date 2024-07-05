package bai2;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> a = Arrays.asList(10,23,22,23,24,24,33,15,26,15);

        // Cach 1
        List<Integer> a1 = a.stream().distinct().toList();

        // Cach 2
        List<Integer> b = new ArrayList<>();
        a.forEach(integer -> {
            if (!b.contains(integer)) {
                b.add(integer);
            }
        });

        for (Integer c : b) {
            System.out.print(c + " ");
        }
    }
}

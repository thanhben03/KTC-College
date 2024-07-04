package bai2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        List<Integer> a = new ArrayList<>();
        a.add(25);
        a.add(1);
        a.add(300);
        a.add(101);
        a.add(77);
        a.add(18);
        a.add(7);
        test(a);
        
    }

    public static void test(List<Integer> b) {
        Consumer<List<Integer>> tool = (arr) -> {
            List<Integer> sorted = arr.stream().sorted().toList();
            System.out.println(sorted.get(sorted.size() - 2));
            System.out.println(sorted.get(0));
        };

        tool.accept(b);
    }
}

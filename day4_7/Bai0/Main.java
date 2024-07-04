package Bai0;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        List<Double> a = new ArrayList<>();
        a.add(1.2);
        a.add(0.0);
        a.add(1.4);

        Function<List<Double>,Double> convert = (arr) -> {

            return arr.stream()
                    .mapToDouble(Double::valueOf)
                    .average()
                    .orElse(0);
        };

        System.out.println(convert.apply(a));
    }
}

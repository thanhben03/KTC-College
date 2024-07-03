package bai2.services;

public class BinaryService {
    public static StringBuilder convertToBinary(int a) {
        StringBuilder temp = new StringBuilder();
        while (a / 2 != 0) {
            temp.append(a % 2);
            a /= 2;

        }
        temp.append(a);
        return temp;
    }
}

package bai2;

import bai2.interfaces.IConvertBinary;
import bai2.services.BinaryService;

public class Application {
    public static void main(String[] args) {
        int a = 34;

        StringBuilder result = new StringBuilder();
        result = check(a, BinaryService::convertToBinary);

        System.out.println(result);
    }

    public static StringBuilder check (int a, IConvertBinary convert) {
        return convert.convertToBinary(a);
    }
}

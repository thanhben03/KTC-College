package bai1;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {19, 17, 15, 12, 16, 18, 4, 11, 13};
        System.out.println(Arrays.toString(arr));

        // left và right là phạm vi duyệt từ trái qua phải
        int left = 0;
        int right = arr.length - 1;

        quickSort(arr, left, right);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int pa = partition(arr, left, right);

            // đệ quy
            quickSort(arr, left, pa - 1);
            quickSort(arr, pa + 1, right);
        }
    }

    private static int partition(int[] arr, int left, int right) {
        // lấy phần tử cuối cùng làm chốt (pivot)
        int pivot = arr[right];
        int pv = left - 1;
        for (int i = left; i < right; i++) {
            // kiểm tra xem nếu phần tử thứ i < chốt
            // hoán vị arr[i] cho arr[pv]
            if (arr[i] <= pivot) {
                pv++;
                int temp = arr[pv];
                arr[pv] = arr[i];
                arr[i] = temp;
            }
        }
        // đưa chốt vào giữa mảng
        int temp = arr[pv + 1];
        arr[pv+1] = arr[right];
        arr[right] = temp;

        // trả về vị trí của chốt
        return pv + 1;
    }
}

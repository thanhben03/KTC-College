package bai1;

import java.util.LinkedList;

public class Application {
    public static void main(String[] args) {
        ListNode list6 = new ListNode(6);
        ListNode list5 = new ListNode(5, list6);

        ListNode list4 = new ListNode(4, list5);
        ListNode list3 = new ListNode(3, list4);
        ListNode list2 = new ListNode(2, list3);
        ListNode list = new ListNode(1, list2);
        int size = getSize(list);
        ListNode result = getMiddleValueNode(size, list);

        System.out.println(result.val);
    }

    public static ListNode getMiddleValueNode(int size, ListNode node) {
        ListNode temp = node;
        for (int i = 0 ; i < size/2; i++) {
            temp = temp.next;
        }
        return temp;
    }

    public static int getSize(ListNode list) {
        int size = 0;
        ListNode temp = list;
        while (temp != null) {
            size++;
            temp = temp.next;
        }

        return size;
    }
}

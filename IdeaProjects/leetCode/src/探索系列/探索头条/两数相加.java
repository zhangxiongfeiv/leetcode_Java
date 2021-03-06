package 探索系列.探索头条;

import java.util.Set;

public class 两数相加 {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode newHead = new ListNode(-1);
        int carry = 0;

        ListNode curNode = newHead;
        while (l1 != null || l2 != null){
            int v1 = l1 == null ? 0 : l1.val;
            int v2 = l2 == null ? 0 : l2.val;
            l1 = l1 == null ? l1 : l1.next;
            l2 = l2 == null ? l2 : l2.next;

            int sum = v1 + v2 + carry;
            carry = 0;
            if (sum >= 10) {
                sum -= 10;
                carry = 1;
            }

            curNode.next = new ListNode(sum);
            curNode = curNode.next;
        }
        if (carry > 0){
            curNode.next = new ListNode(carry);
            curNode = curNode.next;
        }

        return newHead.next;
    }

    public static void main(String[] args) {

        ListNode n1 = new ListNode(9);
        n1.next = new ListNode(8);

        ListNode n2 = new ListNode(1);

        addTwoNumbers(n1, n2);
    }

}

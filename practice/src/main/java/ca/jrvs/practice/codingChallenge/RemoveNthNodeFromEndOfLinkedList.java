package ca.jrvs.practice.codingChallenge;

public class RemoveNthNodeFromEndOfLinkedList {
    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode left = dummy;
        ListNode rigth = head;

        //create gap between left and rigth
        while (n > 0) {
            rigth = rigth.next;
            n--;
        }

        while (rigth != null) {
            left = left.next;
            rigth = rigth.next;
        }

        left.next = left.next.next;
        return dummy.next;
    }
}

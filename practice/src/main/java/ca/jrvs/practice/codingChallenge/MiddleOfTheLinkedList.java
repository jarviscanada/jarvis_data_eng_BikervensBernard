package ca.jrvs.practice.codingChallenge;

public class MiddleOfTheLinkedList {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode middleNode(ListNode head){
        ListNode dummy = new ListNode(0,head);
        int l = 0;
        while(head != null ) {
            head = head.next;
            l++;
        }
        int middle = l/2;
        while(middle > 0) {
            dummy = dummy.next;
            middle--;
        }
        return dummy.next;
    }
}

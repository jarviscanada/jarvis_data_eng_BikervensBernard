package ca.jrvs.practice.codingChallenge;

import java.util.HashSet;
import java.util.Set;

public class LinkedListCycle {
    class ListNode { int val;ListNode next;ListNode(int x) {val = x;next = null;}}

    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next==null ) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;

        while(fast != null && fast.next!= null) {
            if (slow == fast) {
                return true;
            }
            else
            {
                slow = slow.next;
                fast = fast.next.next;
            }
        }
        return false;
    }

    public static boolean hasCycle(ListNode head, String empty) {
        Set<ListNode> set = new HashSet<ListNode>();
        while(head!=null) {
            if(set.contains(head))
                return true;
            else
                set.add(head);
            head = head.next;
        }return false;
    }
}

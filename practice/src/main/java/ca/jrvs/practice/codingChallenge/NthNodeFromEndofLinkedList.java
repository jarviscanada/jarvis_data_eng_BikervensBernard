package ca.jrvs.practice.codingChallenge;

import java.util.*;

public class NthNodeFromEndofLinkedList{
    private static Deque<Integer> l = new LinkedList<Integer>();
    /**
     * find the nth Node From End of LinkedList
     * @Big-O: O(n)
     * @Justification: we call method clone() which is the dominating time complexity function
     * @param nth input array is sorted
     * @param list contains elements to be searched
     * @return the value of the nth element in the linkedlist (from the end)
     * @throws EmptyStackException if the nth element is > than the list <br/>i.e [1,2,3] does not have a 19nth element
     */
    public static  <E> Optional<E> nthNodeFromEndofLinkedList(int nth, Deque<E> list) throws EmptyStackException {
        l.addAll((Collection<? extends Integer>) list);
        return nthNodeFromEndofLinkedList(nth);
    }
    private static  <E> Optional<E> nthNodeFromEndofLinkedList(int nth) {
        if (l.isEmpty()) {
            throw new EmptyStackException();
        }
        else {
            l.removeLast();
            if (nth > 1) {
                nthNodeFromEndofLinkedList(nth - 1);
            }
            return (Optional<E>) Optional.of( l.peekLast());
        }
    }

    public static LinkedListCycle.ListNode removeNthFromEnd(LinkedListCycle.ListNode head, int n) {
        LinkedListCycle.ListNode start = new LinkedListCycle.ListNode(0);
        LinkedListCycle.ListNode slow = start, fast = start;
        slow.next = head;

        //Move fast in front so that the gap between slow and fast becomes n
        for(int i=1; i<=n+1; i++)   {
            fast = fast.next;//
        }
        //Move fast to the end, maintaining the gap
        while(fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        //Skip the desired node
        slow.next = slow.next.next;
        return start.next;
    }

    public static void main(String[] args) {
        LinkedListCycle.ListNode a = new LinkedListCycle.ListNode(1);
        LinkedListCycle.ListNode a2 = new LinkedListCycle.ListNode(2);
        LinkedListCycle.ListNode a3 = new LinkedListCycle.ListNode(3);
        LinkedListCycle.ListNode a4 = new LinkedListCycle.ListNode(4);
        LinkedListCycle.ListNode a5 = new LinkedListCycle.ListNode(5);
        a4.next = a5;
        a3.next = a4;
        a2.next = a3;
        a.next = a2;
        NthNodeFromEndofLinkedList.removeNthFromEnd(a,2);
    }
}

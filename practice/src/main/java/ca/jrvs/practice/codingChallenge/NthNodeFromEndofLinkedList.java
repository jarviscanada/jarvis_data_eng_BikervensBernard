package ca.jrvs.practice.codingChallenge;

import java.util.*;

public class NthNodeFromEndofLinkedList{
    private static Deque<Integer> l = new LinkedList<Integer>();
    /**
     * find the nth Node From End of LinkedList
     * @Big-O: O(n)
     * @Justification: we create a stack containing element of list by iteration
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
}

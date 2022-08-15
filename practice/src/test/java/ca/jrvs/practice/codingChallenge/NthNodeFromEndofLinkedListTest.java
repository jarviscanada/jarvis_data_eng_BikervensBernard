package ca.jrvs.practice.codingChallenge;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NthNodeFromEndofLinkedListTest{

    Deque<Integer> l;

    @Before
    public void setUp() {l = new LinkedList(Arrays.asList(1,2,3,4,5,6,7,8,9,10));}

    @Test
    public void nthNodeFromEndofLinkedList_valid() {
        assertEquals(Optional.of(10 - 3).get(),new NthNodeFromEndofLinkedList().nthNodeFromEndofLinkedList(3,l).get());
        for (int i = 0 ; i < l.size();i++) {
            assertTrue((i+1) == l.pop());
        }
    }

    @Test
    public void nthNodeFromEndofLinkedList_invalid() {
        try {
            new NthNodeFromEndofLinkedList(). nthNodeFromEndofLinkedList(30,l).get();
        }
        catch (EmptyStackException e) {
            assertTrue(true);
        }
    }
}
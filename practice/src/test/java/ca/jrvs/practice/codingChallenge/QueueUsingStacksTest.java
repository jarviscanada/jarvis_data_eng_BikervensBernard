package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class QueueUsingStacksTest{

    @Test
    public void push() {
        QueueUsingStacks q = new QueueUsingStacks();
        assertTrue(q.empty());
        q.push(1);
        assertFalse(q.empty());
        assertEquals(1,q.size());
    }

    @Test
    public void testPush() {
        QueueUsingStacks q = new QueueUsingStacks();
        assertTrue(q.empty());
        q.push(1,"2 stack method");
        assertFalse(q.empty());
        assertEquals(1,q.size());
    }

    @Test
    public void pop() {
        QueueUsingStacks q = new QueueUsingStacks();
        assertTrue(q.empty());
        q.push(1);
        q.push(2);
        assertFalse(q.empty());
        assertEquals(2,q.size());
        assertEquals(1,q.pop());
        assertEquals(1,q.size());

    }

    @Test
    public void testPop() {
        QueueUsingStacks q = new QueueUsingStacks();
        assertTrue(q.empty());
        q.push(1,"2 stack method");
        q.push(2,"2 stack method");
        assertFalse(q.empty());
        assertEquals(2,q.size());
        assertEquals(1,q.pop("2 stack method"));
        assertEquals(1,q.size());
    }

    @Test
    public void peek() {
        QueueUsingStacks q = new QueueUsingStacks();
        assertTrue(q.empty());
        q.push(1);
        q.push(2);
        assertFalse(q.empty());
        assertEquals(2,q.size());
        assertEquals(1,q.peek());
        assertEquals(2,q.size());

    }

    @Test
    public void testPeek() {
        QueueUsingStacks q = new QueueUsingStacks();
        assertTrue(q.empty());
        q.push(1,"2 stack method");
        q.push(2,"2 stack method");
        assertFalse(q.empty());
        assertEquals(2,q.size());
        assertEquals(1,q.peek("2 stack method"));
        assertEquals(2,q.size());
    }

    @Test
    public void empty() {
        assertTrue(new QueueUsingStacks().empty());
    }

    @Test
    public void testEmpty() {
        assertTrue(new QueueUsingStacks().empty(""));
    }
}
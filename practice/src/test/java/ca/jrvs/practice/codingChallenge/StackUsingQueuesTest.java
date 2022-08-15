package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class StackUsingQueuesTest{

    @Test
    public void push() {
        StackUsingQueues myStack = new StackUsingQueues();
        assertTrue(myStack.empty());
        myStack.push(1);
        myStack.push(2);
        assertFalse(myStack.empty());
        assertEquals(2, myStack.size());
    }

    @Test
    public void pop() {
        StackUsingQueues myStack = new StackUsingQueues();
        assertTrue(myStack.empty());
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        assertEquals(3,myStack.size());
        assertEquals(3,myStack.pop());
        assertEquals(2,myStack.pop());
        assertEquals(1,myStack.size());
    }

    @Test
    public void top() {
        StackUsingQueues myStack = new StackUsingQueues();
        assertTrue(myStack.empty());
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        assertEquals(3,myStack.size());
        assertEquals(3,myStack.top());
        assertEquals(3,myStack.size());
    }

    @Test
    public void empty() {
        StackUsingQueues myStack = new StackUsingQueues();
        assertTrue(myStack.empty());
        myStack.push(1);
        assertFalse(myStack.empty());
    }

    @Test
    public void testPush() {
        StackUsingQueues myStack = new StackUsingQueues();
        assertTrue(myStack.empty());
        myStack.push(1,"2 queue");
        myStack.push(2,"2 queue");
        assertFalse(myStack.empty());
        assertEquals(2, myStack.size());
    }

    @Test
    public void testPop() {
        StackUsingQueues myStack = new StackUsingQueues();
        assertTrue(myStack.empty());
        myStack.push(1,"2 queue");
        myStack.push(2,"2 queue");
        myStack.push(3,"2 queue");
        assertEquals(3,myStack.size());
        assertEquals(3,myStack.pop("2 queue"));
        assertEquals(2,myStack.pop("2 queue"));
        assertEquals(1,myStack.size());
    }
}
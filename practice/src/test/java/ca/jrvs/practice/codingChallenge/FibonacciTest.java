package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class FibonacciTest{

    @Test
    public void fib_valid() {
        assertEquals(1,Fibonacci.fib(1));
        assertEquals(1,Fibonacci.fib(2));
        assertEquals(2,Fibonacci.fib(3));
        assertEquals(3,Fibonacci.fib(4));
    }

    @Test
    public void fib_bottom_up_valid() {
        assertEquals(1,Fibonacci.fib_bottom_up(1));
        assertEquals(1,Fibonacci.fib_bottom_up(2));
        assertEquals(2,Fibonacci.fib_bottom_up(3));
        assertEquals(3,Fibonacci.fib_bottom_up(4));
    }

    @Test
    public void fib_invalid() {
        assertEquals(-1,Fibonacci.fib(-1));
    }

    @Test
    public void fib_bottom_up_invalid() {
        assertEquals(-1,Fibonacci.fib(-1));
    }
}
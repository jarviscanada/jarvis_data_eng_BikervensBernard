package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class MyAtoiTest{

    @Test
    public void myAtoi_valid() {
        assertEquals(42, MyAtoi.myAtoi("42"));
        assertEquals(-42, MyAtoi.myAtoi("   -42"));
        assertEquals(4193, MyAtoi.myAtoi("4193 with words"));

        //fail by design
        assertEquals(0, MyAtoi.myAtoi("with words"));
        assertEquals(0, MyAtoi.myAtoi("words   -42"));
    }
}
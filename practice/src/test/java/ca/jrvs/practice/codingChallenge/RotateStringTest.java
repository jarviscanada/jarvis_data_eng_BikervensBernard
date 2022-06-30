package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class RotateStringTest{

    @Test
    public void rotateString_valid() {
        assertTrue(RotateString.rotateString("abcde","bcdea"));
    }

    @Test
    public void testRotateString_valid() {
        assertTrue(RotateString.rotateString("abcde","bcdea","@Big-O: O(n^2) ->return A.length() == B.length() && (A + A).contains(B)"));
    }

    @Test
    public void rotateString_invalid() {
        assertFalse(RotateString.rotateString("abcde","abced"));
    }

    @Test
    public void testRotateString_invalid() {
        assertFalse(RotateString.rotateString("abcde","abced","@Big-O: O(n^2) ->return A.length() == B.length() && (A + A).contains(B)"));
    }
}
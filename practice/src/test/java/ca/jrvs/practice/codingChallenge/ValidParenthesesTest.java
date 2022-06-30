package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidParenthesesTest{

    @Test
    public void isValid_true() {
        assertTrue(ValidParentheses.isValid("()"));
        assertTrue(ValidParentheses.isValid("()[]{}"));
        assertTrue(ValidParentheses.isValid("{ { } [ ] [ [ [ ] ] ] }"));
    }

    @Test
    public void isValid_false() {
        assertFalse(ValidParentheses.isValid("(]"));
    }
}
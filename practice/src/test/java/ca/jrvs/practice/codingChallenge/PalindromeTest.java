package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class PalindromeTest{

    @Test
    public void isPalindrome_true() {
        assertTrue(Palindrome.isPalindrome("A man, a plan, a canal: Panama"));
        assertTrue(Palindrome.isPalindrome("99"));
        assertTrue(Palindrome.isPalindrome("111"));
        assertTrue(Palindrome.isPalindrome(" "));
    }

    @Test
    public void isPalindrome_false() {
        assertFalse(Palindrome.isPalindrome("A man, a plan, art canal: montreal"));
        assertFalse(Palindrome.isPalindrome("911 hello"));
    }
}
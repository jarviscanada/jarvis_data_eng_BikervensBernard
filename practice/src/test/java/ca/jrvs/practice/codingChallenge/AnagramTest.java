package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class AnagramTest{

    @Test
    public void isAnagram_true() {
        assertTrue(Anagram.isAnagram("anagram","nagaram"));
    }

    @Test
    public void isAnagram_false() {
        assertFalse(Anagram.isAnagram("rat","car"));
    }
}
package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class TwoSumTest{

    @Test
    public void twoSum_valid() {
        assertArrayEquals(new int[] {0,3}, TwoSum.twoSum(new int[]{8,3,4,1},9));
    }
    @Test
    public void twoSum_invalid() {
        assertArrayEquals(null, TwoSum.twoSum(new int[]{3,3,4,1},9));
    }

    @Test
    public void TwoSumBigO_N_valid() {
        assertArrayEquals(new int[] {0,3}, TwoSum.twoSum(new int[]{8,3,4,1},9,0));
    }
    @Test
    public void TwoSumBigO_N_invalid() {
        assertArrayEquals(null,  TwoSum.twoSum(new int[]{1,3,4,1},9,0));
    }
}
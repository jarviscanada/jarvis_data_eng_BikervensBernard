package ca.jrvs.practice.sorting;

import org.junit.After;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class MergesortTest{

    private int[] arr = new int[] {6,4,23,14,5,67,7,5,3,2,0};
    private int[] negative = new int[] {-6,-4,-23,-14,-5,-67,0};
    private int[] mix = new int[] {6,4,-23,14,5,67,-7,5,3,-2,0};

    @Test
    public void mergeSort_arr_valid() {
        int[] copy = arr.clone();
        Arrays.sort(copy);
        Mergesort.sort(arr);
        assertArrayEquals(copy,arr);
    }

    @Test
    public void mergeSort_negative_valid() {
        int[] copy = negative.clone();
        Arrays.sort(copy);
        Mergesort.sort(negative);
        assertArrayEquals(copy,negative);
    }

    @Test
    public void mergeSort_mix_valid() {
        int[] copy = mix.clone();
        Arrays.sort(copy);
        Mergesort.sort(mix);
        assertArrayEquals(copy,mix);
    }

    @After
    public void tearDown() {
        arr = new int[] {6,4,23,14,5,67,7,5,3,2,0};
        negative = new int[] {-6,-4,-23,-14,-5,-67,0};
        mix = new int[] {6,4,-23,14,5,67,-7,5,3,-2,0};
    }
}
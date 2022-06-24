package ca.jrvs.practice.sorting;

import org.junit.After;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class QuicksortTest{

    private int[] arr = new int[] {6,4,23,14,5,67,7,5,3,2,0};
    private int[] negative = new int[] {-6,-4,-23,-14,-5,-67,0};
    private int[] mix = new int[] {6,4,-23,14,5,67,-7,5,3,-2,0};

    @Test
    public void sort_pos_arr_valid() {
        int[] copy = arr.clone();
        Arrays.sort(copy);
        Quicksort.sort(arr, 0, arr.length - 1);
        assertArrayEquals(copy,arr);
    }

    @Test
    public void sort_negative_arr_valid() {
        int[] copy = negative.clone();
        Arrays.sort(copy);
        Quicksort.sort(negative, 0, negative.length - 1);
        assertArrayEquals(copy,negative);
    }

    @Test
    public void sort_mix_arr_valid() {
        int[] copy = mix.clone();
        Arrays.sort(copy);
        Quicksort.sort(mix, 0, mix.length - 1);
        assertArrayEquals(copy,mix);
    }

    @Test
    public void sort_arr_bound1_invalid() {
        int[] copy = arr.clone();
        Arrays.sort(copy);
        try{
            Quicksort.sort(arr, 0, arr.length);
        }catch (IndexOutOfBoundsException e) {
            assertTrue(true);
        }
    }

    @Test
    public void sort_arr_bound2_invalid() {
        int[] copy = arr.clone();
        Arrays.sort(copy);
        try{
            Quicksort.sort(arr, -10, arr.length-1);
        }catch (IndexOutOfBoundsException e) {
            assertTrue(true);
        }
    }

    @After
    public void tearDown() {
       arr = new int[] {6,4,23,14,5,67,7,5,3,2,0};
       negative = new int[] {-6,-4,-23,-14,-5,-67,0};
       mix = new int[] {6,4,-23,14,5,67,-7,5,3,-2,0};
    }
}
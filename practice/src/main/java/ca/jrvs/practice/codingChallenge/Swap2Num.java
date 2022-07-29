package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;

public class Swap2Num {

    public static void swap(int[] arr) {
        arr[0] = arr[0]+arr[1];
        arr[1] = arr[0]-arr[1];
        arr[0] = arr[0] - arr[1];
    }
}

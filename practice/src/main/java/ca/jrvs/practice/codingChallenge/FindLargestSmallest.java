package ca.jrvs.practice.codingChallenge;

import java.util.*;

public class FindLargestSmallest {

    public int findLargestViaLoop(int[] arr) {
        if (arr.length ==0) {return -1;}
        if (arr.length ==1) {return arr[0];}
        int largest = arr[0];
        for (int number: arr) {
            if (number > largest) {
                largest = number;
            }
        }
        return largest;
    }

    public Optional findLargestViaStream(int[] arr) {
        if (arr.length ==0) {return Optional.empty();}
        if (arr.length ==1) {return Optional.of(arr[0]);}
        return Optional.of( Arrays.stream(arr).max());
    }

    public int findLargestViaCollection(int[] arr) {
        if (arr.length ==0) {return -1;}
        if (arr.length ==1) {return arr[0];}
        Arrays.sort(arr);
        return arr[arr.length-1];
    }
}

package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;

public class FindDuplicateNumber {

    public static int find(int[] nums) {
        Arrays.sort(nums);
        for(int i = 1 ; i < nums.length; i++) {
            if (nums[i] == nums[i-1]) {
                return nums[i];
            }
        }
        return -1;
    }
}

package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;
import java.util.Map;

// https://www.notion.so/jarvisdev/Two-Sum-cb7112f7f8e94f5b883dde972235a3fc
// https://leetcode.com/problems/two-sum/
public class TwoSum {

    /**
     * Given an array of integers and an integer target, return indices of the two numbers such that they add up to target.
     * You may assume that each input would have exactly one solution, and you may not use the same element twice.
     * @Big-O: worst O(2n)
     * @Justification: double for loop
     * @param nums arrays of all possible output
     * @param target number we want to sum to
     * @return array containing solution'PrintLetterWithNumber index or null if no solution exist
     */
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0 ; i < nums.length ; i++) {
            for (int j = 0 ; j < nums.length ; j++) {
                if (i != j) {
                    int sum = nums[i]+nums[j];
                    if (sum == target) {
                        return new int[] {i,j};
                    }
                }
            }
        }return null;
    }

    /**
     * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
     * You may assume that each input would have exactly one solution, and you may not use the same element twice.
     * @Big-O: worst O(n)
     * @Justification: single for loop
     * @param numbers arrays of all possible output
     * @param target number we want to sum to
     * @return array containing solution'PrintLetterWithNumber index or null if no solution exist
     */
    public static int[] twoSum(int[] numbers, int target, String bigoOfN) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int[] result = new int[2];
        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(target - numbers[i])) {
                result[1] = i;
                result[0] = map.get(target - numbers[i]);
                return result;
            }
            map.put(numbers[i], i);
        }
        return null;
    }
}

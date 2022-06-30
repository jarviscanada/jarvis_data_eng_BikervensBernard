package ca.jrvs.practice.codingChallenge;

// https://www.notion.so/jarvisdev/Fibonacci-Number-Climbing-Stairs-7feed493a7104a208ef69c31a591c62b
// https://leetcode.com/problems/fibonacci-number/
public class Fibonacci{

    /**
     * The Fibonacci numbers, commonly denoted F(n) form a sequence,
     * called the Fibonacci sequence, such that each number is the sum of the two preceding
     * ones, starting from 0 and 1
     * @Big-O: O(n^2)
     * @Justification: for each number n we have 2 step
     * @param n starting point
     * @return the sum of n-1 + n-2, starting from 0 and 1
     */
    public static int fib(int n){
        if (n <= 1) {
            return n;
        }return fib(n-1)+fib(n-2);
    }

    /**
     * The Fibonacci numbers, commonly denoted F(n) form a sequence,
     * called the Fibonacci sequence, such that each number is the sum of the two preceding
     * ones, starting from 0 and 1
     * @Big-O: O(n)
     * @Justification: an array of n is created and iterated upon
     * @param n starting point
     * @return the sum of n-1 + n-2, starting from 0 and 1
     */
    public static int fib_bottom_up(int n){
        if (n <= 1) {return n;}
        int[] arr = new int[n+1];
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i < arr.length; i++) {
            arr[i] = arr[i-1] + arr[i-2];
        }return arr[n-1];
    }
}

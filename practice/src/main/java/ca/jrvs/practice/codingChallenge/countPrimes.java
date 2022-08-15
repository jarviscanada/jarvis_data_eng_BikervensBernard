package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class countPrimes {
    public static int count(int n) {
        if (n == 0 || n == 1) {return 0;}
        boolean[] x = new boolean[n];//[f,f,f,f,f,f,f,f,f,f]
        for (int i = 0 ; i < n; i++) x[i] = true;

        for (int i = 2 ; i < n; i++) {
            int current = i+1;
            if (x[i] == true) {
                int next = current;
                int j = 1;
                while (next <= n) {
                    x[next-1] = false;
                    next = current*j;
                    j++;
                }
            }
        }
        int y = 0;
        for (boolean b : x) {
            if (b == false) {y++;}
        }return y;
    }
    public static void main(String[] args) {
    }
}

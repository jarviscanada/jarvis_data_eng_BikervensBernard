package ca.jrvs.practice.codingChallenge;

import java.util.*;

public class FindingDuplicatedChar {
    public static Object[] find(String s) {
        Set<Character> set = new HashSet<>();
        s = s.toLowerCase().replaceAll("\\s+","");
        ArrayList<Character> duplicate = new ArrayList<>();
        for( char c : s.toCharArray()) {
           if (set.contains(c))
               duplicate.add(c) ;
           else set.add(c);
        }
        set = new HashSet<>();
        for (Character c: duplicate) set.add(c);
        return set.toArray();
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString( FindingDuplicatedChar.find("A black cat died on a road")));
    }
}

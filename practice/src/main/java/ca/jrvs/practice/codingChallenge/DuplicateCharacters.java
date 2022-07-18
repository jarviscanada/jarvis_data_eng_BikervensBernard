package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;

public class DuplicateCharacters {
    public void find(String s) {
        char[] c = s.toLowerCase().replace(" ","").toCharArray();
        HashMap<Character,Integer> map = new HashMap<>();
        for (char ch: c) {
            map.put(ch,map.getOrDefault(ch,0)+1);
        }
    }
}

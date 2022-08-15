package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;

// https://www.notion.so/jarvisdev/Valid-Anagram-738a4bd6c455489d80bf309e0b3364ea
public class Anagram {

    /**
     * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
     * typically using all the original letters exactly once.
     *
     * @Big-O: O(n)
     * @Justification: double for loop
     * @param s arrays of all possible output
     * @param t number we want to sum to
     * @return return true if t is an anagram of PrintLetterWithNumber, and false otherwise.
     */
    public static boolean isAnagram(String s, String t) {
        HashMap<Character,Integer> smap=new HashMap<>();
        int sl=s.length();
        int tl=t.length();
        if(sl!=tl){return false;}
        for(int i=0;i<sl;i++){
            smap.put(s.charAt(i),smap.getOrDefault(s.charAt(i),0)+1);
            smap.put(t.charAt(i),smap.getOrDefault(t.charAt(i),0)-1);
        }
        for(char c:smap.keySet()){
            if(smap.get(c)!=0){return false;}
        }
        return true;
    }
}

package ca.jrvs.practice.codingChallenge;

import java.util.Iterator;
import java.util.Map;

// https://www.notion.so/jarvisdev/How-to-compare-two-maps-c1ebe6ec504e446aa13c4b3099c0f4d
public class CompareMaps {

    /**
     * Compares the specified objects for equality.
     * Returns true if the 2 given object is also a map and the two maps represent the same mappings.
     * More formally, two maps m1 and m2 represent the same mappings if m1.entrySet().equals(m2.entrySet()).
     * This ensures that the equals method works properly across different implementations of the Map interface.
     * @Big-O: worst O(n)
     * @Big-O: average O(1)
     * @Justification: direct access to each entry but possible look up to the linkedlist in bucket
     * @param m1 Map to be compared for equality
     * @param m2 Map to be compared for equality
     * @return true if the specified object is equal to this map
     */
    public static <K,V> boolean compareMaps(Map<K,V> m1, Map<K,V> m2){
        boolean same = true;

        if (m1 == null && m2 == null) {return same;}
        if (m1 == null && m2 != null) {return false;}
        if (m1 != null && m2 == null) {return false;}
        if (m1.size() != m2.size()) {return false;}
        if (m1.isEmpty() && m2.isEmpty()) {return same;}

        Iterator<Map.Entry<K, V>> iterator = m2.entrySet().iterator();
        for (Map.Entry<K, V> entry: m1.entrySet()) {
            if (!entry.equals(iterator.next())) {
                same = false;
            }
        }
        if (!m1.entrySet().equals(m2.entrySet())) {return false;}
        return same;
    }
}

package ca.jrvs.practice.search;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class BinarySearchTest{
    private final static Integer[] arrPositive = new Integer[] {1,2,3,4,5,6,7,8,9,10};
    private final static Integer[] arrNegative = new Integer[] {-3,-2,-1,5,6,7,8,10,11};
    private final static Integer[] arrEmpty = new Integer[] {};
    private final static int target = 5;
    private final static BinarySearch bs = new BinarySearch();

    @Test
    public void binarySearchIteration_positiveValue() {
        Optional<Integer> out = bs.binarySearchIteration(arrPositive,target);
        assertEquals(Optional.of(4).get(),out.get());
    }

    @Test
    public void binarySearchIteration_negativeValue() {
        Optional<Integer> out = bs.binarySearchIteration(arrNegative,target);
        assertEquals(Optional.of(3).get(),out.get());
    }

    @Test
    public void binarySearchIteration_noValue() {
        Optional<Integer> out = bs.binarySearchIteration(arrEmpty,target);
        assertEquals(Optional.empty(),out);
    }

    @Test
    public void binarySearchIteration_valueNotIn() {
        Optional<Integer> out = bs.binarySearchIteration(arrEmpty,target*target);
        assertEquals(Optional.empty(),out);
    }

    @Test
    public void binarySearchRecursion_positiveValue() {
        Optional<Integer> out = bs.binarySearchRecursion(arrPositive,0, arrPositive.length, target);
        assertEquals(Optional.of(4).get(),out.get());
    }

    @Test
    public void binarySearchRecursion_negativeValue() {
        Optional<Integer> out = bs.binarySearchRecursion(arrNegative,0, arrNegative.length, target);
        assertEquals(Optional.of(3).get(),out.get());
    }

    @Test
    public void binarySearchRecursion_noValue() {
        Optional<Integer> out = bs.binarySearchRecursion(arrEmpty,0, arrEmpty.length, target);
        assertEquals(Optional.empty(),out);
    }

    @Test
    public void binarySearchRecursion_valueNotIn() {
        Optional<Integer> out = bs.binarySearchRecursion(arrNegative,0, arrNegative.length, target*target);
        assertEquals(Optional.empty(),out);
    }
}
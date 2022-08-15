package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class OddEvenTest{
    private final int even  = 2;
    private final int odd = 3;
    private final int evenNegative = -2;
    private final int oddNegative = -3;

    @Test
    public void oddMod() {assertEquals(OddEven.oddEvenMod(odd), "odd");}
    @Test
    public void oddBit() {assertEquals(OddEven.oddEvenBit(odd), "odd");}
    @Test
    public void evenMod() {assertEquals(OddEven.oddEvenMod(even), "even");}
    @Test
    public void evenBit() {assertEquals(OddEven.oddEvenBit(even), "even");}
    @Test
    public void oddNegativeMod() {assertEquals(OddEven.oddEvenMod(oddNegative), "odd");}
    @Test
    public void oddNegativeBit() {assertEquals(OddEven.oddEvenBit(oddNegative), "odd");}
    @Test
    public void evenNegativeMod() {assertEquals(OddEven.oddEvenMod(evenNegative), "even");}
    @Test
    public void evenNegativeBit() {assertEquals(OddEven.oddEvenBit(evenNegative), "even");}

}
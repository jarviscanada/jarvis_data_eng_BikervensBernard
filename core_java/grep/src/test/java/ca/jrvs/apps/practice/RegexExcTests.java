package ca.jrvs.apps.practice;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class RegexExcTests{
    static RegexExc tester = null;

    @BeforeClass
    public static void test_RegexExc_setup() {
        tester = new RegexExc();
    }

    @AfterClass
    public static void test_RegexExc_cleanup() {
        tester = null;
    }

    @Test
    public void test_RegexExc_matchJpeg() {
        assertTrue(tester.matchJpeg("abc.jpg"));
        assertTrue(tester.matchJpeg("abc.jpeg"));
        assertFalse(tester.matchJpeg(".jpg"));
        assertFalse(tester.matchJpeg(".jpeg"));
        assertFalse(tester.matchJpeg("abc.jpggx"));
        assertFalse(tester.matchJpeg("jpg"));
        assertFalse(tester.matchJpeg("jpeg"));
    }

    @Test
    public void test_RegexExc_matchIp() {
        assertTrue(tester.matchIp("192.16.0.1"));
        assertTrue(tester.matchIp("182.168.100.100"));
        assertFalse(tester.matchIp("192.168"));
        assertFalse(tester.matchIp("192#168#0#1"));
    }

    @Test
    public void test_RegexExc_matchEmpty() {
        assertTrue(tester.matchEmptyLine(""));
        assertTrue(tester.matchEmptyLine("  "));
        assertTrue(tester.matchEmptyLine(" "));
        assertFalse(tester.matchEmptyLine("   a   "));
        assertFalse(tester.matchEmptyLine("a"));
    }
}

package ca.jrvs.apps.practice;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class LambdaStreamExcTest {
    static LambdaStreamExc tester = null;

    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;
    private static final PrintStream originalErr = System.err;

    @BeforeClass
    public static void test_LambdaStreamExc_setup() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        tester = new LambdaStreamExc();
    }

    @AfterClass
    public static void test_LambdaStreamExc_cleanup() {
        System.setOut(originalOut);
        System.setErr(originalErr);
        tester = null;
    }

    @Test
    public void test_LambdaStreamExc_createStrStream() {
        Stream<String> out = tester.createStrStream(new String[] {"a","b","c"});
        assertTrue(out.getClass().isInstance(Arrays.stream(new String[]{})));
        assertArrayEquals(out.toArray(), new String[] {"a","b","c"});
    }

    @Test
    public void test_LambdaStreamExc_toUpperCase() {
        String[] expected = {"A", "B"};
        Stream<String> actual = tester.toUpperCase(new String[] {"a", "b"});
        assertArrayEquals(expected, actual.toArray());
    }

    @Test
    public void test_LambdaStreamExc_filter() {
        String[] in = {"a", "b", "c"};
        Stream<String> actual = tester.filter(Arrays.stream(in), "c");
        assertArrayEquals(new String[] {"a","b"}, actual.toArray());
    }

    @Test
    public void test_LambdaStreamExc_createIntStream() {
        int[] in = {1, 2, 3};
        IntStream actual = tester.createIntStream(in);
        assertArrayEquals(in,actual.toArray());
    }

    @Test
    public void test_LambdaStreamExc_toList_givenStList() {
        assertEquals(Arrays.asList("a","b"), tester.toList(Arrays.asList("a","b").stream() ));
    }

    @Test
    public void test_LambdaStreamExc_toList_givenIntStream() {
        IntStream is = tester.createIntStream(new int[] {1,2});
        assertEquals(Arrays.asList(1,2), tester.toList(is));
    }

    @Test
    public void test_LambdaStreamExc_getOdd() {
        IntStream is = tester.createIntStream(new int[] {1,2,3,4,5,6});
        IntStream expected = tester.createIntStream(new int[] {1,3,5});
        IntStream actual = tester.getOdd(is);
        assertArrayEquals(expected.toArray(),actual.toArray());
    }

    @Test
    public void test_LambdaStreamExc_getLambdaPrinter() {
        Consumer<String> printer = tester.getLambdaPrinter("start>","<end");
        printer.accept("body");
    }

    @Test
    public void test_LambdaStreamExc_printMessages() {
        Consumer<String> printer = tester.getLambdaPrinter("testing: ", "! ");
        String[] messages = {"a", "b", "c"};
        tester.printMessages(messages, printer);
        String expected = "msg: a !\n" +"msg: b !\n"+ "msg: c !\n";
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void test_LambdaStreamExc_printOdd() {
        IntStream in = tester.createIntStream(new int[] {1,2,3,4,5,6});
        Consumer<String> printer = tester.getLambdaPrinter("odd value of: ", "! ");
        tester.printOdd(in,printer);
        String expected = "odd value of:  1 ! \n" +"odd value of:  3 ! \n"+ "odd value of:  5 ! \n";
        assertTrue(outContent.toString().contains(expected));
    }

    @Test
    public void test_LambdaStreamExc_flatNestedInt() {
        List<Integer> a = Arrays.asList(1, 2, 5);
        List<Integer> b = Arrays.asList(3, 4, 4);
        List<List<Integer>> in = Arrays.asList(a, b);
        List<Integer> expected = Arrays.asList(1, 4, 25, 9, 16, 16);
        Stream<List<Integer>> nest_stream = in.stream();
        Stream<Integer> flat = tester.flatNestedInt(nest_stream);
        assertEquals(expected,tester.toList(flat));
    }

    @Test
    public void test_LambdaStreamExc_createIntStream_givenRange() {
        IntStream actual = tester.createIntStream(2, 4);
        assertArrayEquals(new int[] {2, 3, 4},actual.toArray());
    }

    @Test
    public void test_LambdaStreamExc_squareRootIntStream() {
        int[] array = {4, 9, 25};
        List<Integer> a = Arrays.asList(2, 3, 5);
        IntStream stream = tester.createIntStream(array);
        DoubleStream out = tester.squareRootIntStream(stream);
        assertEquals(a, tester.toList(out));
    }
}

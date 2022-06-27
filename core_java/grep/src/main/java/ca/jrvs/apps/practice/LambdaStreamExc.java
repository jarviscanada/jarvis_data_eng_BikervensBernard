package ca.jrvs.apps.practice;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LambdaStreamExc implements ILambdaStreamExc{
    final Logger logger = LoggerFactory.getLogger (LambdaStreamExc.class);

    @Override
    public Stream<String> createStrStream(String... strings) {
        return Stream.of(strings);
    }

    @Override
    public Stream<String> toUpperCase(String... strings) {
        Stream<String> s = createStrStream(strings);
        return s.map(x -> x.toUpperCase());
    }

    @Override
    public Stream<String> filter(Stream<String> stringStream, String pattern) {
        return stringStream.filter(x->!x.contains(pattern));
    }

    @Override
    public IntStream createIntStream(int[] arr) {
        return IntStream.of(arr);
    }

    @Override
    public <E> List<E> toList(Stream<E> stream) {
        return stream.collect(Collectors.toList());
    }

    @Override
    public List<Integer> toList(IntStream intStream) {
        List<Integer> l = new ArrayList<>();
        intStream.forEach(i->l.add(i));
        return l;
    }

    public List<Integer> toList(DoubleStream intStream) {
        List<Integer> l = new ArrayList<>();
        intStream.forEach(i->l.add((int) i));
        return l;
    }

    @Override
    public IntStream createIntStream(int start, int end) {
        return IntStream.rangeClosed(start,end);
    }

    @Override
    public DoubleStream squareRootIntStream(IntStream intStream) {
        return intStream.boxed().mapToDouble(x->Math.sqrt(x.doubleValue()));
    }

    @Override
    public IntStream getOdd(IntStream intStream) {
        return intStream.filter(x -> x%2!=0);
    }

    @Override
    public Consumer<String> getLambdaPrinter(String prefix, String suffix) {
        return (x) -> System.out.println(prefix +" "+x+" "+suffix);
    }

    @Override
    public void printMessages(String[] messages, Consumer<String> printer) {
        Consumer<String> c = getLambdaPrinter("msg:", "!");
        Stream.of(messages).forEach(x-> c.accept(x));
    }

    @Override
    public void printOdd(IntStream intStream, Consumer<String> printer) {
        this.getOdd(intStream).forEach(x -> printer.accept(String.valueOf(x)));
    }

    @Override
    public Stream<Integer> flatNestedInt(Stream<List<Integer>> ints) {
        //return ints.flatMap(l -> l.stream().map(x-> (int) Math.pow(x,2) ));
        return ints.flatMap(l -> l.stream().map(x-> x * x));
    }

    public static void main(String[] args) {

        BasicConfigurator.configure();
        LambdaStreamExc lse = new LambdaStreamExc();
        try {
            Consumer<String> printer = lse.getLambdaPrinter("start>", "<end");
            printer.accept("Message body");

            String[] messages = {"a","b", "c"};
            lse.printMessages(messages, lse.getLambdaPrinter("msg:", "!") );

            lse.printOdd(lse.createIntStream(new int[]{1,2,3,4,5,6,7,8,9}), lse.getLambdaPrinter("isOdd->", ". "));

            System.out.println("");

            List<Integer> x = Arrays.asList(1, 2, 3);
            List<Integer> y = Arrays.asList(4, 5, 6);
            Stream<List<Integer>> s = Arrays.asList(x, y).stream();
            Stream<Integer> out = lse.flatNestedInt(s);
            out.forEach(i->printer.accept(""+i+""));

        } catch (Exception ex) {
            lse.logger.error("Error: Unable to process", ex);
        }
    }
}

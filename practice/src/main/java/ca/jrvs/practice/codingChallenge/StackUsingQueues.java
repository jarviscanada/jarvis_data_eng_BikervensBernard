package ca.jrvs.practice.codingChallenge;

import java.util.LinkedList;
import java.util.Queue;

// https://www.notion.so/jarvisdev/Implement-Stack-using-Queue-2367f4635734468cac742a7c84bfbe72
public class StackUsingQueues {

    private static Queue<Integer> q1 = new LinkedList<Integer>();
    private static Queue<Integer> q2 = new LinkedList<Integer>();
    private static int top;

    public static void push(int x) {
        q1.add(x);
        for (int i=1; i<q1.size(); i++)
            q1.add(q1.remove());
    }

    public static int pop(String empty) {
        return q1.remove();
    }

    public static int top() {
        return q1.peek();
    }

    public static boolean empty() {
        return q1.isEmpty();
    }

    public static void push(int x, String empty) {
        q2.add(x);
        top = x;
        while (!q1.isEmpty()) {
            q2.add(q1.remove());
        }
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
    }

    public static int pop() {
        int r = q1.remove();
        if (!q1.isEmpty()) {
            top = q1.peek();
        }
        return r;
    }
}

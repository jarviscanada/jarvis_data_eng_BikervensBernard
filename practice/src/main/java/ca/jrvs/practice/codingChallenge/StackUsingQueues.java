package ca.jrvs.practice.codingChallenge;

import java.util.LinkedList;
import java.util.Queue;

// https://www.notion.so/jarvisdev/Implement-Stack-using-Queue-2367f4635734468cac742a7c84bfbe72
// https://leetcode.com/problems/implement-stack-using-queues/
public class StackUsingQueues {

    private Queue<Integer> q1 = new LinkedList<Integer>();

    /**
     * left linear rotation
     * @Big-O: O(n)
     * @Justification: we shift every element all the time */
    public void push(int x) {
        q1.add(x);
        for (int i=1; i<q1.size(); i++)
            q1.add(q1.remove());
    }

    /**
     * @Big-O: O(1)
     * @Justification: direct access */
    public int pop() {return q1.remove();}

    public int top() {return q1.peek();}

    public boolean empty() {return q1.isEmpty();}
    public int size() {return q1.size();}



    private Queue<Integer> q2 = new LinkedList<>();
    private int top;

    /**
     * left linear rotation
     * @Big-O: O(1)
     * @Justification: direct access */
    public void push(int x,String empty) {
        q1.add(x);
        top = x;
    }

    /**
     * @Big-O: O(n)
     * @Justification: iteration of first queue */
    public int pop(String empty) {
        while (q1.size() > 1) {
            top = q1.remove();
            q2.add(top);
        }
        int x = q1.remove();
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
        return x;
    }
}

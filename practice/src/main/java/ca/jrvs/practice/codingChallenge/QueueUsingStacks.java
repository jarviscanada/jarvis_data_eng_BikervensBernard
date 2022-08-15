package ca.jrvs.practice.codingChallenge;

import java.util.Stack;

// https://www.notion.so/jarvisdev/Implement-Queue-using-Stacks-28a3dcc816fb423e902517f5a96ec4f1
// https://leetcode.com/problems/implement-queue-using-stacks/
public class QueueUsingStacks{

    private Stack<Integer> s = new Stack();
    private Stack<Integer> s2 = new Stack<>();
    private int front;
    public void push(int x) {
        s.push(x);
        int i = 1;
        while(i < s.size() ) {
            s.push(s.firstElement());
            s.removeElementAt(0);
            i++;
        }
    }

    /**
     * @Big-O: O(1)
     * @Justification: direct access insertion
     */
    public void push(int x, String empty) {
        if (s.empty())
            front = x;
        s.push(x);
    }
    public int pop() {return s.pop();}
    /**
     * @Big-O: O(1) Amortized. worst Big-O: O(n)
     * @Justification: direct access insertion if not empty. else n iteration occurs
     */
    public int pop(String empty) {
        if (s2.isEmpty()) {
            while (!s.isEmpty())
                s2.push(s.pop());
        }
        return s2.pop();
    }
    public int peek() {return s.peek();}
    public int peek(String empty) {
        if (!s2.isEmpty()) {
            return s2.peek();
        }
        return front;
    }
    public boolean empty() {return s.isEmpty();}
    public boolean empty(String empty) {return s.isEmpty() && s2.isEmpty();}
    public int size() {return s.size()+s2.size();}
}

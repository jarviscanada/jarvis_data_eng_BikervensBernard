package ca.jrvs.practice.codingChallenge;

import java.util.Stack;

public class ValidParentheses{
    public static boolean isValid(String s) {
        Stack stack = new Stack();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            }else {
                if ( stack.isEmpty() ) {
                    return false;
                }
                if ( stack.peek().equals('(') && c != ')' ) {
                    return false;
                }
                if ( stack.peek().equals('{') && c != '}' ) {
                    return false;
                }
                if ( stack.peek().equals('[') && c != ']' ) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}

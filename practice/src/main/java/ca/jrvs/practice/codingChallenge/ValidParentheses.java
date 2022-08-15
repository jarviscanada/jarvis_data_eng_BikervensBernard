package ca.jrvs.practice.codingChallenge;

import java.util.Stack;
// https://www.notion.so/jarvisdev/Valid-Parentheses-4f526a24e06e47cd85be33d535781599
// https://leetcode.com/problems/valid-parentheses/
public class ValidParentheses{
    public static boolean isValid(String s) {
        Stack stack = new Stack();
        for (char c : s.trim().replaceAll(" ","").toCharArray()) {
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

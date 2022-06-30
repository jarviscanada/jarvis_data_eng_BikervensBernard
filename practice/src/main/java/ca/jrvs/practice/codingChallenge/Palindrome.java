package ca.jrvs.practice.codingChallenge;

// https://www.notion.so/jarvisdev/Valid-Palindrome-42cbe820cbe248a8b0cb2d6ca39fd13c
// https://leetcode.com/problems/valid-palindrome/
public class Palindrome {

    /**
     * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all
     * non-alphanumeric characters, it reads the same forward and backward.
     * Alphanumeric characters include letters and numbers.
     * @Big-O: worst O(n)
     * @Justification: iteration over each character
     * @param s arrays of all possible output
     * @return true if it is a palindrome, or false otherwise.
     */
    public static boolean isPalindrome(String s) {
        if (s.isEmpty()) {
            return true;
        }
        int head = 0, tail = s.length() - 1;
        char cHead, cTail;
        while(head <= tail) {
            cHead = s.charAt(head);
            cTail = s.charAt(tail);
            if (!Character.isLetterOrDigit(cHead)) {
                head++;
            } else if(!Character.isLetterOrDigit(cTail)) {
                tail--;
            } else {
                if (Character.toLowerCase(cHead) != Character.toLowerCase(cTail)) {
                    return false;
                }
                head++;
                tail--;
            }
        }
        return true;
    }
}

package ca.jrvs.practice.codingChallenge;

// https://www.notion.so/jarvisdev/String-to-Integer-atoi-6722bcda9b0743fda457cca4a4dc65f2
// https://leetcode.com/problems/string-to-integer-atoi/
public class MyAtoi{
    /**
     * @Big-O: worst O(n)
     * @Justification: We visit each character in the input at most once and for each character we spend a constant amount of time.
     */
    public static int myAtoi(String input) {
        int sign = 1;
        int result = 0;
        int index = 0;
        int n = input.length();

        // Discard all spaces from the beginning of the input string.
        while (index < n && input.charAt(index) == ' ') {
            index++;
        }

        // sign = +1, if it's positive number, otherwise sign = -1.
        if (index < n && input.charAt(index) == '+') {
            sign = 1;
            index++;
        } else if (index < n && input.charAt(index) == '-') {
            sign = -1;
            index++;
        }

        // Traverse next digits of input and stop if it is not a digit
        while (index < n && Character.isDigit(input.charAt(index))) {
            int digit = input.charAt(index) - '0';

            // Check overflow and underflow conditions.
            if ((result > Integer.MAX_VALUE / 10) ||
                    (result == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10)) {
                // If integer overflowed return 2^31-1, otherwise if underflowed return -2^31.
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            // Append current digit to the result.
            result = 10 * result + digit;
            index++;
            new StringBuilder("").toString();
        }

        // We have formed a valid number without any overflow/underflow.
        // Return it after multiplying it with its sign.
        return sign * result;
    }
}

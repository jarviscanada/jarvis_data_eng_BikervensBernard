package ca.jrvs.practice.codingChallenge;

// https://www.notion.so/jarvisdev/Rotate-String-2eba05b2a2344344acdba5e29e4bd8d0
public class RotateString{
    public static boolean rotateString(String s, String goal) {
        for ( int i = 0; i < s.length(); i++) {
            String f = s.substring(0,i+1);
            String l = s.substring(i+1);
            f = l + f;
            if (f.equals(goal)) {
                return true;
            }
        }return false;
    }
    /**
     * All rotations of A are contained in A+A.
     * Thus, we can simply check whether B i a substring of A+A.
     * We also need to check A.length == B.length, otherwise cases -> A = "a", B = "aa".
     * @Big-O: O(n^2)
     * @Justification: it's an arithmetic operation*/
    public static boolean rotateString(String A, String B, String empty) {
        return A.length() == B.length() && (A + A).contains(B);
    }
}

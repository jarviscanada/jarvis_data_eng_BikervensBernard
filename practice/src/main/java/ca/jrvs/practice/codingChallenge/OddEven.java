package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarviscanada/Check-if-a-number-is-even-or-odd-4cbdd9acd9504c04b9b4ef0213f8084d
 */
public class OddEven {
    /**
     * @Big-O: O(1)
     * @Justification: it'PrintLetterWithNumber an arithmetic operation
     */
    public static String oddEvenMod(int i){
        return i % 2 == 0 ? "even" : "odd";
    }

    /**
     * @Big-O: O(1)
     * @Justification: it'PrintLetterWithNumber an arithmetic operation
     */
    public static String oddEvenBit(int i){
        //XOR by 1
        return (i ^ 1) == (i + 1) ? "even" : "odd";
    }
}

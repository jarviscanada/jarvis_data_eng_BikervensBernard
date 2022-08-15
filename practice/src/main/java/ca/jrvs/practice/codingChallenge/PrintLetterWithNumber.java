package ca.jrvs.practice.codingChallenge;

public class PrintLetterWithNumber {
    public static String print(String str) {
        String[] alphabetArray = "abcdefghijklmnopqrstuvwxyz".split("");
        String[] strArray = str.split("");
        String out = "";
        for (String s: strArray) {
            for(int i = 0 ; i < alphabetArray.length; i++) {
                if (s.equalsIgnoreCase(alphabetArray[i])) {
                    //same letter
                    out+=s;
                    out+= s.equals(alphabetArray[i]) ? i+1: alphabetArray.length+1;
                }
            }
        }
        return out;
    }

    public static String print(String str,int i) {
        String a = "abcdefghijklmnopqrstuvwxyz";
        String s = "";
        for (char c: str.toCharArray()) {
            s+= c;
            s+= Character.isUpperCase(c) ? a.indexOf(c)+26+2 : a.indexOf(c)+1;
        }
        return s;
    }

    public static void main(String[] args) {
        //For example, the index of a is 1,  b is 2, A is 27, and so on
        //input: "abcee"
        //output: "a1b2c3e5e5"
        System.out.println(PrintLetterWithNumber.print("abcee"));
        System.out.println(PrintLetterWithNumber.print("Abcee"));
        System.out.println(PrintLetterWithNumber.print("Abcee",0));
        System.out.println(PrintLetterWithNumber.print("abcee",0));
    }
}

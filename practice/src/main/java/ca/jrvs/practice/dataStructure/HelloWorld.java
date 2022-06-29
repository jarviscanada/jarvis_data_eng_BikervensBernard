package ca.jrvs.practice.dataStructure;

import ca.jrvs.practice.codingChallenge.Anagram;

class HelloWorld {

  // Your program begins with a call to main().
  // Prints "Hello, World" to the terminal window.
  public static void main(String args[]) {
    System.out.println("Hello, World");
    System.out.println(
            new Anagram().isAnagram("anagram", "nagaram")
    );

  }
}

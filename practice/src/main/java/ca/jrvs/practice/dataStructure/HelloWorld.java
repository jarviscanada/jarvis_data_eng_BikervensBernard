package ca.jrvs.practice.dataStructure;

import ca.jrvs.practice.codingChallenge.NthNodeFromEndofLinkedList;

import java.util.Arrays;
import java.util.LinkedList;

class HelloWorld {

  // Your program begins with a call to main().
  // Prints "Hello, World" to the terminal window.
  public static void main(String args[]) {
    System.out.println("Hello, World");
    LinkedList l = new LinkedList(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
    System.out.println(
            new NthNodeFromEndofLinkedList().nthNodeFromEndofLinkedList(3,l).get()
    );
  }
}

package ca.jrvs.practice.dataStructure;

import ca.jrvs.practice.search.BinarySearch;

import java.util.Optional;

class HelloWorld {

  // Your program begins with a call to main().
  // Prints "Hello, World" to the terminal window.
  public static void main(String args[]) {
    System.out.println("Hello, World");
    BinarySearch bs = new BinarySearch();
    Integer[] arr = new Integer[] {1,2,3,4,5,6,7,8,9,10};
    Optional<Integer> x1 = bs.binarySearchIteration(arr,5);
    System.out.println(x1);
    Optional<Integer> x2 = bs.binarySearchRecursion(arr,0, arr.length,5);
    System.out.println(x2);
  }
}

package ca.jrvs.practice.dataStructure;

import ca.jrvs.practice.interfaces.ITree;

class HelloWorld {

  // Your program begins with a call to main().
  // Prints "Hello, World" to the terminal window.
  public static void main(String args[]) {
    System.out.println("Hello, World");
    ITree<Node> bst = new BST();
    bst.insert(new Node( 8));
    bst.insert(new Node( 2));
    bst.insert(new Node( 21));
    bst.insert(new Node( 1));
    bst.insert(new Node( 5));
    bst.insert(new Node( 13));
    bst.insert(new Node( 3));
    bst.printTree();

  }
}

package ca.jrvs.practice.dataStructure;

public class Node {
    public int data;
    public int height = 0;
    public Node leftChild;
    public Node rightChild;
    public Node(int value) {data = value;}
    public Node(Node l, int value, Node r) {
        data = value;leftChild = l;rightChild = r;
    }
}
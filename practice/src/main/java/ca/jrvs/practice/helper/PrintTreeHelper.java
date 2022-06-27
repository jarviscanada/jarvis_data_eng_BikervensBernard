package ca.jrvs.practice.helper;

import ca.jrvs.practice.dataStructure.Node;

import java.util.ArrayList;

public class PrintTreeHelper{

    public void printTree(Node root) {
        ArrayList<Node> treeLevel = new ArrayList<Node>();
        treeLevel.add(root);
        ArrayList<Node> temp = new ArrayList<Node>();
        int counter = 0;
        int height = heightOfTree(root);
        double numberOfElements = (Math.pow(2 , (height + 1)) - 1);

        while (counter <= height) {
            Node removed = treeLevel.remove(0);
            if (temp.isEmpty()) {
                printSpace(
                        numberOfElements / Math.pow(2 , counter + 1),
                        removed
                );
            }
            else {
                printSpace(
                        numberOfElements / Math.pow(2 , counter),
                        removed
                );
            }
            if (removed == null) {
                temp.add(null);
                temp.add(null);
            }
            else {
                temp.add(removed.leftChild);
                temp.add(removed.rightChild);
            }
            if (treeLevel.isEmpty()) {
                System.out.println("");
                System.out.println("");
                treeLevel = temp;
                temp = new ArrayList<>();
                counter++;
            }
        }
    }
    private void printSpace(double n, Node removed){
        for(;n>0;n--) {System.out.print("\t ");}
        String bf = " bf->";
        if(removed == null){System.out.print(" ");}
        else {System.out.print(removed.data + bf(removed));}
    }
    private int bf(Node node) {return node == null ? 0: heightOf(node.leftChild) - heightOf(node.rightChild);}
    private int heightOf(Node node) {return (node==null)? 0 : node.height;}
    private int heightOfTree(Node root){
        if(root==null) {return 0;}
        if(root.leftChild == null ){if(root.rightChild==null) {return 0;}}
        int l = heightOfTree(root.leftChild);
        int r = heightOfTree(root.rightChild);
        return 1 + Math.max(l,r);
    }
}

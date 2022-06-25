package ca.jrvs.practice.dataStructure;

import ca.jrvs.practice.interfaces.ITree;

import java.util.ArrayList;

public class BST implements ITree<Node>{

    public enum Traversal {IN_ORDER, POST_ORDER, PRE_ORDER}
    private Node root;

    public Node getRoot() {
        return root;
    }
    private Node insert(Node current, int data) {
        if (current == null) {return new Node(data);}
        if (data < current.data)  {
            current.leftChild = insert(current.leftChild,data);
        }
        else {
            current.rightChild =  insert(current.rightChild,data);
        }
        return current;
    }
    private Node delete(Node current, Node data) {
        if (current == null) {return null;}

        /* find the node logic*/
        if (data.data < current.data)
        {current.leftChild = delete(current.leftChild, data);}
        else if (data.data > current.data)
        {current.rightChild = delete(current.rightChild, data);}
        else {
            // (current.data == data.data)

            // node with 1 children: Get the inorder
            if (current.leftChild == null) {return current.rightChild;}
            if (current.rightChild == null) {return current.leftChild;}

            // node with two children: Get the inorder

            /**
             * logic goes as follow:
             * do not touch or move node from the tree (keep identical pointer)
             * set current node value to the same value as inorder successor (smallest in the right subtree)
             * we now have a tree wth double value
             * from current node.leftchild run delete methode() with value to delete = current value
             * delete methode will perform as if it is a case of remove a node with 0 | 1 children
             * (this is correct since the smallest value in this subtree has 0 | 1 children by definition)
             * */
            current.data = minValue(current.rightChild).data;
            current.rightChild = delete(current.rightChild,current);
        }
        return current;
    }
    private Node minValue(Node current) {
        if (current.leftChild == null) return current;
        minValue(current.leftChild);
        return current;
    }
    private void traverse(Node current, String order) {
        if (order.equals(String.valueOf(Traversal.IN_ORDER)) ){
            if (current.leftChild != null) {traverse(current.leftChild,order);}
            System.out.print(" " + current.data);
            if (current.rightChild != null) {traverse(current.rightChild,order);}
        }
        if (order.equals(String.valueOf(Traversal.POST_ORDER)) ){
            if (current.leftChild != null) {traverse(current.leftChild,order);}
            if (current.rightChild != null) {traverse(current.rightChild,order);}
            System.out.print(" " + current.data);
        }
        if (order.equals(String.valueOf(Traversal.PRE_ORDER)) ){
            System.out.print(" " + current.data);
            if (current.leftChild != null) {traverse(current.leftChild,order);}
            if (current.rightChild != null) {traverse(current.rightChild,order);}
        }
    }
    private Node find(Node toFind, Node current) {
        if (current == null) {return null;}
        if (current.data == toFind.data) {return current;}
        else {
            if (toFind.data < current.data) {
                return this.find(toFind, current.leftChild);
            }
            if (toFind.data > current.data) {
                return this.find(toFind, current.rightChild);
            }
        }
        return null;
    }

    @Override
    public ITree<Node> insert(Node data) {
        this.root = this.insert(this.root,data.data);
        return this;
    }

    @Override
    public void delete(Node data) {this.root = this.delete(this.root,data);}

    @Override
    public void traverse(String order) {
        this.traverse(this.root,order);
        System.out.println();
    }

    @Override
    public Node getMax(Node current) {
        return null;
    }

    @Override
    public Node getMin(Node current) {
        return null;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {return this.root == null;}

    @Override
    public Node find(Node toFind) {return (this.root == null)? null :this.find(toFind, this.root);}

    @Override
    public void printTree() {
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

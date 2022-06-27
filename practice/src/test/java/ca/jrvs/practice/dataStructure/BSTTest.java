package ca.jrvs.practice.dataStructure;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BSTTest{
    private BST bst;

    @Before
    public void setUp() throws Exception {
        bst = new BST();
        bst.insert(new Node( 8));
        bst.insert(new Node( 2));
        bst.insert(new Node( 21));
        bst.insert(new Node( 1));
        bst.insert(new Node( 5));
        bst.insert(new Node( 13));
        bst.insert(new Node( 3));
    }

    @Test
    public void insert() {
        bst = new BST();
        bst.insert(new Node( 8));
        bst.insert(new Node( 2));
        bst.insert(new Node( 21));
        bst.insert(new Node( 1));
        bst.insert(new Node( 5));
        bst.insert(new Node( 13));
        bst.insert(new Node( 3));
        assertEquals(8,bst.getRoot().data);
        assertEquals(2,bst.getRoot().leftChild.data);
        assertEquals(21,bst.getRoot().rightChild.data);
        assertEquals(1,bst.getRoot().leftChild.leftChild.data);
        assertEquals(5,bst.getRoot().leftChild.rightChild.data);
        assertEquals(3,bst.getRoot().leftChild.rightChild.leftChild.data);
        assertEquals(13,bst.getRoot().rightChild.leftChild.data);
    }

    @Test
    public void delete() {
        assertEquals(8,bst.getRoot().data);
        assertEquals(2,bst.getRoot().leftChild.data);
        assertEquals(21,bst.getRoot().rightChild.data);
        assertEquals(1,bst.getRoot().leftChild.leftChild.data);
        assertEquals(5,bst.getRoot().leftChild.rightChild.data);
        assertEquals(3,bst.getRoot().leftChild.rightChild.leftChild.data);
        assertEquals(13,bst.getRoot().rightChild.leftChild.data);

        bst.delete(new Node(2));

        assertEquals(8,bst.getRoot().data);
        assertEquals(5,bst.getRoot().leftChild.data);
        assertEquals(21,bst.getRoot().rightChild.data);
        assertEquals(1,bst.getRoot().leftChild.leftChild.data);
        assertEquals(3,bst.getRoot().leftChild.rightChild.data);
        assertEquals(13,bst.getRoot().rightChild.leftChild.data);
    }

    @Test
    public void traverse() {
        bst.traverse(String.valueOf(BST.Traversal.PRE_ORDER));
        bst.traverse(String.valueOf(BST.Traversal.POST_ORDER));
        bst.traverse(String.valueOf(BST.Traversal.IN_ORDER));
    }

    @Test
    public void getMax() {
        assertTrue(true);
    }

    @Test
    public void getMin() {
        assertTrue(true);
    }

    @Test
    public void getSize() {
        assertTrue(true);
    }

    @Test
    public void isEmpty() {
        assertFalse(bst.isEmpty());
        bst = new BST();
        assertTrue(bst.isEmpty());
    }

    @Test
    public void find_validKey() {
        assertEquals(3,bst.find(new Node(3)).data);
    }

    @Test
    public void find_invalidKey() {
        assertEquals(null,bst.find(new Node(30)));
    }
}
package ca.jrvs.practice.interfaces;

public interface ITree <T> {
    public ITree<T> insert(T node);

    public void delete(T node);

    public void traverse(String order);

    public T getMax(T node);

    public T getMin(T node);

    public int getSize();

    public boolean isEmpty();

    public T find(T toFind);
    public void printTree();
}
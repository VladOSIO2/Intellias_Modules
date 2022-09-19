package module9.myCollections;

import java.util.Objects;

public class MyStack<E> {

    private Node<E> top;
    private int size;

    public MyStack() {
        size = 0;
    }

    //pushes an element to the stack
    public void push(E element) {
        top = new Node<>(element, top);
        size++;
    }

    //removes the top element from the stack
    public E remove(int index) {
        Objects.checkIndex(index, size);

        //finding node previous to node at a given index
        Node<E> eNode = top;
        for (int i = 1; i < index; i++) {
            eNode = eNode.next;
        }

        //let the gc destroy unlinked node
        Node<E> nextNode = eNode.next;
        eNode.next = nextNode.next;
        size--;
        return nextNode.item;
    }

    public void clear() {
        //clearing all the stored links so
        //GC will destroy all of those objects
        for (Node<E> eNode = top; eNode != null; ) {
            Node<E> next = eNode.next;
            eNode.item = null;
            eNode.next = null;
            eNode = next;
        }
        top = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public E peek() {
        return top.item;
    }

    public E pop() {
        Node<E> eNode = top;
        top = top.next;
        size--;
        return eNode.item;
    }

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }

    @Override
    public String toString() {
        if (size == 0)
            return "";

        StringBuilder sb = new StringBuilder(top.item.toString());
        Node<E> eNode = top;
        for (int i = 1; i < size; i++) {
            eNode = eNode.next;
            sb.append(" -> ").append(eNode.item.toString());
        }
        return sb.toString();
    }
}

package module9.myCollections;

import java.util.Objects;

public class MyQueue<E> {

    //first node of queue, will be first out
    private Node<E> firstNode;

    //last node of the queue, used as a queue tail for adding new nodes
    private Node<E> lastNode;

    private int size;

    public MyQueue() {
        size = 0;
    }

    public void add(E element) {
        final Node<E> l = lastNode;
        final Node<E> newNode = new Node<>(element, null);
        lastNode = newNode;
        if (l == null) { //queue is empty
            firstNode = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    public E remove(int index) {
        Objects.checkIndex(index, size);

        if (index == 0) { //polling out first element from queue
            return poll();
        }

        //finding node previous to node at a given index
        Node<E> eNode = firstNode;
        for (int i = 1; i < index; i++) {
            eNode = eNode.next;
        }

        //let the gc destroy unlinked node
        Node<E> nextNode = eNode.next;
        eNode.next = nextNode.next;
        if (index == size - 1) { //if removing last node
            lastNode = eNode;
        }

        size--;
        return nextNode.item;
    }

    public void clear() {
        //clearing all the stored links so
        //GC will destroy all of those objects
        for (Node<E> x = firstNode; x != null; ) {
            Node<E> next = x.next;
            x.item = null;
            x.next = null;
            x = next;
        }
        firstNode = null;
        lastNode = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    //returns the value of first element in the queue
    public E peek() {
        if (size == 0) {
            return null;
        }
        return firstNode.item;
    }

    //removes first element from the queue
    //returns a removed element
    public E poll() {
        if (size == 0) {
            return null;
        }
        final Node<E> node = firstNode;
        firstNode = firstNode.next;
        if (firstNode == null) {
            lastNode = null;
        }
        size--;
        return node.item;
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

        StringBuilder sb = new StringBuilder(firstNode.item.toString());
        Node<E> eNode = firstNode;
        for (int i = 1; i < size; i++) {
            eNode = eNode.next;
            sb.append(" -> ").append(eNode.item.toString());
        }
        return sb.toString();
    }
}
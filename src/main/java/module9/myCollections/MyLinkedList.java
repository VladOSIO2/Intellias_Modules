package module9.myCollections;

import java.util.Objects;

public class MyLinkedList<E> {
    private Node<E> firstNode;
    private Node<E> lastNode;
    private int size;

    public MyLinkedList() {

    }

    public void add(E element) {
        final Node<E> l = lastNode;
        final Node<E> newNode = new Node<>(l, element, null);
        lastNode = newNode;
        if (l == null) {
            firstNode = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    public E remove(int index) {
        Objects.checkIndex(index, size);
        return unlink(searchNode(index));
    }

    public void clear() {
        //clearing all the stored links so
        //GC will destroy all of those objects
        for (Node<E> x = firstNode; x != null; ) {
            Node<E> next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        firstNode = lastNode = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public E get(int index) {
        Objects.checkIndex(index, size);
        return searchNode(index).item;
    }

    private Node<E> searchNode(int index) {
        Node<E> x;
        if (index < (size >> 1)) { //if index is in 1st half of elements
            //searching starting from first element
            x = firstNode;
            for (int i = 0; i < index; i++)
                x = x.next;
        } else {
            //searching starting from last element
            x = lastNode;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
        }
        return x;
    }

    //unlinks the node from a linked list, returns element that the unlinked node contained
    private E unlink(Node<E> node) {
        final E element = node.item;
        final Node<E> next = node.next;
        final Node<E> prev = node.prev;

        if (prev == null) {
            //unlinking 1st element
            firstNode = next;
        } else {
            prev.next = next;
            node.prev = null;
        }

        if (next == null) {
            //unlinking last element
            lastNode = prev;
        } else {
            next.prev = prev;
            node.next = null;
        }

        node.item = null;
        size--;
        return element;
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}

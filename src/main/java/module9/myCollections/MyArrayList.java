package module9.myCollections;

import java.util.Arrays;
import java.util.Objects;

public class MyArrayList<E> {
    private static final int DEFAULT_INITIAL_CAPACITY = 10;

    private Object[] elements;  //an array where all the elements are being stored
    private int size;           //current size of an array (not capacity)

    public MyArrayList(int initCapacity) {
        this.elements = new Object[initCapacity];
    }

    public MyArrayList() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    //adds an element to arraylist
    public void add(E element) {
        if (size == elements.length) {
            grow();
        }
        elements[size] = element;
        size++;
    }

    //removes element by index, returns removed element
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        Objects.checkIndex(index, size);
        E oldValue = (E) elements[index];

        final int newSize = size - 1;
        if (newSize > index) { //shifting elements after a removed element one position left
            System.arraycopy(elements, index + 1, elements, index, newSize - index);
        }
        size = newSize;
        elements[newSize] = null;

        return oldValue;
    }

    //clears the array
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    //returns array size
    public int size() {
        return size;
    }

    //returns element in array by index
    @SuppressWarnings("unchecked")
    public E get(int index) {
        Objects.checkIndex(index, size);
        return (E) elements[index];
    }

    //grows the arraylist's array to 1.5x of its size
    private void grow() {
        int oldCapacity = elements.length;
        if (oldCapacity > 0) {
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            elements = Arrays.copyOf(elements, newCapacity);
        } else {
            elements = new Object[DEFAULT_INITIAL_CAPACITY];
        }
    }
}

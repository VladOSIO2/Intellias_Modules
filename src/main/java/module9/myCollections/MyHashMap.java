package module9.myCollections;

import java.util.Arrays;

public class MyHashMap<K, V> {

    private static final int DEFAULT_CAPACITY = 16;

    //Maximum capacity of the HashMap
    //determined by maximum power of 2 that doesn't exceed the max int value
    private static final int MAXIMUM_CAPACITY = 1 << 30;

    //when the MyHashMap load exceeds the load factor,
    //the table resize method will be called
    private static final float LOAD_FACTOR = 0.75f;
    private Node<K, V>[] table;
    private int size;
    private int capacity;


    public MyHashMap() {
        @SuppressWarnings({"unchecked"})
        Node<K,V>[] tab = (Node<K,V>[]) new Node[DEFAULT_CAPACITY];
        table = tab;
        size = 0;
        capacity = DEFAULT_CAPACITY;
    }

    //puts entry in a MyHashMap
    public void put(K key, V value) {
        if (key == null) {
            throw new NullPointerException("Key can't be null!");
        }

        if (containsKey(key)) {
            throw new IllegalArgumentException("Already contains key: " + key);
        }

        if (isOverloaded()) {
            resize();
        }

        putUnchecked(key, value);
        size++;
    }

    //removes entry from MyHashMap by key, returns removed value
    public V remove(K key) {
        int index = getBucketIndex(hash(key));
        Node<K, V> node = table[index];

        if (node == null) {
            return null;
        } else if (node.key.equals(key)) {
            table[index] = null;
            size--;
            return node.value;
        } else {
            while (node.next != null) {
                if (node.next.key.equals(key)) {
                    //removing the link to the node with specified key
                    Node<K, V> nextNode = node.next;
                    node.next = nextNode.next;
                    size--;
                    return nextNode.value;
                }
                node = node.next;
            }
        }
        return null;
    }

    //clears the MyHashMap
    public void clear() {
        if (table != null) {
            Arrays.fill(table, null);
        }
        size = 0;
    }

    public int size() {
        return size;
    }

    public V get(K key) {
        Node<K, V> node = getNode(key);
        return node != null ? node.value : null;
    }

    private boolean containsKey(K key) {
        return getNode(key) != null;
    }

    //finds the node with the given key, returns that node
    private Node<K, V> getNode(K key) {
        int bucket = getBucketIndex(hash(key));
        Node<K, V> node = table[bucket];

        if (node == null) {
            return null;
        } else if (node.key.equals(key)) {
            return node;
        } else {
            while (node.next != null) {
                node = node.next;
                if (node.key.equals(key)) {
                    return node;
                }
            }
        }
        return null;
    }

    //spreading higher bits of hash code with XOR
    //to spread the values on the table
    private int hash(K key) {
        int hash;
        return(key == null)
                ? 0
                : (hash = key.hashCode()) ^ (hash >>> 16);
    }

    //calculates the index of a bucket in a table for a given hash
    private int getBucketIndex(int hash) {
        return hash % capacity;
    }


    //returns true if current hashmap load is greater than load factor
    private boolean isOverloaded() {
        return Float.compare((float) size / capacity, LOAD_FACTOR) > 0;
    }


    //resizes the table array, doubling its size
    //and rearranging all the nodes
    @SuppressWarnings({"unchecked"})
    private void resize() {
        Node<K, V>[] oldTable = table;
        int newCapacity = Math.min(capacity << 1, MAXIMUM_CAPACITY);
        table = (Node<K, V>[]) new Node[newCapacity];
        int oldCapacity = capacity;
        capacity = newCapacity;
        putNodesFromTable(oldTable, oldCapacity);
    }

    //puts all the nodes from the given old table to a new table of MyHashMap
    private void putNodesFromTable(Node<K, V>[] oldTable, int oldCapacity) {
        for (int i = 0; i < oldCapacity; i++) {
            Node<K, V> node = oldTable[i];
            while (node != null) {
                putUnchecked(node.key, node.value);
                node = node.next;
            }
        }
    }

    // put without checking is the data valid
    // (for rehashing nodes after resizing)
    private void putUnchecked(K key, V value) {
        int hash = hash(key);
        int index = getBucketIndex(hash);
        Node<K, V> node = new Node<>(key, value, hash, null);

        if (table[index] == null) { //bucket is empty
            table[index] = node;
        } else { //place node in the end of chain in the bucket
            Node<K, V> searchNode = table[index];
            while (searchNode.next != null) {
                searchNode = searchNode.next;
            }
            searchNode.next = node;
        }
    }

    private static class Node<K, V> {
        K key;
        V value;

        int hashcode;
        Node<K, V> next;

        public Node(K key, V value, int hashcode, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.hashcode = hashcode;
            this.next = next;
        }
    }
}

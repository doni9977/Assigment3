package src;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BST<K extends Comparable<K>, V> implements Iterable<BST.Node<K, V>> {
    public static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    private Node<K, V> root;
    private int size = 0;

    public BST() {
        root = null;
    }

    public void put(K key, V value) {
        if (key == null) {
            return;
        }

        Node<K, V> newNode = new Node<>(key, value);

        if (root == null) {
            root = newNode;
            size++;
            return;
        }

        Node<K, V> current = root;
        Node<K, V> parent = null;

        while (current != null) {
            parent = current;
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                current.value = value;
                return;
            }
        }

        int cmp = key.compareTo(parent.key);
        if (cmp < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        size++;
    }

    public V get(K key) {
        if (key == null) {
            return null;
        }

        Node<K, V> current = root;

        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                return current.value;
            }
        }
        return null;
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public boolean remove(K key) {
        if (key == null) {
            return false;
        }

        Node<K, V> current = root;
        Node<K, V> parent = null;

        while (current != null && key.compareTo(current.key) != 0) {
            parent = current;
            if (key.compareTo(current.key) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if (current == null) {
            return false;
        }

        size--;

        if (current.left == null || current.right == null) {
            Node<K, V> newChild = (current.left != null) ? current.left : current.right;

            if (parent == null) {
                root = newChild;
            } else if (parent.left == current) {
                parent.left = newChild;
            } else {
                parent.right = newChild;
            }
        } else {
            Node<K, V> successorParent = current;
            Node<K, V> successor = current.right;

            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }

            if (successorParent != current) {
                successorParent.left = successor.right;
            } else {
                successorParent.right = successor.right;
            }

            current.key = successor.key;
            current.value = successor.value;
        }

        return true;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<Node<K, V>> iterator() {
        return new BSTIterator();
    }

    private class BSTIterator implements Iterator<Node<K, V>> {
        private Stack<Node<K, V>> stack;

        public BSTIterator() {
            stack = new Stack<>();
            Node<K, V> current = root;
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Node<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Node<K, V> node = stack.pop();
            Node<K, V> current = node.right;
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            return node;
        }
    }
}
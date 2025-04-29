package src;

import java.util.ArrayList;
import java.util.List;
// for commit
public class MyHashTable<K, V> {
    private static class HashNode<K, V> {
        K key;
        V value;
        HashNode<K, V> next;

        HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + "=" + value + "}";
        }
    }

    private List<HashNode<K, V>>[] buckets;
    private final int capacity;
    private int size;

    @SuppressWarnings("unchecked")
    public MyHashTable() {
        this(11);
    }

    @SuppressWarnings("unchecked")
    public MyHashTable(int capacity) {
        this.capacity = capacity;
        this.buckets = new List[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new ArrayList<>();
        }
        this.size = 0;
    }


    private int hash(K key) {
        return Math.abs(key.hashCode() % capacity);
    }


    public void put(K key, V value) {
        int index = hash(key);
        List<HashNode<K, V>> bucket = buckets[index];


        for (HashNode<K, V> node : bucket) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }


        HashNode<K, V> newNode = new HashNode<>(key, value);
        bucket.add(0, newNode);
        size++;
    }


    public V get(K key) {
        int index = hash(key);
        List<HashNode<K, V>> bucket = buckets[index];

        for (HashNode<K, V> node : bucket) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }


    public V remove(K key) {
        int index = hash(key);
        List<HashNode<K, V>> bucket = buckets[index];

        for (int i = 0; i < bucket.size(); i++) {
            HashNode<K, V> node = bucket.get(i);
            if (node.key.equals(key)) {
                bucket.remove(i);
                size--;
                return node.value;
            }
        }
        return null;
    }


    public boolean contains(V value) {
        for (List<HashNode<K, V>> bucket : buckets) {
            for (HashNode<K, V> node : bucket) {
                if (node.value.equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }


    public K getKey(V value) {
        for (List<HashNode<K, V>> bucket : buckets) {
            for (HashNode<K, V> node : bucket) {
                if (node.value.equals(value)) {
                    return node.key;
                }
            }
        }
        return null;
    }


    public int getBucketSize(int index) {
        return buckets[index].size();
    }


    public List<K> getKeysInBucket(int index) {
        List<K> keys = new ArrayList<>();
        for (HashNode<K, V> node : buckets[index]) {
            keys.add(node.key);
        }
        return keys;
    }

    public int size() {
        return size;
    }
}

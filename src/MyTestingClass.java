package src;

import java.util.Random;

public class MyTestingClass {
    private String name;
    private int id;

    public MyTestingClass(String name, int id) {
        this.name = name;
        this.id = id;
    }


    @Override
    public int hashCode() {
        int hash = 37;
        hash = hash * 41 + name.hashCode();
        hash = hash * 43 + Integer.hashCode(id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MyTestingClass other = (MyTestingClass) obj;
        return id == other.id && name.equals(other.name);
    }

    @Override
    public String toString() {
        return name + "-" + id;
    }

    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Integer> table = new MyHashTable<>(50); // 50 бакетов
        Random random = new Random();


        for (int i = 0; i < 10000; i++) {
            String name = "Name" + random.nextInt(20000);
            int id = random.nextInt(20000);
            MyTestingClass key = new MyTestingClass(name, id);
            table.put(key, i);
        }


        System.out.println("Total elements: " + table.size());


        int minSize = Integer.MAX_VALUE;
        int maxSize = Integer.MIN_VALUE;
        for (int i = 0; i < 50; i++) {
            int bucketSize = table.getBucketSize(i);
            System.out.println("Bucket " + i + " size: " + bucketSize + ", Keys: " + table.getKeysInBucket(i));
            minSize = Math.min(minSize, bucketSize);
            maxSize = Math.max(maxSize, bucketSize);
        }


        System.out.println("Min bucket size: " + minSize);
        System.out.println("Max bucket size: " + maxSize);
        System.out.println("Average bucket size: " + (table.size() / 50.0));
    }
}

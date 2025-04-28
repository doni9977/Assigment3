package src;
import java.util.Random;

public class TestBST {
    public static void main(String[] args) {
        BST<Integer, String> bst = new BST<>();
        Random random = new Random();

        System.out.println("Is tree empty? " + bst.isEmpty());

        for (int i = 0; i < 20; i++) {
            int key = random.nextInt(100);
            bst.put(key, "Value" + key);
            System.out.println("Inserted: key=" + key + ", value=Value" + key);
        }

        System.out.println("\nIs tree empty? " + bst.isEmpty());

        int testKey = random.nextInt(100);
        System.out.println("Does key " + testKey + " exist? " + bst.containsKey(testKey));

        System.out.println("\nIn-order traversal:");
        for (var node : bst) {
            System.out.println("Key: " + node.getKey() + ", Value: " + node.getValue());
        }

        System.out.println("\nTotal size: " + bst.size());

        // Тест с null-ключом
        bst.put(null, "NullValue");
        System.out.println("Get with null key: " + bst.get(null));
        System.out.println("Remove with null key: " + bst.remove(null));
    }
}
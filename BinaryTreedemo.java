import java.util.Scanner;

// Node class for the tree
class Node {
    int key;
    Node left, right;

    public Node(int item) {
        key = item;
        left = right = null;
    }
}

class BinaryTree {
    Node root;

    BinaryTree() {
        root = null;
    }

    // Insert a key into the BST
    void insert(int key) {
        root = insertRec(root, key);
    }

    // A recursive function to insert a new key in BST
    Node insertRec(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }
        if (key < root.key) {
            root.left = insertRec(root.left, key);
        } else if (key > root.key) {
            root.right = insertRec(root.right, key);
        }
        return root;
    }

    // Search for a key in the BST
    boolean search(int key) {
        Node current = root;
        while (current != null) {
            if (key == current.key) {
                return true;
            } else if (key < current.key) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }

    // In-order Traversal: Left -> Root -> Right
    void inorder() {
        inorderRec(root);
        System.out.println();
    }

    void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.key + " ");
            inorderRec(root.right);
        }
    }

    // Pre-order Traversal: Root -> Left -> Right
    void preorder() {
        preorderRec(root);
        System.out.println();
    }

    void preorderRec(Node root) {
        if (root != null) {
            System.out.print(root.key + " ");
            preorderRec(root.left);
            preorderRec(root.right);
        }
    }

    // Post-order Traversal: Left -> Right -> Root
    void postorder() {
        postorderRec(root);
        System.out.println();
    }

    void postorderRec(Node root) {
        if (root != null) {
            postorderRec(root.left);
            postorderRec(root.right);
            System.out.print(root.key + " ");
        }
    }
}

public class BinaryTreeDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinaryTree tree = new BinaryTree();

        System.out.print("Enter the number of elements to insert: ");
        int n = scanner.nextInt();
        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            tree.insert(scanner.nextInt());
        }

        System.out.println("\n--- Traversals ---");
        System.out.print("Pre-order traversal: ");
        tree.preorder();

        System.out.print("In-order traversal: ");
        tree.inorder();

        System.out.print("Post-order traversal: ");
        tree.postorder();
        
        System.out.println("\n--- Search Operation ---");
        System.out.print("Enter the key to search for: ");
        int keyToSearch = scanner.nextInt();

        if (tree.search(keyToSearch)) {
            System.out.println("Key " + keyToSearch + " found in the tree.");
        } else {
            System.out.println("Key " + keyToSearch + " not found in the tree.");
        }
        
        scanner.close();
    }
}

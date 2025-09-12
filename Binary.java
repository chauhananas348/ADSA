
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Node {
    int data;
    Node left, right;

    public Node(int item) {
        data = item;
        left = right = null;
    }
}

class BinaryTree {
    Node root;

    BinaryTree() {
        root = null;
    }

    // Method to insert a new node
    public void insert(int data) {
        root = insertRec(root, data);
    }

    // A recursive function to insert a new key in a BST
    private Node insertRec(Node root, int data) {
        if (root == null) {
            root = new Node(data);
            return root;
        }

        if (data < root.data)
            root.left = insertRec(root.left, data);
        else if (data > root.data)
            root.right = insertRec(root.right, data);

        return root;
    }

    // Pre-order Traversal
    public void preorderTraversal(Node node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preorderTraversal(node.left);
            preorderTraversal(node.right);
        }
    }

    // In-order Traversal
    public void inorderTraversal(Node node) {
        if (node != null) {
            inorderTraversal(node.left);
            System.out.print(node.data + " ");
            inorderTraversal(node.right);
        }
    }

    // Post-order Traversal
    public void postorderTraversal(Node node) {
        if (node != null) {
            postorderTraversal(node.left);
            postorderTraversal(node.right);
            System.out.print(node.data + " ");
        }
    }
}

public class Binary {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinaryTree tree = new BinaryTree();

        System.out.println("Enter numbers to build the binary tree (enter a non-integer to stop):");
        while (scanner.hasNextInt()) {
            tree.insert(scanner.nextInt());
        }
        scanner.close();

        System.out.print("\nPre-order Traversal: ");
        tree.preorderTraversal(tree.root);

        System.out.print("\nIn-order Traversal: ");
        tree.inorderTraversal(tree.root);

        System.out.print("\nPost-order Traversal: ");
        tree.postorderTraversal(tree.root);

        System.out.println();
    }
}
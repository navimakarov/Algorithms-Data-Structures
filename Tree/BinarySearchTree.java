import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree {
    static class Node {
        Node left, right;
        int val;

        public Node(int val) {
            this.val = val;
            left = null;
            right = null;
        }
    }

    Node root;
    public BinarySearchTree() {
        root = null;
    }

    // O(h), h - height of BST, worst case - O(n)
    public void add(int value) {
        root = addRecursive(root, value);
    }

    private Node addRecursive(Node current, int value) {
        if(current == null) {
            return new Node(value);
        }
        if(value < current.val)
            current.left = addRecursive(current.left, value);
        else if(value > current.val)
            current.right = addRecursive(current.right, value);
        else
            return current;

        return current;
    }

    // O(h), h - height of BST, worst case - O(n)
    public boolean containsNode(int val) {
        return containsNodeRecursive(root, val);
    }

    private boolean containsNodeRecursive(Node current, int value) {
        if(current == null)
            return false;

        if(current.val == value)
            return true;

        return value < current.val
                ? containsNodeRecursive(current.left, value)
                : containsNodeRecursive(current.right, value);
    }

    // O(h), h - height of BST, worst case - O(n)
    public void delete(int value) {
        root = deleteRecursive(root, value);
    }

    private Node deleteRecursive(Node current, int val) {
        if(current.val == val) {
            if(current.left == null && current.right == null)
                return null;
            if(current.right == null)
                return current.left;
            if(current.left == null)
                return current.right;

            int smallestValue = findSmallestValue(current.right);
            current.val = smallestValue;
            current.right = deleteRecursive(current.right, smallestValue);
            return current;

        }

        if(val < current.val) {
            deleteRecursive(current.left, val);
            return current;
        }
        deleteRecursive(current.right, val);
        return current;
    }

    private int findSmallestValue(Node current) {
        return current.left == null ? current.val : findSmallestValue(current.left);
    }

    /**
     * DFS
     */
    //---------------------------------------------------------
    public void traverseInOrder(Node node) {
        if (node != null) {
            traverseInOrder(node.left);
            System.out.print(" " + node.val);
            traverseInOrder(node.right);
        }
    }

    public void traversePreOrder(Node node) {
        if (node != null) {
            System.out.print(" " + node.val);
            traversePreOrder(node.left);
            traversePreOrder(node.right);
        }
    }

    public void traversePostOrder(Node node) {
        if (node != null) {
            traversePostOrder(node.left);
            traversePostOrder(node.right);
            System.out.print(" " + node.val);
        }
    }
    //---------------------------------------------------------
    /**
     * BFS
     */
    //---------------------------------------------------------
    public void traverseLevelOrder() {
        if (root == null) {
            return;
        }

        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);

        while (!nodes.isEmpty()) {

            Node node = nodes.remove();

            System.out.print(" " + node.val);

            if (node.left != null) {
                nodes.add(node.left);
            }

            if (node.right != null) {
                nodes.add(node.right);
            }
        }
    }
    //---------------------------------------------------------
}

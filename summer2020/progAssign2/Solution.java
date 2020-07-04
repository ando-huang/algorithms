import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class AVLNode {
    public String key;
    public int value;
    public AVLNode left;
    public AVLNode right;
    public int height;
    // below are added augmentations for assignment
    public int sign; // bool used for flip
    public String leftMax; // key used for flip
    public String rightMin; // key used for flip

    AVLNode(String key, int value) {
        this.key = key;
        this.value = value;
        this.height = 1;
        this.sign = 1; // false means -1^0, no sign change
    }
}

class AVLTree {
    public static AVLNode root;

    public static int getHeight(AVLNode node) {
        if (node == null)
            return 0;
        return node.height;
    }

    public static void updateInfo(AVLNode node) { // costs O(1)
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    public static void setRightMin(AVLNode node) { // costs logn
        AVLNode curr = node.right;
        while (curr.left != null) {
            curr = curr.left;
        }
        node.rightMin = curr.key;
    }

    public static void setLeftMax(AVLNode node) { // costs logn
        AVLNode curr = node.left;
        while (curr.right != null) {
            curr = curr.right;
        }
        node.leftMax = curr.key;
    }

    public static AVLNode rightRotate(AVLNode node) {
        pushdown(node);
        AVLNode nodel = node.left;
        AVLNode nodelr = nodel.right;
        nodel.right = node;
        node.left = nodelr;
        updateInfo(node);
        updateInfo(nodel);

        setLeftMax(node);
        setRightMin(node);
        // pushdown(node);
        setLeftMax(nodel);
        setRightMin(nodel);
        // pushdown(nodel);
        // maybe for nodelr too
        return nodel;
    }

    public static AVLNode leftRotate(AVLNode node) {
        pushdown(node);
        AVLNode noder = node.right;
        AVLNode noderl = noder.left;
        noder.left = node;
        node.right = noderl;
        updateInfo(node);
        updateInfo(noder);

        setLeftMax(node);
        setRightMin(node);
        // pushdown(node);
        setLeftMax(noder);
        setRightMin(noder);
        // pushdown(noder);
        // maybe for noderl too
        return noder;
    }

    public static int getBalance(AVLNode node) {
        if (node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    public static AVLNode doInsert(AVLNode node, String key, int value) {
        if (node == null)
            return new AVLNode(key, value);
        if (key.compareTo(node.key) < 0)
            node.left = doInsert(node.left, key, value);
        else if (key.compareTo(node.key) > 0)
            node.right = doInsert(node.right, key, value);
        else {
            node.value = value;
            return node;
        }
        updateInfo(node);
        int balance = getBalance(node);
        if (balance > 1 && key.compareTo(node.left.key) < 0)
            return rightRotate(node);
        if (balance < -1 && key.compareTo(node.right.key) > 0)
            return leftRotate(node);
        if (balance > 1 && key.compareTo(node.left.key) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && key.compareTo(node.right.key) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        // minmax updates might be unnecessary but whatever
        setLeftMax(node);
        setRightMin(node);
        pushdown(node);
        return node;
    }

    public void insert(String key, int value) {
        root = doInsert(root, key, value);
    }

    public static int find(String key) {
        return doFind(root, key);
    }

    public static int doFind(AVLNode node, String key) {
        if (node == null)
            return -1;
        if (key.equals(node.key))
            return node.value;
        else if (key.compareTo(node.key) < 0)
            return doFind(node.left, key);
        return doFind(node.right, key);
    }

    /**
     * 
     * @param node   - starting node from which the flip is being called upon
     * @param key    - lower limit of flip (inclusive)
     * @param endKey - upper limit of the flip (inclusive)
     * 
     * @author Andrew Huang
     */
    public static void doFlip(AVLNode node, String key, String endKey) {
        if (node == null) {
            return; // nothing to do
        }

        // mess with logic a bit here, compare to max of the left tree and min of right

    }

    /**
     * flips the signs of all nodes(stocks) within range [a,b]
     * 
     * @param key    - starting key a (inclusive)
     * @param endKey - ending key b (inclusive)
     * 
     * @author Andrew Huang
     */
    public static void flip(String key, String endKey) {
        doFlip(root, key, endKey);
    }

    public static void pushdown(AVLNode node) {
        if (node.sign == -1) {
            node.sign *= -1;
        } else {
            node.left.sign *= node.sign;
            node.right.sign *= node.sign;
            node.sign = 1;
        }
    }

    AVLTree() {
        root = null;
    }
}

public class Solution {

    public static void main(String[] args) throws IOException {
        AVLTree stonks = new AVLTree();

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String input = in.readLine();
        int n = Integer.parseInt(input);

        for (int i = 0; i < n; i++) {
            input = in.readLine();
            String[] data = input.split(" ");
            int operation = Integer.parseInt(data[0]);

            if (operation == 1) {
                stonks.insert(data[1], Integer.parseInt(data[2]));
            }

            else if (operation == 2){
                stonks.flip(data[1], data[2]);
            }

            else if (operation == 3){
                stonks.find(data[1]);
            }
        }
        


        /**
         *  1st function: Insert( a , k ){
         *      inserts a new stock (node) into the tree with associated value k
         *  }
         * 
         *  2nd function: flip( a ,  b ){
         *      flips the sign of all the values in range [a,b] (inclusive)
         *  }
         * 
         *  3rd function: find( a ){
         *      travels to node with key a, and returns value k associated with it.
         *  }
         * 
         * 
         *  Input/Output
         *  
         *  Input:
         *  1 a k -> insert node with key a, value k
         *  2 a b -> flips sign value of all nodes in range [a,b]
         *  3 a   -> returns the value associated with key a
         * 
         * 
         */ 
    }
}
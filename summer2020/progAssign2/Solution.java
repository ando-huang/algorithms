import java.io.*;
//import java.util.*;

class AVLNode {
    public String key;
    public int value;
    public AVLNode left;
    public AVLNode right;
    public int height;
    // below are added augmentations for assignment
    public int sign; // bool used for flip
    public String min;
    public String max;

    AVLNode(String key, int value) {
        this.key = key;
        this.value = value;
        this.height = 1;
        this.right = null;
        this.left = null;
        this.sign = 1; // false means -1^0, no sign change
        this.min = key;
        this.max = key;
    }
}

class AVLTree {
    public static AVLNode root;

    public static void inorderPrint(AVLNode node){
        if(node == null){return;}
        inorderPrint(node.left);
        System.out.println(node.key);
        inorderPrint(node.right);
    }

    public static int getHeight(AVLNode node) {
        if (node == null)
            return 0;
        return node.height;
    }

    public static void updateInfo(AVLNode node) { // costs O(1)
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        if(node.right != null){
            node.max = node.right.max;
        }
        else{ node.max = node.key; }
        if(node.left != null){
            node.min = node.left.min;
        }
        else{ node.min = node.key; }
        //System.out.println(node.key + " min " + node.min);
        //System.out.println(node.key + " max " + node.max);
    }

    /**
     * helps redistribute the signs for all the nodes during operations
     * @param node
     */
    public static void pushdown(AVLNode node) {
        if(node == null){
            return;
        }
        if (node.sign == -1) { //if the sign was flipped, directly apply the flip to the value
            node.value *= -1;
        }
        if(node.left  != null){ node.left.sign *= node.sign; }
        if(node.right != null){ node.right.sign *= node.sign; }
        node.sign = 1;
    }

    public static AVLNode rightRotate(AVLNode node) {
        pushdown(node);
        AVLNode nodel = node.left;
        AVLNode nodelr = nodel.right;
        nodel.right = node;
        node.left = nodelr;
        updateInfo(node);
        updateInfo(nodel);
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
        return noder;
    }

    public static int getBalance(AVLNode node) { //do not change
        if (node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    public static AVLNode doInsert(AVLNode node, String key, int value) {
        if (node == null)
            return new AVLNode(key, value);
        
        pushdown(node);
        
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
        return node;
    }

    public void insert(String key, int value) {
        root = doInsert(root, key, value);
    }

    public int find(String key) {
        return doFind(root, key);
    }

    //modified to include the carry sign
    public static int doFind(AVLNode node, String key) {
        if (node == null)
            return -1;
        pushdown(node);
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
        pushdown(node);
        if(key.compareTo(node.min) <= 0 && endKey.compareTo(node.max)>=0){ //whole subtree is within
            node.sign *= -1;
            return;
        }
        else if(key.compareTo(node.max) > 0 || endKey.compareTo(node.min) < 0){
            return;
        }
        else{
            if(key.compareTo(node.key) <= 0 && endKey.compareTo(node.key) >= 0){
                node.value *= -1;     
            }
            doFlip(node.left, key, endKey);
            doFlip(node.right, key, endKey);
        }
    }

    /**
     * flips the signs of all nodes(stocks) within range [a,b]
     * 
     * @param key    - starting key a (inclusive)
     * @param endKey - ending key b (inclusive)
     * 
     * @author Andrew Huang
     */
    public void flip(String key, String endKey) {
        //make sure the keys are in order, just housekeeping
        if(key.compareTo(endKey) < 0){doFlip(root, key, endKey);}
        else{ //equivalent keys or endkey comes before key
            doFlip(root, endKey, key);
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
        int n = Integer.parseInt(input); //size of list of inputs

        for (int i = 0; i < n; i++) {
            input = in.readLine(); //resetting the input every time
            String[] data = input.split(" "); //creates an array of strings with the inputs
            int operation = Integer.parseInt(data[0]); //not needed but just for simplicity

            if (operation == 1) {
                stonks.insert(data[1], Integer.parseInt(data[2])); //insert(k, v)
            }

            else if (operation == 2) {
                stonks.flip(data[1], data[2]);                     //flip(k, j)
            }

            else if (operation == 3) {
                int x = stonks.find(data[1]);                      //find(k)
                System.out.println(x);
            }
        }
        //done with the reading
        in.close();
    }
}

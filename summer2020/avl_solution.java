import java.io.*;
import java.util.*;

class AVLNode{
    public String key;
    public int value;
    public AVLNode left;
    public AVLNode right;
    public int height;
    AVLNode(String key, int value){
        this.key=key;
        this.value=value;
        this.height=1;
    }
}

class AVLTree{
    public static AVLNode root;

    public static int getHeight(AVLNode node){
        if(node == null)
            return 0;
        return node.height;
    }

    public static void updateInfo(AVLNode node){
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    public static AVLNode rightRotate(AVLNode node) {
        AVLNode nodel = node.left;
        AVLNode nodelr = nodel.right;
        nodel.right = node;
        node.left = nodelr;
        updateInfo(node);
        updateInfo(nodel);
        return nodel;
    }

    public static AVLNode leftRotate(AVLNode node) {
        AVLNode noder = node.right;
        AVLNode noderl = noder.left;
        noder.left = node;
        node.right = noderl;
        updateInfo(node);
        updateInfo(noder);
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
        return node;
    }

    public static void insert(String key, int value) { root = doInsert(root, key, value); }

    public static int find(String key) { return doFind(root, key); }

    public static int doFind(AVLNode node, String key) {
        if (node == null)
            return -1;
        if (key.equals(node.key))
            return node.value;
        else if (key.compareTo(node.key) < 0)
            return doFind(node.left, key);
        return doFind(node.right, key);
    }

    AVLTree(){
        root = null;
    }
}

public class avl_solution{
    public static void main(String args[]){
        AVLTree tree = new AVLTree();
        Scanner sc = new Scanner(System.in);
        int queries = sc.nextInt();
        for(int q = 0; q < queries; q++){
            int t = sc.nextInt();
            if(t == 1){
                String name = sc.next();
                int value = sc.nextInt();
                tree.insert(name, value);
            }
            else{
                String name = sc.next();
                System.out.println(tree.find(name));
            }
        }
    }
}
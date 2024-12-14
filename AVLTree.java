/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.avltree;

/**
 *
 * @author Mujtaba Abr
 */
public class AVLTree {


    private Node root;

    private class Node {
        int key;
        Node left;
        Node right;
        int height;

        Node(int key) {
            this.key = key;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }
    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }
    private void updateHeight(Node node) {
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }
    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }
    private Node rotateLeft(Node node) {
        Node temp = node.right;
        node.right = temp.left;
        temp.left = node;

        updateHeight(node);
        updateHeight(temp);

        return temp;
    }

    private Node rotateRight(Node node) {
        Node temp = node.left;
        node.left = temp.right;
        temp.right = node;

        updateHeight(node);
        updateHeight(temp);

        return temp;
    }
    private Node rebalance(Node node) {
        int balanceFactor = getBalanceFactor(node);

        if (balanceFactor > 1) {
            if (getBalanceFactor(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            node = rotateRight(node);
        } else if (balanceFactor < -1) {
            if (getBalanceFactor(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            node = rotateLeft(node);
        }

        return node;
    }

    public void insert(int key) {
        root = insert(root, key);
    }
private Node insert(Node node, int key) {
        if (node == null) {
            return new Node(key);
        }
if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        } else {
            // Key already exists
            return node;
        }
updateHeight(node);
        node = rebalance(node);

        return node;
    }

    public void delete(int key) {
        root = delete(root, key);
    }
 private Node delete(Node node, int key) {
        if (node == null) {
            return null;
        }

        if (key < node.key) {
            node.left = delete(node.left, key);
        } else if (key > node.key) {
            node.right = delete(node.right, key);
        } else {
 
            if (node.left == null && node.right == null) {
                return null;
            }
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }

            Node temp = node.right;
            while (temp.left != null) {
                temp = temp.left;
            }
            node.key = temp.key;
            node.right = delete(node.right, temp.key);
        }

        updateHeight(node);
        node = rebalance(node);

        return node;
    }

    public boolean search(int key) {
        return search(root, key);
    }

    private boolean search(Node node, int key) {
        if (node == null) {
            return false;
        }

        if (key < node.key) {
            return search(node.left, key);
        } else if (key > node.key) {
            return search(node.right, key);
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);
        tree.insert(25);

        System.out.println("Search 30: " + tree.search(30)); // true
        System.out.println("Search 35: " + tree.search(35)); // false

        tree.delete(20);

        System.out.println("Search 20: " + tree.search(20)); // false

    
    }  
   

}


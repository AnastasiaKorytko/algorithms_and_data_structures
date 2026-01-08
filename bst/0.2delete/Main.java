package org.example;

import java.io.*;
import java.util.ArrayList;

class Node{
    int data;
    Node left;
    Node right;

    public Node(int data){
        this.data = data;
        left = null;
        right = null;
    }
}

class BinaryTree {
    private Node rootNode;
    public ArrayList<Integer> arr = new ArrayList<>();

    public BinaryTree() {
        rootNode = null;
    }

    public void insert(int num) {
        Node newNode = new Node(num);
        if (rootNode == null) {
            rootNode = newNode;
            return;
        }
        Node prNode = null;
        Node currNode = rootNode;
        while (currNode != null) {
            prNode = currNode;
            if (num > currNode.data) {
                currNode = currNode.right;
            } else if (num < currNode.data) {
                currNode = currNode.left;
            } else
                return;
        }
        if (num > prNode.data) {
            prNode.right = newNode;
        } else if (num < prNode.data) {
            prNode.left = newNode;
        }
    }

    public void preOrderTraversal() {
        arr.clear();
        if (rootNode == null)
            return;
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(rootNode);
        while (!(nodes.isEmpty())){
            Node n = nodes.remove(nodes.size() - 1);
            arr.add(n.data);
            if (n.right != null) {
                nodes.add(n.right);
            }
            if (n.left != null) {
                nodes.add(n.left);
            }

        }

    }

    public void deleteList(Node node){
        Node parentNode = prSearch(node);
        if (parentNode == null){
            rootNode = null;
        }
        else if (node == parentNode.right) {
            parentNode.right = null;
        }
        else if (node == parentNode.left) {
            parentNode.left = null;
        }

    }

    public void deleteOneTree(Node node){
        Node parentNode = prSearch(node);
        Node nextNode = null;
        if (node.right == null){
            nextNode = node.left;
        }
        else if (node.left == null){
            nextNode = node.right;
        }
        if (parentNode == null){
            rootNode = nextNode;
        }
        else if (node == parentNode.right) {
            parentNode.right = nextNode;
        }
        else if (node == parentNode.left) {
            parentNode.left = nextNode;
        }

    }

    public void rightDelete(Node node){
        Node rightNode = node.right;
        Node prRight = node;
        while (rightNode.left != null){
            prRight = rightNode;
            rightNode = rightNode.left;
        }
        node.data = rightNode.data;
        if (prRight == node){
            prRight.right = rightNode.right;
        }
        else {
            prRight.left = rightNode.right;
        }

    }

    public Node search(int num){
        if (rootNode == null) {
            return null;
        }
        Node prNode = null;
        Node currNode = rootNode;
        Node findNode;
        while (currNode != null) {
            prNode = currNode;
            if (num > currNode.data) {
                currNode = currNode.right;
            } else if (num < currNode.data) {
                currNode = currNode.left;
            } else
                return currNode;
        }
        return null;
    }

    public Node prSearch(Node node){
        if (rootNode == null) {
            return null;
        }
        if (rootNode == node){
            return null;
        }
        Node prNode = null;
        Node currNode = rootNode;
        while (currNode != null) {
            if (node.data > currNode.data) {
                prNode = currNode;
                currNode = currNode.right;
            } else if (node.data < currNode.data) {
                prNode = currNode;
                currNode = currNode.left;
            } else
                return prNode;
        }
        return null;
    }

    public void delete(int num){
        Node node = search(num);
        if (node == null)
            return;
        if (node.left == null && node.right == null){
            deleteList(node);
        }
        else if ((node.left == null && node.right != null) || (node.left != null && node.right == null)){
            deleteOneTree(node);
        }
        else if (node.left != null && node.right != null){
            rightDelete(node);
        }
    }

}
public class Main {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        ArrayList<Integer> arr = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("input.txt"));
            int key = Integer.parseInt(br.readLine());
            String str = br.readLine();
            String line = br.readLine();
            while (line != null) {
                arr.add(Integer.parseInt(line));
                line = br.readLine();
            }
            for (int el : arr) {
                tree.insert(el);
            }
            File file = new File("output.txt");
            PrintWriter pw = new PrintWriter(file);
            tree.delete(key);
            tree.preOrderTraversal();
            for (int el : tree.arr) {
                pw.println(el);
            }
            pw.close();

        } catch (IOException e) {
            System.out.println("Error: " + e);
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                System.out.println("Error: " + e);
            }
        }
    }
}
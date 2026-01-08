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

}

    public class Main {
        public static void main(String[] args) {
            BinaryTree tree = new BinaryTree();
            ArrayList<Integer> arr = new ArrayList<>();
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader("input.txt"));
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



//    public  Node insertValue(int num, Node node){
//        if (node == null){
//            return new Node(num);
//        }
//        if (num > node.data){
//            node.right = insertValue(num, node.right);
//        }
//        else if (num < node.data){
//            node.left = insertValue(num, node.left);
//        }
//        return node;
//    }





//    public void preOrderTraversal(Node node) {
//        if (node != null){
//            arr.add(node.data);
//            preOrderTraversal(node.left);
//            preOrderTraversal(node.right);
//        }
//
//    }
//
//    public void preOrderTraversalSout(){
//        arr.clear();
//        preOrderTraversal(rootNode);
//    }


//package org.example;
//
//import java.util.ArrayList;
//
//public class BinaryTree{
//    private Node rootNode;
//    public ArrayList<Integer> arr = new ArrayList<>();
//
//    public BinaryTree(){
//        rootNode = null;
//    }
//
//    public  Node insertValue(int num, Node node){
//        if (node == null){
//            node = new Node(num);
//        }
//        if (num > node.data){
//            node.right = insertValue(num, node.right);
//        }
//        if (num < node.data){
//            node.left = insertValue(num, node.left);
//        }
//        return node;
//    }
//
//    public void insert(int num){
//       rootNode = insertValue(num, rootNode);
//    }
//
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
//        preOrderTraversal(rootNode);
//    }
//}

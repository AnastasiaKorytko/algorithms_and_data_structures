package org.example;

import java.io.*;
import java.util.*;

class Node{
    int data;
    Node left;
    Node right;
    int h;
    int minLeaf;
    int minpreLeaf;
    int msl;
    int minsum;

    public Node(int data){
        this.data = data;
        left = null;
        right = null;
        h = 0;
        minpreLeaf = Integer.MAX_VALUE;
    }
}

class BinaryTree {
    public Node rootNode;
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

    public int setH(Node node){
        if (node == null)
            return -1;
        int leftH = setH(node.left);
        int rightH = setH(node.right);
        node.h = Math.max(leftH, rightH) + 1;
        return node.h;
    }

    public int setMinLeaf(Node node) {
        if (node == null)
            return Integer.MAX_VALUE;
        if (node.left == null && node.right == null) {
            node.minLeaf = node.data;
            return node.minLeaf;
        }
        int leftMin = setMinLeaf(node.left);
        int rightMin = setMinLeaf(node.right);
        int leftH = (node.left != null) ? node.left.h : -1;
        int rightH = (node.right != null) ? node.right.h : -1;
        int res = Integer.MAX_VALUE;
        if (node.left != null && leftH == node.h - 1) res = Math.min(res, leftMin);
        if (node.right != null && rightH == node.h - 1) res = Math.min(res, rightMin);
        node.minLeaf = res;
        return res;
    }

//    public int setMinPreLeaf(Node node) {
//        if (node == null)
//            return Integer.MAX_VALUE;
//        if (node.h == 1) {
//            node.minpreLeaf = node.data;
//            return node.data;
//        }
//        int leftVal = setMinPreLeaf(node.left);
//        int rightVal = setMinPreLeaf(node.right);
//        node.minpreLeaf = Math.min(leftVal, rightVal);
//        return node.minpreLeaf;
//    }

//    public int setMinPreLeaf(Node node) {
//        if (node == null)
//            return Integer.MAX_VALUE;
//        if (node.h == 1) {
//            node.minpreLeaf = node.data;
//            return node.data;
//        }
//        int res = Integer.MAX_VALUE;
//        int leftH = (node.left != null) ? node.left.h : -1;
//        int rightH = (node.right != null) ? node.right.h : -1;
//        if (node.left != null && leftH == node.h - 1) {
//            res = Math.min(res, setMinPreLeaf(node.left));
//        }
//        if (node.right != null && rightH == node.h - 1) {
//            res = Math.min(res, setMinPreLeaf(node.right));
//        }
//        node.minpreLeaf = res;
//        return res;
//    }


    public int setMinPreLeaf(Node node) {
        if (node == null)
            return Integer.MAX_VALUE;
        if (node.h == 1) {
            node.minpreLeaf = node.data;
            return node.data;
        }
        if (node.h == 0) {
            node.minpreLeaf = Integer.MAX_VALUE;
            return Integer.MAX_VALUE;
        }
        int leftVal = setMinPreLeaf(node.left);
        int rightVal = setMinPreLeaf(node.right);
        int res = Integer.MAX_VALUE;
        int leftH = (node.left != null) ? node.left.h : -1;
        int rightH = (node.right != null) ? node.right.h : -1;
        if (leftH == node.h - 1)
            res = Math.min(res, leftVal);
        if (rightH == node.h - 1)
            res = Math.min(res, rightVal);
        node.minpreLeaf = res;
        return res;
    }


    public int setMSL(Node node) {
        if (node == null)
            return -1;
        setMSL(node.left);
        setMSL(node.right);
        if (node.left == null && node.right == null) {
            node.msl = 0;
            return 0;
        } else if (node.left != null && node.right != null) {
            node.msl = node.left.h + node.right.h + 1;
            return node.left.h + node.right.h + 1;
        } else if (node.left != null) {
            node.msl = node.left.h + 1;
            return node.left.h + 1;
        } else {
            node.msl = node.right.h + 1;
            return node.right.h + 1;
        }
    }

    public void setMinSum(Node node) {
        if (node == null)
            return;
        setMinSum(node.left);
        setMinSum(node.right);
        if (node.left != null && node.right != null && node.right.minpreLeaf != Integer.MAX_VALUE && node.left.minpreLeaf!= Integer.MAX_VALUE) {
//            if (node != rootNode)
            node.minsum = Math.min(node.left.minLeaf + node.right.minpreLeaf, node.right.minLeaf + node.left.minpreLeaf);
//            else
//                node.minsum = Math.min(Math.min(node.left.minLeaf + node.right.minpreLeaf, node.right.minLeaf + node.left.minpreLeaf), node.data + node.minLeaf);

        } else if (node.left != null && node.right != null && node.right.minpreLeaf == Integer.MAX_VALUE){
            if (node != rootNode)
            node.minsum = node.right.minLeaf + node.left.minpreLeaf;
            else
                node.minsum = Math.min(node.right.minLeaf + node.left.minpreLeaf, node.data + node.minLeaf);
        }
        else if(node.left != null && node.right != null && node.left.minpreLeaf== Integer.MAX_VALUE){
            if (node != rootNode)
            node.minsum = node.left.minLeaf + node.right.minpreLeaf;
            else
                node.minsum = Math.min(node.left.minLeaf + node.right.minpreLeaf, node.data + node.minLeaf);
        }
        else if (node == rootNode && ((node.right!= null && node.left == null) || (node.left!= null && node.right == null))) {
            node.minsum = node.data + node.minLeaf;
        } else {
            node.minsum = Integer.MAX_VALUE;
        }
    }


    public Node setBest() {
        ArrayList<Node> bestNodes = new ArrayList<>();
        int[] bestMSL = {Integer.MIN_VALUE};
        int[] bestMinSum = {Integer.MAX_VALUE};
        computeBestNodes(rootNode, bestNodes, bestMSL, bestMinSum);
        int minRoot = Integer.MAX_VALUE;
        Node find = null;
        for (Node el : bestNodes){
            if (el.data < minRoot){
                minRoot = el.data;
            }
        }
        for (Node el : bestNodes){
            if (el.data == minRoot)
                find = el;
        }
        return find;
    }

    public void computeBestNodes (Node node, ArrayList<Node> bestNodes, int[] bestMSL, int[] bestMinSum) {
        if (node == null)
            return;
        computeBestNodes(node.left, bestNodes, bestMSL, bestMinSum);
        computeBestNodes(node.right, bestNodes, bestMSL, bestMinSum);
        if (node.msl > bestMSL[0]) {
            bestMSL[0] = node.msl;
            bestMinSum[0] = node.minsum;
            bestNodes.clear();
            bestNodes.add(node);
        } else if (node.msl == bestMSL[0]) {
            if (node.minsum < bestMinSum[0]) {
                bestMinSum[0] = node.minsum;
                bestNodes.clear();
                bestNodes.add(node);
            } else if (node.minsum == bestMinSum[0]) {
                bestNodes.add(node);
            }
        }
    }


    public ArrayList<Integer> buildHalfPath(Node root) {
        ArrayList<Integer> path = new ArrayList<>();
        ArrayList<Integer> path1 = new ArrayList<>();
        ArrayList<Integer> path2 = new ArrayList<>();
        path.add(root.data);
        if (root.left != null && root.right != null && root.right.minpreLeaf != Integer.MAX_VALUE && root.left.minpreLeaf != Integer.MAX_VALUE) {
            int sumLeftRight = root.left.minLeaf + root.right.minpreLeaf;
            int sumRightLeft = root.right.minLeaf + root.left.minpreLeaf;
            if (sumLeftRight < sumRightLeft) {
                collectPath(root.left, root.left.minLeaf, path);
                collectPath(root.right, root.right.minpreLeaf, path);
            } else if (sumLeftRight > sumRightLeft) {
                collectPath(root.right, root.right.minLeaf, path);
                collectPath(root.left, root.left.minpreLeaf, path);
            } else {
                //path.remove(0);
                path1.add(root.data);
                collectPath(root.left, root.left.minLeaf, path1);
                collectPath(root.right, root.right.minpreLeaf, path1);
                path2.add(root.data);
                collectPath(root.right, root.right.minLeaf, path2);
                collectPath(root.left, root.left.minpreLeaf, path2);
                int med1 = 0, med2= 0;
                if ( path1.size()% 2 == 1 && path1.size() > 1) {
                    Collections.sort(path1);
                    med1 = path1.get(path1.size()/2);
                }
                if ( path2.size()% 2 == 1 && path2.size() > 1) {
                    Collections.sort(path2);
                    med2 = path2.get(path2.size()/2);
                }
                if (med1 == med2 && med1 != 0 && med2 != 0){
                    collectPath(root.left, root.left.minLeaf, path);
                    collectPath(root.right, root.right.minpreLeaf, path);
                }
                else{
                    path.remove(0);
                }

            }
        }
        else if(root.left != null && root.right != null && root.right.minpreLeaf == Integer.MAX_VALUE && root.left.minpreLeaf != Integer.MAX_VALUE){
            int sumRoot = root.data + root.minLeaf;
            int sumRightLeft = root.right.minLeaf + root.left.minpreLeaf;
            if (sumRightLeft < sumRoot) {
                collectPath(root.right, root.right.minLeaf, path);
                collectPath(root.left, root.left.minpreLeaf, path);
            }
            else if (sumRoot < sumRightLeft){
                path.remove(0);
                collectPath(root, root.minLeaf, path);
            }
            else{
                //path.remove(0);
                path1.add(root.data);
                collectPath(root.right, root.right.minLeaf, path1);
                collectPath(root.left, root.left.minpreLeaf, path1);
                collectPath(root, root.minLeaf, path2);
                int med1 = 0, med2= 0;
                if ( path1.size()% 2 == 1 && path1.size() > 1) {
                    Collections.sort(path1);
                    med1 = path1.get(path1.size()/2);
                }
                if ( path2.size()% 2 == 1 && path2.size() > 1) {
                    Collections.sort(path2);
                    med2 = path2.get(path2.size()/2);
                }
                if (med1 == med2 && med1 != 0 && med2 != 0){
                    collectPath(root.right, root.right.minLeaf, path);
                    collectPath(root.left, root.left.minpreLeaf, path);
                }
                else{
                    path.remove(0);
                }
            }
        }
        else if(root.left != null && root.right != null && root.left.minpreLeaf == Integer.MAX_VALUE && root.right.minpreLeaf != Integer.MAX_VALUE){
            int sumRoot = root.data + root.minLeaf;
            int sumLeftRight = root.left.minLeaf + root.right.minpreLeaf;
            if (sumLeftRight < sumRoot) {
                collectPath(root.left, root.left.minLeaf, path);
                collectPath(root.right, root.right.minpreLeaf, path);
            }
            else if (sumRoot < sumLeftRight){
                path.remove(0);
                collectPath(root, root.minLeaf, path);
            }
            else{
                //path.remove(0);
                path1.add(root.data);
                collectPath(root.left, root.left.minLeaf, path1);
                collectPath(root.right, root.right.minpreLeaf, path1);
                collectPath(root, root.minLeaf, path2);
                int med1 = 0, med2= 0;
                if ( path1.size()% 2 == 1 && path1.size() > 1) {
                    Collections.sort(path1);
                    med1 = path1.get(path1.size()/2);
                }
                if ( path2.size()% 2 == 1 && path2.size() > 1) {
                    Collections.sort(path2);
                    med2 = path2.get(path2.size()/2);
                }
                if (med1 == med2 && med1 != 0 && med2 != 0){
                    collectPath(root.left, root.left.minLeaf, path);
                    collectPath(root.right, root.right.minpreLeaf, path);
                }
                else{
                    path.remove(0);
                }
            }
        }
        else if(root.left != null && root.right != null && root.left.minpreLeaf == Integer.MAX_VALUE && root.right.minpreLeaf == Integer.MAX_VALUE){
            if (root.right.data > root.left.data)
                collectPath(root.left, root.left.data, path);
                else
                    collectPath(root.right, root.right.data, path);
        } else if(root.left == null && root.right == null){
                return path;
        } else {
            Node child = (root.left != null) ? root.left : root.right;
            collectPath(child, child.minLeaf, path);
        }
        return path;
    }



    public boolean collectPath(Node node, int value, ArrayList<Integer> path) {
        if (node == null)
            return false;
        path.add(node.data);
        if (node.data == value)
            return true;
        if (collectPath(node.left, value, path))
            return true;
        if (collectPath(node.right, value, path))
            return true;
        path.remove(path.size() - 1);
        return false;
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
            br = new BufferedReader(new FileReader("tst.in"));
            String line = br.readLine();
            while (line != null){
                tree.insert(Integer.parseInt(line));
                line = br.readLine();
            }
            br.close();
            tree.setH(tree.rootNode);
            tree.setMinLeaf(tree.rootNode);
            tree.setMinPreLeaf(tree.rootNode);
            tree.setMSL(tree.rootNode);
            tree.setMinSum(tree.rootNode);
            System.out.println(tree.rootNode.minpreLeaf);
            System.out.println(tree.rootNode.minLeaf);
//            System.out.println(tree.rootNode.right.left.left.data);
//            System.out.println(tree.rootNode.left.h);
            //  System.out.println(tree.rootNode.left.minpreLeaf);
          //  System.out.println(tree.rootNode.left.minLeaf);
            //System.out.println(tree.rootNode.right.h);
            //System.out.println(tree.rootNode.right.minpreLeaf);
//            System.out.println(tree.rootNode.left.data);
            System.out.println(tree.rootNode.minsum);
//            System.out.println(tree.rootNode.msl);
            Node bestNode = tree.setBest();
            System.out.println(bestNode.data);
            ArrayList<Integer> hp = tree.buildHalfPath(bestNode);
            for ( int el : hp){
                System.out.print(el + " ");
            }
            if (!(hp.isEmpty()) && hp.size()% 2 == 1 && hp.size() > 1) {
                Collections.sort(hp);
                tree.delete(hp.get(hp.size()/2));
            }
            tree.preOrderTraversal();
            BufferedWriter bw = new BufferedWriter(new FileWriter("tst.out"));
            StringBuilder sb = new StringBuilder();
            for (int el : tree.arr) {
                sb.append(el).append('\n');
            }
            bw.write(sb.toString());
            bw.flush();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
}



//import java.io.*;
//import java.util.*;
//
//class Node{
//    int data;
//    Node left;
//    Node right;
//    int h;
//    long minLeaf;
//    long minpreLeaf;
//    long msl;
//    long minsum;
//
//    public Node(int data){
//        this.data = data;
//        left = null;
//        right = null;
//        h = 0;
//        minpreLeaf = Long.MAX_VALUE;
//        minLeaf = Long.MAX_VALUE;
//        minsum = Long.MAX_VALUE;
//    }
//}
//
//
//class BinaryTree {
//    public Node rootNode;
//    public ArrayList<Integer> arr = new ArrayList<>();
//
//    public BinaryTree() {
//        rootNode = null;
//    }
//
//    public void insert(int num) {
//        Node newNode = new Node(num);
//        if (rootNode == null) {
//            rootNode = newNode;
//            return;
//        }
//        Node prNode = null;
//        Node currNode = rootNode;
//        while (currNode != null) {
//            prNode = currNode;
//            if (num > currNode.data) {
//                currNode = currNode.right;
//            } else if (num < currNode.data) {
//                currNode = currNode.left;
//            } else
//                return;
//        }
//        if (num > prNode.data) {
//            prNode.right = newNode;
//        } else if (num < prNode.data) {
//            prNode.left = newNode;
//        }
//    }
//
//    public void preOrderTraversal() {
//        arr.clear();
//        if (rootNode == null)
//            return;
//        ArrayList<Node> nodes = new ArrayList<>();
//        nodes.add(rootNode);
//        while (!(nodes.isEmpty())){
//            Node n = nodes.remove(nodes.size() - 1);
//            arr.add(n.data);
//            if (n.right != null) {
//                nodes.add(n.right);
//            }
//            if (n.left != null) {
//                nodes.add(n.left);
//            }
//
//        }
//
//    }
//
//    public int setH(Node node){
//        if (node == null)
//            return -1;
//        int leftH = setH(node.left);
//        int rightH = setH(node.right);
//        node.h = Math.max(leftH, rightH) + 1;
//        return node.h;
//    }
//
//    public long setMinLeaf(Node node) {
//        if (node == null)
//            return Long.MAX_VALUE;
//        if (node.left == null && node.right == null) {
//            node.minLeaf = node.data;
//            return node.minLeaf;
//        }
//        long leftMin = setMinLeaf(node.left);
//        long rightMin = setMinLeaf(node.right);
//        int leftH = (node.left != null) ? node.left.h : -1;
//        int rightH = (node.right != null) ? node.right.h : -1;
//        long res = Long.MAX_VALUE;
//        if (node.left != null && leftH == node.h - 1)
//            res = Math.min(res, leftMin);
//        if (node.right != null && rightH == node.h - 1)
//            res = Math.min(res, rightMin);
//        node.minLeaf = res;
//        return res;
//    }
//
//
//
////    public int setMinPreLeaf(Node node) {
////        if (node == null)
////            return Integer.MAX_VALUE;
////        if (node.h == 1) {
////            node.minpreLeaf = node.data;
////            return node.data;
////        }
////        int res = Integer.MAX_VALUE;
////        int leftH = (node.left != null) ? node.left.h : -1;
////        int rightH = (node.right != null) ? node.right.h : -1;
////        if (node.left != null && leftH == node.h - 1) {
////            res = Math.min(res, setMinPreLeaf(node.left));
////        }
////        if (node.right != null && rightH == node.h - 1) {
////            res = Math.min(res, setMinPreLeaf(node.right));
////        }
////        node.minpreLeaf = res;
////        return res;
////    }
//
//    public long setMinPreLeaf(Node node) {
//        if (node == null)
//            return Long.MAX_VALUE;
//        if (node.h == 1) {
//            node.minpreLeaf = node.data;
//            return node.data;
//        }
//        long leftVal = setMinPreLeaf(node.left);
//        long rightVal = setMinPreLeaf(node.right);
//        node.minpreLeaf = Math.min(leftVal, rightVal);
//        return node.minpreLeaf;
//    }
//
//
//    public long setMSL(Node node) {
//        if (node == null)
//            return -1;
//        setMSL(node.left);
//        setMSL(node.right);
//        if (node.left == null && node.right == null) {
//            node.msl = 0;
//            return 0;
//        }
//        else if (node.left != null && node.right != null) {
//            node.msl = node.left.h + node.right.h + 1L;
//            return node.msl;
//        }
//        else if (node.left != null) {
//            node.msl = node.left.h + 1L;
//            return node.msl;
//        }
//        else {
//            node.msl = node.right.h + 1L;
//            return node.msl;
//        }
//    }
//
//
//    public void setMinSum(Node node) {
//        if (node == null)
//            return;
//        setMinSum(node.left);
//        setMinSum(node.right);
//        if (node.left != null && node.right != null && node.right.minpreLeaf != Long.MAX_VALUE && node.left.minpreLeaf  != Long.MAX_VALUE) {
//            long a = node.left.minLeaf + node.right.minpreLeaf;
//            long b = node.right.minLeaf + node.left.minpreLeaf;
//            node.minsum = Math.min(a, b);
//        }
//        else if (node.left != null && node.right != null && node.right.minpreLeaf == Long.MAX_VALUE) {
//            long a = node.right.minLeaf + node.left.minpreLeaf;
//            if (node != rootNode)
//                node.minsum = a;
//            else
//                node.minsum = Math.min(a, node.data + node.minLeaf);
//        }
//        else if (node.left != null && node.right != null && node.left.minpreLeaf == Long.MAX_VALUE) {
//            long a = node.left.minLeaf + node.right.minpreLeaf;
//            if (node != rootNode)
//                node.minsum = a;
//            else
//                node.minsum = Math.min(a, node.data + node.minLeaf);
//        }
//
//        else if (node == rootNode && ((node.right != null && node.left == null) || (node.left  != null && node.right == null))) {
//            node.minsum = node.data + node.minLeaf;
//        }
//        else {
//            node.minsum = Long.MAX_VALUE;
//        }
//    }
//
//    public Node setBest() {
//        ArrayList<Node> bestNodes = new ArrayList<>();
//        long[] bestMSL = {Long.MIN_VALUE};
//        long[] bestMinSum = {Long.MAX_VALUE};
//        computeBestNodes(rootNode, bestNodes, bestMSL, bestMinSum);
//        int minRoot = Integer.MAX_VALUE;
//        Node find = null;
//        for (Node el : bestNodes){
//            if (el.data < minRoot){
//                minRoot = el.data;
//            }
//        }
//        for (Node el : bestNodes){
//            if (el.data == minRoot)
//                find = el;
//        }
//        return find;
//    }
//
//
//    public void computeBestNodes(Node node, ArrayList<Node> bestNodes, long[] bestMSL, long[] bestMinSum) {
//        if (node == null)
//            return;
//        computeBestNodes(node.left, bestNodes, bestMSL, bestMinSum);
//        computeBestNodes(node.right, bestNodes, bestMSL, bestMinSum);
//        if (node.msl > bestMSL[0]) {
//            bestMSL[0] = node.msl;
//            bestMinSum[0] = node.minsum;
//            bestNodes.clear();
//            bestNodes.add(node);
//        }
//        else if (node.msl == bestMSL[0]) {
//            if (node.minsum < bestMinSum[0]) {
//                bestMinSum[0] = node.minsum;
//                bestNodes.clear();
//                bestNodes.add(node);
//            }
//            else if (node.minsum == bestMinSum[0]) {
//                bestNodes.add(node);
//            }
//        }
//    }
//
//
//
//    public ArrayList<Integer> buildHalfPath(Node root) {
//        ArrayList<Integer> path = new ArrayList<>();
//        path.add(root.data);
//        if (root.left != null && root.right != null && root.right.minpreLeaf != Long.MAX_VALUE && root.left.minpreLeaf != Long.MAX_VALUE) {
//            long sumLeftRight = root.left.minLeaf + root.right.minpreLeaf;
//            long sumRightLeft = root.right.minLeaf + root.left.minpreLeaf;
//            if (sumLeftRight < sumRightLeft) {
//                collectPath(root.left, (int) root.left.minLeaf, path);
//                collectPath(root.right, (int) root.right.minpreLeaf, path);
//            }
//            else if (sumLeftRight > sumRightLeft) {
//                collectPath(root.right, (int) root.right.minLeaf, path);
//                collectPath(root.left, (int) root.left.minpreLeaf, path);
//            }
//            else {
//                path.clear();
//            }
//        }
//        else if (root.left != null && root.right != null && root.right.minpreLeaf == Long.MAX_VALUE && root.left.minpreLeaf != Long.MAX_VALUE) {
//            long sumRoot = root.data + root.minLeaf;
//            long sumRightLeft = root.right.minLeaf + root.left.minpreLeaf;
//            if (sumRightLeft < sumRoot) {
//                collectPath(root.right, (int) root.right.minLeaf, path);
//                collectPath(root.left, (int) root.left.minpreLeaf, path);
//            }
//            else if (sumRoot < sumRightLeft){
//                path.clear();
//                collectPath(root, (int) root.minLeaf, path);
//            }
//            else {
//                path.clear();
//            }
//        }
//        else if (root.left != null && root.right != null && root.left.minpreLeaf == Long.MAX_VALUE && root.right.minpreLeaf != Long.MAX_VALUE){
//            long sumRoot = root.data + root.minLeaf;
//            long sumLeftRight = root.left.minLeaf + root.right.minpreLeaf;
//            if (sumLeftRight < sumRoot) {
//                collectPath(root.left, (int) root.left.minLeaf, path);
//                collectPath(root.right, (int) root.right.minpreLeaf, path);
//            }
//            else if (sumRoot < sumLeftRight){
//                path.clear();
//                collectPath(root, (int) root.minLeaf, path);
//            }
//            else {
//                path.clear();
//            }
//        }
//        else if (root.left != null && root.right != null && root.left.minpreLeaf == Long.MAX_VALUE && root.right.minpreLeaf == Long.MAX_VALUE) {
//            if (root.right.data > root.left.data)
//                collectPath(root.left, root.left.data, path);
//            else
//                collectPath(root.right, root.right.data, path);
//        }
//        else if (root.left == null && root.right == null){
//            return path;
//        }
//        else {
//            Node child = (root.left != null) ? root.left : root.right;
//            collectPath(child, (int) child.minLeaf, path);
//        }
//        return path;
//    }
//
//
//    public boolean collectPath(Node node, int value, ArrayList<Integer> path) {
//        if (node == null)
//            return false;
//        path.add(node.data);
//        if (node.data == value)
//            return true;
//        if (collectPath(node.left, value, path))
//            return true;
//        if (collectPath(node.right, value, path))
//            return true;
//        path.remove(path.size() - 1);
//        return false;
//    }
//
//    public void deleteList(Node node){
//        Node parentNode = prSearch(node);
//        if (parentNode == null){
//            rootNode = null;
//        }
//        else if (node == parentNode.right) {
//            parentNode.right = null;
//        }
//        else if (node == parentNode.left) {
//            parentNode.left = null;
//        }
//
//    }
//
//    public void deleteOneTree(Node node){
//        Node parentNode = prSearch(node);
//        Node nextNode = null;
//        if (node.right == null){
//            nextNode = node.left;
//        }
//        else if (node.left == null){
//            nextNode = node.right;
//        }
//        if (parentNode == null){
//            rootNode = nextNode;
//        }
//        else if (node == parentNode.right) {
//            parentNode.right = nextNode;
//        }
//        else if (node == parentNode.left) {
//            parentNode.left = nextNode;
//        }
//
//    }
//
//    public void rightDelete(Node node){
//        Node rightNode = node.right;
//        Node prRight = node;
//        while (rightNode.left != null){
//            prRight = rightNode;
//            rightNode = rightNode.left;
//        }
//        node.data = rightNode.data;
//        if (prRight == node){
//            prRight.right = rightNode.right;
//        }
//        else {
//            prRight.left = rightNode.right;
//        }
//
//    }
//
//    public Node search(int num){
//        if (rootNode == null) {
//            return null;
//        }
//        Node prNode = null;
//        Node currNode = rootNode;
//        Node findNode;
//        while (currNode != null) {
//            prNode = currNode;
//            if (num > currNode.data) {
//                currNode = currNode.right;
//            } else if (num < currNode.data) {
//                currNode = currNode.left;
//            } else
//                return currNode;
//        }
//        return null;
//    }
//
//    public Node prSearch(Node node){
//        if (rootNode == null) {
//            return null;
//        }
//        if (rootNode == node){
//            return null;
//        }
//        Node prNode = null;
//        Node currNode = rootNode;
//        while (currNode != null) {
//            if (node.data > currNode.data) {
//                prNode = currNode;
//                currNode = currNode.right;
//            } else if (node.data < currNode.data) {
//                prNode = currNode;
//                currNode = currNode.left;
//            } else
//                return prNode;
//        }
//        return null;
//    }
//
//    public void delete(int num){
//        Node node = search(num);
//        if (node == null)
//            return;
//        if (node.left == null && node.right == null){
//            deleteList(node);
//        }
//        else if ((node.left == null && node.right != null) || (node.left != null && node.right == null)){
//            deleteOneTree(node);
//        }
//        else if (node.left != null && node.right != null){
//            rightDelete(node);
//        }
//    }
//
//
//}
//public class Main {
//    public static void main(String[] args) {
//        BinaryTree tree = new BinaryTree();
//        ArrayList<Integer> arr = new ArrayList<>();
//        BufferedReader br = null;
//        try {
//            br = new BufferedReader(new FileReader("tst.in"));
//            String line = br.readLine();
//            while (line != null){
//                tree.insert(Integer.parseInt(line));
//                line = br.readLine();
//            }
//            br.close();
//            tree.setH(tree.rootNode);
//            tree.setMinLeaf(tree.rootNode);
//            tree.setMinPreLeaf(tree.rootNode);
//            tree.setMSL(tree.rootNode);
//            tree.setMinSum(tree.rootNode);
////            System.out.println(tree.rootNode.minpreLeaf);
////            System.out.println(tree.rootNode.right.left.left.data);
////            System.out.println(tree.rootNode.left.h);
////            System.out.println(tree.rootNode.left.minLeaf);
////            System.out.println(tree.rootNode.left.data);
////            System.out.println(tree.rootNode.minsum);
////            System.out.println(tree.rootNode.msl);
//            Node bestNode = tree.setBest();
//            ArrayList<Integer> hp = tree.buildHalfPath(bestNode);
////            for ( int el : hp){
////                System.out.print(el + " ");
////            }
//            if (!(hp.isEmpty()) && hp.size()% 2 == 1 && hp.size() > 1) {
//                Collections.sort(hp);
//                tree.delete(hp.get(hp.size()/2));
//            }
//            tree.preOrderTraversal();
//            BufferedWriter bw = new BufferedWriter(new FileWriter("tst.out"));
//            StringBuilder sb = new StringBuilder();
//            for (int el : tree.arr) {
//                sb.append(el).append('\n');
//            }
//            bw.write(sb.toString());
//            bw.flush();
//        } catch (IOException e) {
//            System.out.println("Error: " + e);
//        }
//    }
//}






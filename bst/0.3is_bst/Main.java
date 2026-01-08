package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Stack;

class Node{
    long data;
    Node left;
    Node right;

    public Node(long data){
        this.data = data;
        left = null;
        right = null;
    }
}

public class Main {

    public static boolean isBst(Node rootNode){
        Stack<Node> nodes = new Stack<>();
        Stack<Long> minValue = new Stack<>();
        Stack<Long> maxValue = new Stack<>();
        nodes.push(rootNode);
        minValue.push(Long.MIN_VALUE);
        maxValue.push(Long.MAX_VALUE);
        while(!(nodes.isEmpty())){
            Node node = nodes.pop();
            long min = minValue.pop();
            long max = maxValue.pop();
            if (node.data > max || node.data < min)
                return false;
            if (node.right != null){
                nodes.push(node.right);
                minValue.push(node.data);
                maxValue.push(max);
            }
            if (node.left != null){
                nodes.push(node.left);
                minValue.push(min);
                maxValue.push(node.data - 1);
            }
        }
        return true;
    }
    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("bst.in"));
            int n = Integer.parseInt(br.readLine());
            long rootNode = Long.parseLong(br.readLine());
            ArrayList<Node>tree = new ArrayList<>(n);
            tree.add(new Node(rootNode));
            for (int i = 1; i < n; i++){
                String[] parts = br.readLine().split(" ");
                long m = Long.parseLong(parts[0]);
                int p = Integer.parseInt(parts[1]) - 1;
                char c = parts[2].charAt(0);
                tree.add( new Node(m));
                Node pr = tree.get(p);
                if (c == 'R'){
                    pr.right = tree.get(i);
                }
                else if (c == 'L') {
                    pr.left = tree.get(i);
                }

            }
            File file = new File("bst.out");
            PrintWriter pw = new PrintWriter(file);
            if (isBst(tree.get(0)))
                pw.println("YES");
            else{
                pw.println("NO");
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



//import java.io.*;
//import java.util.*;
//
//class Node {
//    long data;
//    Node left, right;
//    Node(long data) { this.data = data; }
//}
//
//public class Main {
//
//    public static boolean isBst(Node root) {
//        Deque<Node> stack = new ArrayDeque<>();
//        Node curr = root;
//        Long prev = null; // предыдущее значение в обходе
//
//        while (curr != null || !stack.isEmpty()) {
//            // идём влево
//            while (curr != null) {
//                stack.push(curr);
//                curr = curr.left;
//            }
//
//            curr = stack.pop();
//
//            // проверяем порядок (in-order должен быть неубывающим)
//            if (prev != null && curr.data < prev)
//                return false;
//
//            prev = curr.data;
//            curr = curr.right;
//        }
//
//        return true;
//    }
//
//    public static void main(String[] args) throws IOException {
//        try (BufferedReader br = new BufferedReader(new FileReader("bst.in"));
//             PrintWriter pw = new PrintWriter("bst.out")) {
//
//            int n = Integer.parseInt(br.readLine());
//            long rootVal = Long.parseLong(br.readLine());
//            Node[] nodes = new Node[n];
//            nodes[0] = new Node(rootVal);
//
//            for (int i = 1; i < n; i++) {
//                String[] parts = br.readLine().split(" ");
//                long m = Long.parseLong(parts[0]);
//                int p = Integer.parseInt(parts[1]) - 1;
//                char c = parts[2].charAt(0);
//
//                nodes[i] = new Node(m);
//                if (c == 'L') nodes[p].left = nodes[i];
//                else nodes[p].right = nodes[i];
//            }
//
//            pw.println(isBst(nodes[0]) ? "YES" : "NO");
//        }
//    }
//}

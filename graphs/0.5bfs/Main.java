package org.example;
//import java.io.*;
//import java.util.*;
//
//public class Main {
//    public static void main(String[] args) throws IOException {
//        BufferedReader bw = new BufferedReader(new FileReader("input.txt"));
//        PrintWriter pw = new PrintWriter(new FileWriter("output.txt"));
//        int n = Integer.parseInt(bw.readLine());
//        int[][] graph = new int[n][n];
//        for (int i = 0; i < n; i++) {
//            String[] line = bw.readLine().split(" ");
//            for (int j = 0; j < n; j++) {
//                graph[i][j] = Integer.parseInt(line[j]);
//            }
//        }
//        int[] labels = new int[n];
//        boolean[] visited = new boolean[n];
//        Queue<Integer> queue = new LinkedList<>();
//        int currentLabel = 1;
//        queue.add(0);
//        visited[0] = true;
//        labels[0] = currentLabel++;
//        while (!queue.isEmpty()) {
//            int current = queue.poll();
//            List<Integer> neighbors = new ArrayList<>();
//            for (int i = 0; i < n; i++) {
//                if (graph[current][i] == 1 && !visited[i]) {
//                    neighbors.add(i);
//                }
//            }
//
//            // Сортируем по возрастанию (как требует условие)
//            Collections.sort(neighbors);
//
//            // Добавляем в очередь в правильном порядке
//            for (int neighbor : neighbors) {
//                if (!visited[neighbor]) {
//                    queue.add(neighbor);
//                    visited[neighbor] = true;
//                    labels[neighbor] = currentLabel++;
//                }
//            }
//        }
//
//        for (int i = 0; i < n; i++) {
//            pw.print(labels[i]);
//            if (i < n - 1) pw.print(" ");
//        }
//
//        bw.close();
//        pw.close();
//    }
//}

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("input.txt"));
            String line = br.readLine();
            int n = Integer.parseInt(line);
            int[][] graph = new int[n][n];
            for (int i = 0; i < n; i++) {
                line = br.readLine();
                String[] elements = line.split(" ");
                for (int j = 0; j < n; j++) {
                    graph[i][j] = Integer.parseInt(elements[j]);
                }
            }
            br.close();
            int[] labels = new int[n];
            for (int i = 0; i < n; i++){
                labels[i] = 0;
            }
            boolean[] visited = new boolean[n];
            for (int i = 0; i < n; i++){
                visited[i] = false;
            }
            Queue<Integer> queue = new LinkedList<>();
            int labelCount = 1;
            for (int start = 0; start < n; start++) {
                if (!visited[start]) {
                    queue.add(start);
                    visited[start] = true;
                    labels[start] = labelCount++;
                    while (!queue.isEmpty()) {
                        int curr = queue.poll();
                        for (int adj = 0; adj < n; adj++) {
                            if (graph[curr][adj] == 1 && !visited[adj]) {
                                queue.add(adj);
                                visited[adj] = true;
                                labels[adj] = labelCount++;
                            }
                        }
                    }
                }
            }
            PrintWriter pw = new PrintWriter(new FileWriter("output.txt"));
            for (int i = 0; i < n; i++) {
                pw.print(labels[i] + " ");
            }
            pw.close();
        } catch (IOException e) {
            System.out.println("error " + e);
        }
    }
}
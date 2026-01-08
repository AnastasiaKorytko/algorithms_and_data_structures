package org.example;

//import java.io.*;
//
//public class Main {
//
//    public static int minDistance(int[] distance, boolean[] visited) {
//        int min = Integer.MAX_VALUE;
//        int minInd = -1;
//        for (int i = 0; i < distance.length; i++) {
//            if (!visited[i] && distance[i] <= min) {
//                min = distance[i];
//                minInd = i;
//            }
//        }
//        return minInd;
//    }
//
//    public static void main(String[] args) {
//        BufferedReader br = null;
//        try{
//            br = new BufferedReader(new FileReader("input.txt"));
//            String line = br.readLine();
//            String[] tokens = line.split(" ");
//            int n = Integer.parseInt(tokens[0]);
//            int m = Integer.parseInt(tokens[1]);
//            int[][] length = new int[n][n];
//            for(int i = 0; i < n; i++){
//                for (int j = 0; j < n; j++){
//                    length[i][j] = Integer.MAX_VALUE;
//                }
//            }
//            boolean[] visited = new boolean[n];
//            int[] distance = new int[n];
//            distance[0] = 0;
//            for (int i = 1; i < n; i++){
//                distance[i] = Integer.MAX_VALUE;
//                visited[i] = false;
//            }
//
//            for (int k = 0; k < m; k++){
//                line = br.readLine();
//                String[] elements = line.split(" ");
//                int i = Integer.parseInt(elements[0]) - 1;
//                int j = Integer.parseInt(elements[1]) - 1;
//                int l = Integer.parseInt(elements[2]);
//                if (l < length[i][j]) {
//                    length[i][j] = l;
//                    length[j][i] = l;
//                }
//
//            }
//            br.close();
//
//            for (int i = 0; i < n - 1; i++) {
//                int u = minDistance(distance, visited);
//                if (u == -1)
//                    break;
//                visited[u] = true;
//                for (int v = 0; v < n; v++) {
//                    if (!visited[v] && length[u][v] != Integer.MAX_VALUE && distance[u] != Integer.MAX_VALUE && distance[u] + length[u][v] < distance[v]) {
//                        distance[v] = distance[u] + length[u][v];
//                    }
//                }
//            }
//            PrintWriter pw = new PrintWriter(new File("output.txt"));
//            pw.println(distance[n-1]);
//            pw.close();
//
//        } catch (IOException e) {
//            System.out.println("error" + e);
//        }
//    }
//}

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            String line = br.readLine();
            String[] tokens = line.split(" ");
            int n = Integer.parseInt(tokens[0]);
            int m = Integer.parseInt(tokens[1]);
            ArrayList<ArrayList<int[]>> adj = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                adj.add(new ArrayList<>());
            }
            for (int k = 0; k < m; k++) {
                line = br.readLine();
                tokens = line.split(" ");
                int u = Integer.parseInt(tokens[0]) - 1;
                int v = Integer.parseInt(tokens[1]) - 1;
                int l = Integer.parseInt(tokens[2]);
                adj.get(u).add(new int[]{v, l});
                adj.get(v).add(new int[]{u, l});
            }
            br.close();
            long[] distance = new long[n];
            boolean[] visited = new boolean[n];
            for (int i = 0; i < n; i++){
                distance[i] = Long.MAX_VALUE;
            }
            distance[0] = 0;
            PriorityQueue<long[]> pqueue = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
            pqueue.offer(new long[]{0, 0});
            while (!pqueue.isEmpty()) {
                long[] current = pqueue.poll();
                int u = (int) current[0];
                long dist = current[1];
                if (dist != distance[u])
                    continue;
                visited[u] = true;
                for (int[] edge : adj.get(u)) {
                    int v = edge[0];
                    int l = edge[1];
                    if (!visited[v]) {
                        long newDist = distance[u] + l;
                        if (newDist < distance[v]) {
                            distance[v] = newDist;
                            pqueue.offer(new long[]{v, newDist});
                        }
                    }
                }
            }
            PrintWriter pw = new PrintWriter(new File("output.txt"));
            pw.println(distance[n-1]);
            pw.close();

        } catch (IOException e) {
            System.out.println("error" + e);
        }
    }
}
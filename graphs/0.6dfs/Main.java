package org.example;
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
            boolean[] visited = new boolean[n];
            int labelCount = 1;
            Stack<Integer> stack = new Stack<>();
            for (int start = 0; start < n; start++) {
                if (!visited[start]) {
                    stack.push(start);
                    while (!stack.isEmpty()) {
                        int curr = stack.pop();
                        if (!visited[curr]) {
                            visited[curr] = true;
                            labels[curr] = labelCount++;
                            for (int adj = n - 1; adj >= 0; adj--) {
                                if (graph[curr][adj] == 1 && !visited[adj]) {
                                    stack.push(adj);
                                }
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
package org.example;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args){
        try {
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            int N = Integer.parseInt(br.readLine());
            ArrayList<String>[][] words = new ArrayList[26][26];
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    words[i][j] = new ArrayList<>();
                }
            }
            int[][] g = new int[26][26];
            int[] outdeg = new int[26];
            int[] indeg = new int[26];
            int edges = 0;
            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                int u = line.charAt(0) - 'a';
                int v = line.charAt(line.length() - 1) - 'a';
                words[u][v].add(line);
                g[u][v]++;
                outdeg[u]++;
                indeg[v]++;
                edges++;
            }
            br.close();
            PrintWriter pw = new PrintWriter(new File("output.txt"));
            for (int i = 0; i < 26; i++) {
                if (indeg[i] != outdeg[i]) {
                    pw.println("No");
                    pw.close();
                    return;
                }
            }
            int start = -1;
            for (int i = 0; i < 26; i++) {
                if (outdeg[i] > 0) {
                    start = i;
                    break;
                }
            }
            boolean[] visited = new boolean[26];
            ArrayDeque<Integer> dqueue = new ArrayDeque<>();
            visited[start] = true;
            dqueue.add(start);
            while (!dqueue.isEmpty()) {
                int v = dqueue.poll();
                for (int u = 0; u < 26; u++) {
                    if ((g[v][u] > 0 || g[u][v] > 0) && !visited[u]) {
                        visited[u] = true;
                        dqueue.add(u);
                    }
                }
            }
            for (int i = 0; i < 26; i++) {
                if ((indeg[i] != 0 || outdeg[i] != 0) && !visited[i]) {
                    pw.println("No");
                    pw.close();
                    return;
                }
            }
            Stack<Integer> stack = new Stack<>();
            ArrayList<Integer> cycle = new ArrayList<>();
            stack.push(start);
            while (!stack.isEmpty()) {
                int u = stack.peek();
                int v = -1;
                for (int i = 0; i < 26; i++) {
                    if (g[u][i] > 0) {
                        v = i;
                        break;
                    }
                }
                if (v != -1) {
                    g[u][v]--;
                    stack.push(v);
                } else {
                    cycle.add(u);
                    stack.pop();
                }
            }

            if (cycle.size() != edges + 1) {
                pw.println("No");
                pw.close();
                return;
            }
            Collections.reverse(cycle);
            pw.println("Yes");
            for (int i = 0; i+1 < cycle.size(); i++) {
                int u = cycle.get(i);
                int v = cycle.get(i+1);
                ArrayList<String> list = words[u][v];
//            if (list.isEmpty()) {
//                pw.println("No");
//                pw.close();
//                return;
//            }
                String word = list.remove(list.size() - 1);
                pw.write(word);
                pw.println();
            }
            pw.close();
        } catch (IOException e){
            System.out.println("error " + e);
        }
    }
}

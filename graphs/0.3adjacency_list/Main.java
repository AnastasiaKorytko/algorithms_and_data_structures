package org.example;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader("input.txt"));
            String line = br.readLine();
            String[] tokens = line.split(" ");
            int n = Integer.parseInt(tokens[0]);
            int m = Integer.parseInt(tokens[1]);
            ArrayList<Integer>[] adjList = new ArrayList[n];
            for (int i = 0; i < n; i++){
                adjList[i] = new ArrayList<>();
            }

            for (int k = 0; k < m; k++){
                line = br.readLine();
                String[] edge = line.split(" ");
                int i = Integer.parseInt(edge[0]) - 1;
                int j = Integer.parseInt(edge[1]) - 1;
                adjList[i].add(j+1);
                adjList[j].add(i+1);
            }
            br.close();
            PrintWriter pw = new PrintWriter(new File("output.txt"));
            for (int i = 0; i < n; i++){
                pw.print(adjList[i].size() + " ");
                for (int el : adjList[i]){
                    pw.print(el + " ");
                }
                pw.print("\n");
            }
            pw.close();
        } catch (IOException e) {
            System.out.println("error" + e);
        }
    }
}
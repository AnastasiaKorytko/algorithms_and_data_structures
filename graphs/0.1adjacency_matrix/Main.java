package org.example;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader("input.txt"));
            String line = br.readLine();
            String[] tokens = line.split(" ");
            int n = Integer.parseInt(tokens[0]);
            int m = Integer.parseInt(tokens[1]);
            int[][] adjacency = new int[n][n];
            for(int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    adjacency[i][j] = 0;
                }
            }
            for (int k = 0; k < m; k++){
                line = br.readLine();
                String[] edge = line.split(" ");
                int i = Integer.parseInt(edge[0]) - 1;
                int j = Integer.parseInt(edge[1]) - 1;
                adjacency[i][j] = 1;
                adjacency[j][i] = 1;
            }
            br.close();
            PrintWriter pw = new PrintWriter(new File("output.txt"));
            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    pw.print(adjacency[i][j] + " ");
                }
                pw.print("\n");
            }
            pw.close();
        } catch (IOException e) {
            System.out.println("error" + e);
        }
    }
}
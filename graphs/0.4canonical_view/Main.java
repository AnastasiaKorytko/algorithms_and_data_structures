package org.example;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("input.txt"));
            String line = br.readLine();
            int n = Integer.parseInt(line);
            int[] adjList = new int[n];
            for (int i = 0; i < n; i++){
                adjList[i] = 0;
            }
            for (int i = 0; i < n; i++){
                line = br.readLine();
                String[] elements = line.split(" ");
                for (int j = 0; j < n; j++){
                    if (Integer.parseInt(elements[j]) == 1)
                        adjList[j] = i + 1;
                }
            }
            br.close();
            PrintWriter pw = new PrintWriter(new File("output.txt"));
            for (int i = 0; i < n; i++)
                pw.print(adjList[i] + " ");
            pw.close();
        } catch (IOException e) {
            System.out.println("error" + e);
        }
    }
}
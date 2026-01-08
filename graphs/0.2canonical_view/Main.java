package org.example;

import java.io.*;

public class Main {
    public static void main(String[] args) {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader("input.txt"));
                String line = br.readLine();
                int n = Integer.parseInt(line);
                int[] canonical = new int[n];
                for (int i = 0; i < n; i++){
                    canonical[i] = 0;
                }
                for (int i = 0; i < n-1; i++){
                    line = br.readLine();
                    String[] arcs = line.split(" ");
                    int el = Integer.parseInt(arcs[0]);
                    int ind = Integer.parseInt(arcs[1]) - 1;
                    canonical[ind] = el;
                }
                br.close();
                PrintWriter pw = new PrintWriter(new File("output.txt"));
                for (int i = 0; i < n; i++){
                    pw.print(canonical[i] + " ");
                }
                pw.close();
            } catch (IOException e) {
                System.out.println("error" + e);
            }
        }
}
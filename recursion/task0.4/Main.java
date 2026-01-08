package org.example;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static int matrixChainMultiplicationOrder(ArrayList<Integer> dim) {
        int n = dim.size();

        int[][] m = new int[n][n];
        for (int i = 1; i < n; i++) {
            m[i][i] = 0;
        }
        for (int l = 2; l < n; l++) {
            for (int i = 1; i < n - l + 1; i++) {
                int j = i + l - 1;
                m[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {
                    int cost = m[i][k] + m[k + 1][j] + dim.get(i-1) * dim.get(k) * dim.get(j);
                    if (cost < m[i][j]) {
                        m[i][j] = cost;
                    }
                }
            }
        }
        return m[1][n - 1];
    }

    public static void main(String[] args) {
        ArrayList<Integer> vector = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("input.txt"));
            int s = Integer.parseInt(br.readLine());
            String line;
            for (int i = 0; i < s; i++){
                line = br.readLine();
                String[] parts = line.split("\\s+");
                int first = Integer.parseInt(parts[0]);
                int second = Integer.parseInt(parts[1]);
                if (i == 0)
                    vector.add(first);
                vector.add(second);
            }
            File file = new File("output.txt");
            PrintWriter pw = new PrintWriter(file);
            pw.println(matrixChainMultiplicationOrder(vector));
            pw.close();
        }
        catch (IOException e){
            System.out.println("Error: " + e);
        }
        finally {
            try{
                br.close();
            }
            catch (IOException e){
                System.out.println("Error: " + e);
            }
        }
    }
}
package org.example;

import java.io.*;

public class Main {
    public static int LowerBound(int[] arr, int r, int x) {
        int q = 0;
        while (q < r) {
            int k = (q + r) / 2;
            if (x <= arr[k]) {
                r = k;
            } else {
                q = k + 1;
            }
        }
        return q;
    }

    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            int[] arr = new int[700000];
            br = new BufferedReader(new FileReader("input.txt"));
            int n = Integer.parseInt(br.readLine());
            String line;
            line = br.readLine();
            String[] parts = line.split("\\s+");
            for (int i = 0; i < n; i++){
                arr[i] = Integer.parseInt(parts[i]);
            }
            int[] F = new int[n];
            for (int i = 0; i < n; i++) {
                F[i] = Integer.MAX_VALUE;
            }
            int k = 0;
            for (int i = 0; i < n; i++){
                int q = LowerBound(F, k, arr[i]);
                    F[q] = arr[i];
                if (q == k)
                    k++;
            }
            File file = new File("output.txt");
            PrintWriter pw = new PrintWriter(file);
            pw.println(k);
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
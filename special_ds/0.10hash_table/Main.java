package org.example;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader("input.txt"));
            String line = br.readLine();
            String[] tokens = line.split(" ");
            int m = Integer.parseInt(tokens[0]);
            int c = Integer.parseInt(tokens[1]);
            int n = Integer.parseInt(tokens[2]);
            int[] arr = new int[n];
            for (int i = 0; i < n; i++){
                arr[i] = Integer.parseInt(br.readLine());
            }
            br.close();
            int[] hash_table = new int[m];
            for (int i = 0; i < m; i++){
                hash_table[i] = -1;
            }
            Set<Integer> added = new HashSet<>();
            for (int i = 0; i < n; i++){
                if (added.contains(arr[i]))
                    continue;
                for (int j = 0; j < m; j++){
                    int h = ((arr[i] % m) + c*j) % m;
                    if (hash_table[h] == -1) {
                        hash_table[h] = arr[i];
                        added.add(arr[i]);
                        break;
                    }
                }

            }
            PrintWriter pw = new PrintWriter(new File("output.txt"));
            for (int i = 0; i < m; i++){
                pw.print(hash_table[i] + " ");
            }
            pw.close();
        }
        catch (IOException e){
            System.out.println("error" + e);
        }
    }
}

package org.example;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("in.txt"));
            String line;
            line = br.readLine();
            int x = Integer.parseInt(line);
            line = br.readLine();
            int y = Integer.parseInt(line);
            line = br.readLine();
            int z = Integer.parseInt(line);
            String A = br.readLine();
            String B = br.readLine();
            int m = A.length();
            int n = B.length();
            int[][] F = new int[m+1][n+1];
            for (int i = 0; i < m+1; i++){
                F[i][0] = x * i;
            }
            for (int j = 0; j < n+1; j++){
                F[0][j] = y * j;
            }
            for (int i = 1; i < m+1; i++){
                for (int j = 1; j < n+1; j++){
                    if (A.charAt(i - 1) == B.charAt(j - 1)){
                        F[i][j] = Math.min(Math.min(F[i-1][j] + x, F[i][j-1] + y), F[i-1][j-1]);

                    }
                    else
                    {
                        F[i][j] = Math.min(Math.min(F[i-1][j] + x, F[i][j-1] + y), F[i-1][j-1] + z);
                    }
                }
            }
            File file = new File("out.txt");
            PrintWriter pw = new PrintWriter(file);
            pw.println(F[m][n]);
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
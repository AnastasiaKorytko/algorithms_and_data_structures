package org.example;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = null;
        br = new BufferedReader(new FileReader("input.txt"));
        String s = br.readLine();
        br.close();
        int n = s.length();
        int[][] F = new int [n][n];
            for (int i = 0; i < n; i++) {
                F[i][i] = 1;
            }
            for (int i = 0; i < n-1; i++){
                    if (s.charAt(i) == s.charAt(i+1)){
                        F[i][i+1] = 2;
                    }
                if (s.charAt(i) != s.charAt(i+1)){
                    F[i][i+1] = 1;
                }


        }

            for (int i = n-1; i >= 0; i--){
                for (int j = i+1; j < n; j++){
                    if (s.charAt(i) == s.charAt(j)) {
                        F[i][j] = F[i+1][j-1] + 2;
                    }
                    if (s.charAt(i) != s.charAt(j)) {
                        F[i][j] = Math.max(F[i+1][j], F[i][j-1]);
                    }
                }
            }
            String pal = "";
            int i = 0, j = n-1;
            while (i <= j){
                if (i == j){
                    pal += s.charAt(i);
                    break;
                }
                if (s.charAt(i) == s.charAt(j)){
                    pal += s.charAt(i);
                    i++;
                    j--;
                }
                else if (Math.max(F[i+1][j], F[i][j-1]) == F[i+1][j]){
                    i++;
                }
                else{
                    j--;
                }
            }
            String p = "";
            if (F[0][n-1] % 2 == 0){
                String revpal = new StringBuffer(pal).reverse().toString();
                p = pal + revpal;
            }
            else {
                String pal1 = pal.substring(0, pal.length()-1);
                String revpal = new StringBuffer(pal1).reverse().toString();
                p = pal + revpal;
            }
        File file = new File("output.txt");
        PrintWriter pw = new PrintWriter(file);
        pw.println(F[0][n-1]);
        pw.println(p);
        pw.close();
    }
}

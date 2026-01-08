package org.example;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        BufferedReader br = null;
        try{
            br =new BufferedReader(new FileReader("input.txt"));
            int n = Integer.parseInt(br.readLine());
            String s = br.readLine();
            br.close();
            PrintWriter pw = new PrintWriter(new File("output.txt"));
            String[] tokens = s.split(" ");
            int[] arr = new int[n];
            for (int i = 0; i < n; i++){
                arr[i] = Integer.parseInt(tokens[i]);
            }
            boolean isBinHeap = true;
            for (int i = 0; i < n/2; i++) {
                if ((2 * i + 1) < n && (2 * i + 2) < n) {
                    if (arr[i] <= arr[2 * i + 1] && arr[i] <= arr[2 * i + 2]) {
                        isBinHeap = true;
                    }
                    else {
                        isBinHeap = false;
                        break;
                    }
                } else if ((2 * i + 1) < n && (2 * i + 2) >= n) {
                    if(arr[i] <= arr[2 * i + 1])
                    isBinHeap = true;
                    else {
                        isBinHeap = false;
                        break;
                    }

                } else {
                    continue;
                }
            }
            if (isBinHeap)
                pw.println("YES");
            else
                pw.println("NO");

            pw.close();

        }
        catch(IOException e){
                System.out.println("Error " + e);
            }

    }
}
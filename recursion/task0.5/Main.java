package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] arrA = new int[n];
        int[] arrB = new int[n];
        for (int i = 0; i < n; i++) {
            arrA[i] = scan.nextInt();
        }
        for (int i = 0; i < n; i++) {
            arrB[i] = scan.nextInt();
        }
        ArrayList<Integer> indA = new ArrayList<>();
        ArrayList<Integer> indB = new ArrayList<>();
        int[][] f = new int[n+1][n+1];
        for (int i = 0; i < n+1; i++) {
            f[i][0] = 0;
        }
        for (int j = 0; j < n+1; j++) {
            f[0][j] = 0;
        }
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < n+1; j++) {
                if (arrA[i-1] == arrB[j-1]) {
                    f[i][j] = f[i - 1][j - 1] + 1;
                }
                if (arrA[i-1] != arrB[j-1]) {
                    f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
                }
            }
    }
        int i = n, j = n;
        while   (i > 0 && j > 0){
            if (arrA[i - 1] == arrB[j - 1]){
                indA.add(i - 1);
                indB.add(j - 1);
                i--;
                j--;
            }
            else if (f[i - 1][j] > f[i][j - 1]){
                i--;
            }
            else
                j--;
        }

        Collections.reverse(indA);
        Collections.reverse(indB);
        System.out.println(f[n][n]);
        for (int el: indA){
            System.out.print(el + " ");
        }
        System.out.println();
        for (int el: indB){
            System.out.print(el + " ");
        }
    }
}

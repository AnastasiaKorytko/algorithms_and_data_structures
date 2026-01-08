package org.example;

import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int M = scan.nextInt();
        int N = scan.nextInt();
        int[] H = new int[M];
        int[] B = new int[N];
        for (int i = 0; i < M; i++){
            H[i] = scan.nextInt();
        }
        for (int i = 0; i < N; i++){
            B[i] = scan.nextInt();
        }

        int[] ind = new int[M];
        int[][] S = new int [M][N];
        for (int i = 0; i < M; i++){
            for (int j = i; j < N; j++){
                S[i][j] = H[i] + B[j];
            }
        }
        boolean[][] F = new boolean [M][N];
        int[][] Pr = new int [M][N];
        for (int j = 0; j < N ; j++){
            F[0][j] = true;
            Pr[0][j] = -1;
        }

        for (int i = 1; i < M; i++){
            TreeMap<Integer, Integer> map = new TreeMap<>();
            int k = i-1;
            for (int j = i; j < N; j++){
                while (k < j){
                    if (F[i-1][k]){
                        map.put(S[i-1][k], k);
                    }
                    k++;
                }
                Map.Entry<Integer, Integer> entry = map.lowerEntry(S[i][j]);
                if (entry == null){
                    F[i][j] = false;
                    Pr[i][j] = -1;
                }
                else {
                    F[i][j] = true;
                    Pr[i][j] = entry.getValue();
                }

            }
        }

        for (int j = M-1; j < N; j++){
            if (F[M-1][j]){
                int curInd = j;
                for (int i = M-1; i >= 0; i--){
                    ind[i] = curInd + 1;
                    curInd = Pr[i][curInd];

                }
                for (int k = 0; k < M; k++){
                    System.out.print(ind[k] + " ");
                }
                return;
            }
        }
        System.out.println(-1);
    }
}




//import java.util.*;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner scan = new Scanner(System.in);
//        int M = scan.nextInt();
//        int N = scan.nextInt();
//        int[] H = new int[M];
//        int[] B = new int[N];
//        for (int i = 0; i < M; i++) H[i] = scan.nextInt();
//        for (int i = 0; i < N; i++) B[i] = scan.nextInt();
//
//        int[] ind = new int[M];
//        boolean[][] F = new boolean[M][N];
//        int[][] Pr = new int[M][N];
//
//        // Precompute sums directly
//        for (int j = 0; j < N; j++) {
//            F[0][j] = true;
//            Pr[0][j] = -1;
//        }
//
//        for (int i = 1; i < M; i++) {
//            for (int j = i; j < N; j++) {
//                F[i][j] = false;
//                Pr[i][j] = -1;
//                int target = H[i] + B[j];
//                for (int k = i - 1; k < j; k++) {
//                    if (F[i - 1][k] && H[i - 1] + B[k] < target) {
//                        F[i][j] = true;
//                        Pr[i][j] = k;
//                        break; // keep break for correctness
//                    }
//                }
//            }
//        }
//
//        for (int j = M - 1; j < N; j++) {
//            if (F[M - 1][j]) {
//                int curInd = j;
//                for (int i = M - 1; i >= 0; i--) {
//                    ind[i] = curInd + 1;
//                    curInd = Pr[i][curInd];
//                }
//                for (int k = 0; k < M; k++) {
//                    System.out.print(ind[k] + " ");
//                }
//                return;
//            }
//        }
//
//        System.out.println(-1);
//    }
//}

//import java.util.*;
//public class Main {
//        public static void main(String[] args) {
//        Scanner scan = new Scanner(System.in);
//        int M = scan.nextInt();
//        int N = scan.nextInt();
//        int[] H = new int[M];
//        int[] B = new int[N];
//        for (int i = 0; i < M; i++){
//            H[i] = scan.nextInt();
//        }
//        for (int i = 0; i < N; i++){
//            B[i] = scan.nextInt();
//        }
//
//
//        int[] ind = new int[M];
//        BitSet[] F = new BitSet[M];
//        for (int i = 0; i < M; i++){
//            F[i] = new BitSet(N);
//        }
//        int[][] Pr = new int [M][N];
//        for (int j = 0; j < N ; j++){
//            F[0].set(j);
//            Pr[0][j] = -1;
//        }
//
//        for (int i = 1; i < M; i++){
//            for (int j = i; j < N; j++){
//                Pr[i][j] = -1;
//                int sum = H[i] + B[j];
//                for (int k = i -1; k < j; k++){
//                    if (F[i-1].get(k) && (H[i-1] + B[k] < sum)){
//                        Pr[i][j] = k;
//                        F[i].set(j);
//                        break;
//                    }
//                }
//
//            }
//        }
//
//        for (int j = M-1; j < N; j++){
//            if (F[M-1].get(j)){
//                int curInd = j;
//                for (int i = M-1; i >= 0; i--){
//                    ind[i] = curInd + 1;
//                    curInd = Pr[i][curInd];
//
//                }
//                for (int k = 0; k < M; k++){
//                    System.out.print(ind[k] + " ");
//                }
//                return;
//            }
//        }
//        System.out.println(-1);
//    }
//}


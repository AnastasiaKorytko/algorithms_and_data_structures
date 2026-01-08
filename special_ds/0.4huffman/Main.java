package org.example;

//import java.io.*;
//import java.util.ArrayList;
//import java.util.StringTokenizer;
//
//public class Main {
//    public static void main(String[] args) {
//        BufferedReader br = null;
//        try {
//            br = new BufferedReader(new FileReader("huffman.in"));
//            int n = Integer.parseInt(br.readLine());
//            String s = br.readLine();
//            br.close();
//            long[] huff = new long[2 * n];
////            String[] tokens = s.split(" ");
////            for (int i = 0; i < n; i++){
////                huff[i] = Long.parseLong(tokens[i]);
////            }
//            StringTokenizer st = new StringTokenizer(s);
//            for (int i = 0; i < n; i++) {
//                huff[i] = Long.parseLong(st.nextToken());
//            }
//            int i = 0;
//            int j = n;
//            int parent = 0;
//            long length = 0;
//            for (int k = 0; k < n-1; k++){
//                long min1;
//                long min2;
//                if (i < n && (parent == 0 || huff[i] <= huff[j])){
//                    min1 = huff[i];
//                    i++;
//                }
//                else{
//                    min1 = huff[j];
//                    j++;
//                    parent--;
//                }
//                if (i < n && (parent == 0 || huff[i] <= huff[j])){
//                    min2 = huff[i];
//                    i++;
//                }
//                else{
//                    min2 = huff[j];
//                    j++;
//                    parent--;
//                }
//                long sum = min1 + min2;
//                length+= sum;
//                huff[j + parent] = sum;
//                parent++;
//
//            }
//            PrintWriter pw = new PrintWriter(new File("huffman.out"));
//            pw.println(length);
//            pw.close();
//        }
//        catch (IOException e) {
//            System.out.println("Error" + e);
//        }
//    }
//}


import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("huffman.in"));
            int n = Integer.parseInt(br.readLine());
            String s = br.readLine();
            br.close();
            ArrayList<Long> huff = new ArrayList<>(2*n);
            for (int i = 0; i < 2*n; i++){
                huff.add(0L);
            }
            StringTokenizer st = new StringTokenizer(s);
            for (int i = 0; i < n; i++) {
                huff.set(i, Long.parseLong(st.nextToken()));
            }
            int i = 0;
            int j = n;
            int parent = 0;
            Long length = 0L;
            for (int k = 0; k < n-1; k++){
                Long min1;
                Long min2;
                if (i < n && (parent == 0 || huff.get(i) <= huff.get(j))){
                    min1 = huff.get(i);
                    i++;
                }
                else{
                    min1 = huff.get(j);
                    j++;
                    parent--;
                }
                if (i < n && (parent == 0 || huff.get(i) <= huff.get(j))){
                    min2 = huff.get(i);
                    i++;
                }
                else{
                    min2 = huff.get(j);
                    j++;
                    parent--;
                }
                Long sum = min1 + min2;
                length+= sum;
                huff.set(j + parent, sum);
                parent++;

            }
            PrintWriter pw = new PrintWriter(new File("huffman.out"));
            pw.println(length);
            pw.close();
        }
        catch (IOException e) {
            System.out.println("Error" + e);
        }
    }
}
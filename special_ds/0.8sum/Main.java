package org.example;

import java.util.ArrayList;
import java.util.Scanner;

class FenvikTree{
    public int size;
    public long[] t;

    public FenvikTree(int size){
        this.size = size;
        t = new long[size + 1];
    }

    long getSum(int pos){
        long sum = 0;
        for (int i = pos+1; i > 0; i -= i & -i){
            sum += t[i];
        }
        return sum;
    }

    void update(int pos, long x){
        for (int i = pos+1; i < size + 1; i += i & -i){
            t[i] += x;
        }
    }

    long intervalSum(int l, int r){
        return getSum(r-1) - getSum(l-1);
    }


}
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        FenvikTree tree = new FenvikTree(n);
        long[] arr = new long[n];
        ArrayList<Long> output = new ArrayList<>(n);
        for (int i = 0; i < n; i++){
            arr[i] = scan.nextLong();
            tree.update(i, arr[i]);
        }
        int q = scan.nextInt();
        scan.nextLine();
        for (int i = 0; i < q; i++){
            String line = scan.nextLine();
            String[] tokens = line.split(" ");
            if (tokens[0].equals("Add")){
                int ind = Integer.parseInt(tokens[1]);
                long x = Long.parseLong(tokens[2]);
                tree.update(ind, x);
                arr[ind] += x;
            }
            if (tokens[0].equals("FindSum")){
                int l = Integer.parseInt(tokens[1]);
                int r = Integer.parseInt(tokens[2]);
                output.add(tree.intervalSum(l, r));
            }
        }
        for (long el: output){
            System.out.println(el);
        }
    }
}
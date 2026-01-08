package org.example;

import java.io.*;

class DSU{
    public int[] parent;
    int components;

    public DSU(int components){
        this.components = components;
        parent = new int[components];
        for (int i = 0; i < components; i++){
            parent[i] = -1;
        }
    }

    public int find(int x){
        if (parent[x] < 0){
            return x;
        }
        parent[x] = find(parent[x]);
        return parent[x];
    }

    public void union(int a, int b){
        a = find(a);
        b = find(b);
        if (a == b){
            return;
        }
        else {
            if (parent[a] < parent[b]) {
                parent[a] += parent[b];
                parent[b] = a;
            } else {
                parent[b] += parent[a];
                parent[a] = b;
            }
            components--;
        }
    }

    public int getComponents() {
        return components;
    }
}
public class Main {

    public static void main(String[] args) {
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader("input.txt"));
            PrintWriter pw = new PrintWriter(new File("output.txt"));
            String line = br.readLine();
            String[] input = line.split(" ");
            int n = Integer.parseInt(input[0]);
            DSU roads = new DSU(n);
            int q = Integer.parseInt(input[1]);
            for (int i = 0; i < q; i++){
                line = br.readLine();
                String[] request = line.split(" ");
                int u = Integer.parseInt(request[0]) - 1;
                int v = Integer.parseInt(request[1]) - 1;
                roads.union(u, v);
                pw.println(roads.getComponents());
            }
            br.close();
            pw.close();
        }
        catch (IOException e){
            System.out.println("error" + e);
        }
    }
}
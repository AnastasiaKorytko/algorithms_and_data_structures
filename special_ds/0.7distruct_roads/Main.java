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

    public boolean isConnected(){
        if (components == 1)
            return true;
        else
            return false;
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
            int m = Integer.parseInt(input[1]);
            int q = Integer.parseInt(input[2]);
            int[][] nodes = new int[m][2];
            for (int i = 0; i < m; i++){
                line = br.readLine();
                String[] tokens = line.split(" ");
                nodes[i][0] = Integer.parseInt(tokens[0]) - 1;
                nodes[i][1] = Integer.parseInt(tokens[1]) - 1;
            }
            int[] earthquakes = new int[q];
            for (int i = 0; i < q; i++){
                line = br.readLine();
                earthquakes[i] = Integer.parseInt(line) - 1;
            }
            boolean[] destructedRoads = new boolean[m];
            for (int i = 0; i < q; i++){
                destructedRoads[earthquakes[i]] = true;
            }
            DSU roads = new DSU(n);
            for (int i = 0; i<m; i++ ){
                if (!destructedRoads[i]){
                    roads.union(nodes[i][0], nodes[i][1]);
                }
            }
            StringBuilder res = new StringBuilder("");
            for (int i = q-1; i >= 0; i--){
                if (roads.isConnected())
                    res.append('1');
                else
                    res.append('0');
                roads.union(nodes[earthquakes[i]][0], nodes[earthquakes[i]][1]);
            }
            pw.println(res.reverse());
            br.close();
            pw.close();
        }
        catch (IOException e){
            System.out.println("error" + e);
        }
    }
}
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class Main {
    public static int rec(int n, int[] arr) {
        if (n == 1) {
            return arr[0];
        }
        if (n == 2) {
            return -1;
        }
        int[] F = new int[n];
        F[0] = arr[0];
        F[1] = -1;
        F[2] = arr[0] + arr[2];
        if (n > 3) {
            for (int i = 3; i < n; i++) {
                if (F[i - 2] == -1 && F[i - 3] == -1) {
                    F[i] = -1;
                }
                else if (F[i - 2] == -1) {
                    F[i] = F[i - 3] + arr[i];
                }
                else if (F[i - 3] == -1) {
                    F[i] = F[i - 2] + arr[i];
                }
                else {
                    F[i] = Math.max(F[i - 2], F[i - 3]) + arr[i];
                }
            }
        }
        return F[n - 1];
    }

    public static ArrayList<Integer> rec2(int n, int[] arr) {
        ArrayList<Integer> index = new ArrayList<>();
        if (n == 1) {
            index.add(1);
            return index;
        }
        if (n == 2) {
            return index;
        }
        int[] F = new int[n];
        F[0] = arr[0];
        F[1] = -1;
        F[2] = arr[0] + arr[2];
        if (n > 3) {
            for (int i = 3; i < n; i++) {
                if (F[i - 2] == -1 && F[i - 3] == -1) {
                    F[i] = -1;
                }
                else if (F[i - 2] == -1) {
                    F[i] = F[i - 3] + arr[i];
                }
                else if (F[i - 3] == -1) {
                    F[i] = F[i - 2] + arr[i];
                }
                else {
                    F[i] = Math.max(F[i - 2], F[i - 3]) + arr[i];
                }
            }
        }
        if (F[n - 1] == -1) {
            return index;
        }
        int i = n - 1;
        while (i >= 0) {
            index.add(i + 1);
            if (i == 0)
                break;
            if (i >= 3 && F[i] == F[i - 3] + arr[i]) {
                i -= 3;
            }
            else if (i >= 2 && F[i] == F[i - 2] + arr[i]) {
                i -= 2;
            }
            else if (i == 2) {
                i = 0;
            }
        }
        return index;
    }

public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scan.nextInt();
        }
        System.out.println(rec(n, arr));
        if (n != 2){
            ArrayList<Integer> vector = new ArrayList<>();
            vector = rec2(n, arr);
            Collections.reverse(vector);
            for (int el: vector) {
                System.out.print(el + " ");
            }
        }
    }
}
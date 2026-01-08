import java.util.Scanner;
public class Main {
public static long binom(int n, int k, long m) {
    long[][] f = new long[n + 1][k + 1];
    for (int i = 0; i < n+1; i++) {
        for (int j = 0; j <= Math.min(i, k); j++) {
            if (i == 0 || j == 0 || j == i)
                f[i][j] = 1;
             else {
                f[i][j] = (f[i - 1][j - 1] + f[i - 1][j]) % m;
            }
        }
    }
    return f[n][k];
}

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int K = scan.nextInt();
        int m = 1000000007;
        long res = binom(N, K, m);
        System.out.println(res);
    }
}
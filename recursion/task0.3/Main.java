//import java.math.BigInteger;
//import java.util.Scanner;
//public class Main {
//    public static final BigInteger m = BigInteger.valueOf(1000000007);
//    public static BigInteger binom(int n, int k) {
//    if (k == 0 || k == n)
//        return BigInteger.ONE;
//    BigInteger f = BigInteger.ONE;
//    BigInteger f2 = BigInteger.ONE;
//    for (int i = 1; i <= k; i++) {
//        f = f.multiply(BigInteger.valueOf(n - k + i));
//        f2 = f2.multiply(BigInteger.valueOf(i));
//    }
//    BigInteger res = f.divide(f2).mod(m);
//        //BigInteger res = f.mod(m);
//    return res;
//}
//    public static void main(String[] args) {
//        Scanner scan = new Scanner(System.in);
//        int N = scan.nextInt();
//        int K = scan.nextInt();
//        BigInteger res = binom(N, K);
//        System.out.println(res);
//    }
//}


//import java.math.BigInteger;
//import java.util.Scanner;
//public class Main {
//    public static final BigInteger m = BigInteger.valueOf(1000000007);
//    public static BigInteger binom(int n, int k) {
//        if (k == 0 || k == n)
//            return BigInteger.ONE;
//        BigInteger f = BigInteger.ONE;
//        k = Math.min(k, n - k);
//        for (int i = 1; i <= k; i++) {
//            f = f.multiply(BigInteger.valueOf(n - k + i)).multiply(BigInteger.valueOf(i).modInverse(m)).mod(m);
//        }
//        return f;
//    }
//    public static void main(String[] args) {
//        Scanner scan = new Scanner(System.in);
//        int N = scan.nextInt();
//        int K = scan.nextInt();
//        BigInteger res = binom(N, K);
//        System.out.println(res);
//    }
//}

//import java.math.BigInteger;
//import java.util.Scanner;
//public class Main {
//    public static final BigInteger m = BigInteger.valueOf(1000000007);
//    public static final int max = 500001;
//    public static BigInteger[] inv = new BigInteger[max];
//    public static void modInv() {
//        for (int i = 1; i < max; i++) {
//            inv[i] = BigInteger.valueOf(i).modInverse(m);
//        }
//    }
//    public static BigInteger binom(int n, int k) {
//        if (k == 0 || k == n)
//            return BigInteger.ONE;
//        BigInteger f = BigInteger.ONE;
//        k = Math.min(k, n - k);
//        for (int i = 1; i <= k; i++) {
//            f = f.multiply(BigInteger.valueOf(n - k + i)).mod(m);
//            f = f.multiply(inv[i]).mod(m);
//        }
//        return f;
//    }
//    public static void main(String[] args) {
//        Scanner scan = new Scanner(System.in);
//        int N = scan.nextInt();
//        int K = scan.nextInt();
//        modInv();
//        BigInteger res = binom(N, K);
//        System.out.println(res);
//    }
//}

import java.util.Scanner;
public class Main {
    public static final long m = 1000000007;
    public static long modInverse(long a, long p) {
        long res = 1;
        long power = p - 2;
        while (power > 0) {
            if (power % 2 == 1) {
                res = (res * a) % p;
            }
            a = (a * a) % p;
            power /= 2;
        }
        return res;
    }
    public static long binom(int n, int k) {
        if (k == 0 || k == n)
            return 1;
        k = Math.min(k, n - k);
        long f = 1;
        for (int i = 1; i <= k; i++) {
            f = f * (n - k + i) % m;
            f = f * modInverse(i, m) % m;
        }
        return f;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int K = scan.nextInt();
        long res = binom(N, K);
        System.out.println(res);
    }
}
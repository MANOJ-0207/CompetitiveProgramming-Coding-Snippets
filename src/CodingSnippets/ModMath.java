package CodingSnippets;

public class ModMath {
    private static final int MOD = 100_000_007;

    // Precompute factorials and inverse factorials up to MAX
    private static final int MAX = 1_000_005;
    private static final int[] factorial = new int[MAX];
    private static final int[] inverseFactorial = new int[MAX];

    static {
        computeFactorials();
    }

    public static int modAddition(int a, int b) {
        return ((a % MOD) + (b % MOD)) % MOD;
    }

    public static int modSubtraction(int a, int b) {
        return ((a % MOD) - (b % MOD) + MOD) % MOD;
    }

    public static int modMultiplication(int a, int b) {
        return (int) (((long)(a % MOD) * (b % MOD)) % MOD);
    }

    public static int modDivision(int a, int b) {
        int bInv = modInverse(b, MOD);
        return modMultiplication(a, bInv);
    }

    public static int modPowerBinaryExponentiation(int a, int b) {
        int result = 1;
        int base = a % MOD;

        while (b > 0) {
            if ((b & 1) == 1) {
                result = modMultiplication(result, base);
            }
            base = modMultiplication(base, base);
            b >>= 1;
        }
        return result;
    }

    private static int modInverse(int a, int mod) {
        return modPowerBinaryExponentiation(a, mod - 2); // MOD is prime
    }

    private static void computeFactorials() {
        factorial[0] = 1;
        for (int i = 1; i < MAX; i++) {
            factorial[i] = modMultiplication(factorial[i - 1], i);
        }

        inverseFactorial[MAX - 1] = modInverse(factorial[MAX - 1], MOD);
        for (int i = MAX - 2; i >= 0; i--) {
            inverseFactorial[i] = modMultiplication(inverseFactorial[i + 1], i + 1);
        }
    }

    public static int modFactorial(int n) {
        return factorial[n];
    }

    public static int modNCR(int n, int r) {
        if (r < 0 || r > n) return 0;
        return modMultiplication(factorial[n], modMultiplication(inverseFactorial[r], inverseFactorial[n - r]));
    }

    public static int modNPR(int n, int r) {
        if (r < 0 || r > n) return 0;
        return modMultiplication(factorial[n], inverseFactorial[n - r]);
    }
}

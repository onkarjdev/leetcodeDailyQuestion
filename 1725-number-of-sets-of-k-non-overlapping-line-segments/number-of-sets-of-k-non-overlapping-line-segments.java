class Solution {
    public int numberOfSets(int n, int k) {
        long MOD = 1_000_000_007L;
        int N = n + k - 1;
        int K = 2 * k;
        long numerator = 1;
        long denominator = 1;
        for (int i = 1; i <= K; i++) {
            numerator = (numerator * (N - i + 1)) % MOD;
            denominator = (denominator * i) % MOD;
        }
        long denominatorInverse = modInverse(denominator, MOD);

        return (int) ((numerator * denominatorInverse) % MOD);
    }

    private long modInverse(long base, long mod) {
        return power(base, mod - 2, mod);
    }

    private long power(long base, long exp, long mod) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = (res * base) % mod;
            }
            base = (base * base) % mod;
            exp >>= 1;
        }
        return res;
    }
}
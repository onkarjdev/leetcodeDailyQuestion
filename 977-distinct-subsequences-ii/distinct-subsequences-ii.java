class Solution {
    private static final int MOD = (int) 1e9 + 7;
    public int distinctSubseqII(String s) {
        int[] dp = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            int charIndex = s.charAt(i) - 'a';
            dp[charIndex] = sum(dp) + 1;
        }
        return sum(dp);
    }
    private int sum(int[] arr) {
        int total = 0;
        for (int value : arr) {
            total = (total + value) % MOD;
        }
      
        return total;
    }
}

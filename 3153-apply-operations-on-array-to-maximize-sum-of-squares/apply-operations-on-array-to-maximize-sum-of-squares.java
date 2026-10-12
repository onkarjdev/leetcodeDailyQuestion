import java.util.List;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] bitCount = new int[31];
        for (int num : nums) {
            for (int i = 0; i < 31; i++) {
                if ((num & (1 << i)) != 0) {
                    bitCount[i]++;
                }
            }
        }
        long totalSumOfSquares = 0;
        long MOD = 1_000_000_007;
        for (int i = 0; i < k; i++) {
            long currentNum = 0;
            for (int bit = 30; bit >= 0; bit--) {
                if (bitCount[bit] > 0) {
                    currentNum |= (1L << bit);
                    bitCount[bit]--;
                }
            }
            totalSumOfSquares = (totalSumOfSquares + (currentNum * currentNum) % MOD) % MOD;
        }
        return (int) totalSumOfSquares;
    }
}
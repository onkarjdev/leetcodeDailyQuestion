import java.util.*;

class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] cost = new long[n - x + 1];

        // Sliding window data structures to find median cost
        TreeMap<Integer, Integer> left = new TreeMap<>();   // Max half
        TreeMap<Integer, Integer> right = new TreeMap<>();  // Min half
        int leftSize = 0, rightSize = 0;
        long leftSum = 0, rightSum = 0;

        int maxLeftCount = (x + 1) / 2;
        int maxRightCount = x / 2;

        // Helper functions for TreeMap multiset operations
        Runnable balance = () -> {}; // Defined via lambda inside code scope logic below
        
        for (int i = 0; i < n; i++) {
            // Add nums[i]
            if (leftSize == 0 || nums[i] <= left.lastKey()) {
                left.put(nums[i], left.getOrDefault(nums[i], 0) + 1);
                leftSum += nums[i];
                leftSize++;
            } else {
                right.put(nums[i], right.getOrDefault(nums[i], 0) + 1);
                rightSum += nums[i];
                rightSize++;
            }

            // Remove nums[i - x] if window exceeds size x
            if (i >= x) {
                int out = nums[i - x];
                if (left.containsKey(out)) {
                    int count = left.get(out);
                    if (count == 1) left.remove(out);
                    else left.put(out, count - 1);
                    leftSum -= out;
                    leftSize--;
                } else {
                    int count = right.get(out);
                    if (count == 1) right.remove(out);
                    else right.put(out, count - 1);
                    rightSum -= out;
                    rightSize--;
                }
            }

            // Balance the two halves
            while (leftSize > maxLeftCount) {
                int val = left.lastKey();
                int count = left.get(val);
                if (count == 1) left.remove(val);
                else left.put(val, count - 1);
                leftSum -= val;
                leftSize--;

                right.put(val, right.getOrDefault(val, 0) + 1);
                rightSum += val;
                rightSize++;
            }

            while (leftSize < maxLeftCount && rightSize > 0) {
                int val = right.firstKey();
                int count = right.get(val);
                if (count == 1) right.remove(val);
                else right.put(val, count - 1);
                rightSum -= val;
                rightSize--;

                left.put(val, left.getOrDefault(val, 0) + 1);
                leftSum += val;
                leftSize++;
            }

            // If a valid window of size x is formed, record its cost
            if (i >= x - 1) {
                long median = left.lastKey();
                long currentCost = (median * leftSize - leftSum) + (rightSum - median * rightSize);
                cost[i - x + 1] = currentCost;
            }
        }

        // DP to find minimum cost to select k non-overlapping subarrays of size x
        long[] dp = new long[n + 1];
        long INF = (long) 1e18;

        for (int c = 1; c <= k; c++) {
            long[] nextDp = new long[n + 1];
            Arrays.fill(nextDp, INF);

            for (int i = c * x; i <= n; i++) {
                // Option 1: Don't place a subarray ending at i-1
                nextDp[i] = nextDp[i - 1];

                // Option 2: Place a subarray ending at i-1 (starts at i-x)
                if (dp[i - x] != INF) {
                    nextDp[i] = Math.min(nextDp[i], dp[i - x] + cost[i - x]);
                }
            }
            dp = nextDp;
        }

        return dp[n];
    }
}
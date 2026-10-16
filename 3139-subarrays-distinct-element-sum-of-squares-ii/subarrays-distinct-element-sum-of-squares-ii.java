import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final int MOD = 1_000_000_007;

    private long[] sumSq;
    private long[] sumCount;
    private long[] lazy;

    private void push(int node, int l, int r) {
        if (lazy[node] != 0) {
            long x = lazy[node];
            int len = r - l + 1;
            
            // Update sum of squares and sum of counts
            long newSq = (sumSq[node] + 2 * x * sumCount[node] % MOD + len * (x * x % MOD) % MOD) % MOD;
            long newCnt = (sumCount[node] + len * x % MOD) % MOD;
            
            sumSq[node] = newSq;
            sumCount[node] = newCnt;

            if (l != r) {
                lazy[2 * node] = (lazy[2 * node] + x) % MOD;
                lazy[2 * node + 1] = (lazy[2 * node + 1] + x) % MOD;
            }
            lazy[node] = 0;
        }
    }

    private void update(int node, int l, int r, int ql, int qr) {
        push(node, l, r);
        if (ql > r || qr < l) {
            return;
        }
        if (ql <= l && r <= qr) {
            lazy[node] = (lazy[node] + 1) % MOD;
            push(node, l, r);
            return;
        }
        int mid = l + (r - l) / 2;
        update(2 * node, l, mid, ql, qr);
        update(2 * node + 1, mid + 1, r, ql, qr);
        
        sumSq[node] = (sumSq[2 * node] + sumSq[2 * node + 1]) % MOD;
        sumCount[node] = (sumCount[2 * node] + sumCount[2 * node + 1]) % MOD;
    }

    public int sumCounts(int[] nums) {
        int n = nums.length;
        sumSq = new long[4 * n];
        sumCount = new long[4 * n];
        lazy = new long[4 * n];

        Map<Integer, Integer> lastSeen = new HashMap<>();
        long totalSum = 0;

        for (int r = 0; r < n; r++) {
            int l = lastSeen.getOrDefault(nums[r], -1) + 1;
            update(1, 0, n - 1, l, r);
            lastSeen.put(nums[r], r);
            totalSum = (totalSum + sumSq[1]) % MOD;
        }

        return (int) totalSum;
    }
}
class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int minPrice = Integer.MAX_VALUE;

        // gain[i] = 1 (for buying item i) + number of items j != i where items[i][0] divides items[j][0]
        int[] gain = new int[n];
        for (int i = 0; i < n; i++) {
            minPrice = Math.min(minPrice, items[i][1]);
            int count = 1;
            int fi = items[i][0];
            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % fi == 0) {
                    count++;
                }
            }
            gain[i] = count;
        }

        // 0/1 knapsack over distinct item types bought at least once
        int[] dp = new int[budget + 1];
        for (int i = 0; i < n; i++) {
            int cost = items[i][1];
            int val = gain[i];
            for (int b = budget; b >= cost; b--) {
                dp[b] = Math.max(dp[b], dp[b - cost] + val);
            }
        }

        // Spend any leftover budget on the cheapest item for +1 copy per minPrice
        int ans = 0;
        for (int b = 0; b <= budget; b++) {
            int extra = (budget - b) / minPrice;
            ans = Math.max(ans, dp[b] + extra);
        }

        return ans;
    }
}
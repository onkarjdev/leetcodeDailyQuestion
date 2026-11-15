class Solution {
    public int numOfSubarrays(int[] arr) {
        long oddCount = 0;
        long evenCount = 1; // Base case: prefix sum 0 is even
        long currentSum = 0;
        long totalOddSubarrays = 0;
        int MOD = 1_000_000_007;

        for (int num : arr) {
            currentSum += num;

            if (currentSum % 2 == 1) {
                // If current prefix sum is odd, it forms an odd subarray sum 
                // with all previous even prefix sums.
                totalOddSubarrays = (totalOddSubarrays + evenCount) % MOD;
                oddCount++;
            } else {
                // If current prefix sum is even, it forms an odd subarray sum 
                // with all previous odd prefix sums.
                totalOddSubarrays = (totalOddSubarrays + oddCount) % MOD;
                evenCount++;
            }
        }

        return (int) totalOddSubarrays;
    }
}
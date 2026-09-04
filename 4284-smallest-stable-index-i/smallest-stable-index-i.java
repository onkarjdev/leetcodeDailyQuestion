class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        
        // Step 1: Precalculate suffix minimums
        int[] suffixMin = new int[n];
        suffixMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(nums[i], suffixMin[i + 1]);
        }
        
        // Step 2: Iterate from left to right tracking prefix maximum
        int prefixMax = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            prefixMax = Math.max(prefixMax, nums[i]);
            
            // Calculate instability score for index i
            long instability = (long) prefixMax - suffixMin[i];
            
            if (instability <= k) {
                return i;
            }
        }
        
        return -1;
    }
}
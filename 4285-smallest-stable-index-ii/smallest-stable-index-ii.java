class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int[] suffixMin = new int[n];
        suffixMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(suffixMin[i + 1], nums[i]);
        }
        int currentMax = nums[0];
        for (int i = 0; i < n; i++) {
            currentMax = Math.max(currentMax, nums[i]);
            
            if (currentMax - suffixMin[i] <= k) {
                return i;
            }
        }
        
        return -1;
    }
}
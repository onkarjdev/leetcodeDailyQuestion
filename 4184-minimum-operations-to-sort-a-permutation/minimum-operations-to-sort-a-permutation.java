class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int zero = -1;
        
        // Find the index of 0
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zero = i;
                break;
            }
        }
        
        // Check if elements increase to the right (step = 1)
        boolean check1 = true;
        for (int i = 1; i < n; i++) {
            int prev = (zero + (i - 1)) % n;
            int curr = (zero + i) % n;
            if (nums[prev] > nums[curr]) {
                check1 = false;
                break;
            }
        }
        
        // Check if elements increase to the left (step = -1)
        boolean check2 = true;
        for (int i = 1; i < n; i++) {
            int prev = (zero - (i - 1) + n) % n;
            int curr = (zero - i + n) % n;
            if (nums[prev] > nums[curr]) {
                check2 = false;
                break;
            }
        }
        
        int ans = Integer.MAX_VALUE;
        
        if (check1) {
            ans = Math.min(ans, zero);         // Rotate left by `zero` positions
            ans = Math.min(ans, n - zero + 2); // Reverse, rotate, reverse back
        }
        
        if (check2) {
            ans = Math.min(ans, zero + 2);     // Rotate, then reverse
            ans = Math.min(ans, n - zero);     // Reverse, then rotate
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
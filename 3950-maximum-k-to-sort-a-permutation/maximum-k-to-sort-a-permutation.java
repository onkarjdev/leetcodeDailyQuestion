class Solution {
    public int sortPermutation(int[] nums) {
        int ans = -1;
        for (int i = 0; i < nums.length; ++i) {
            // If the element is not in its correct position
            if (i != nums[i]) {
                ans &= nums[i];
            }
        }
        // If no elements were misplaced, ans remains -1, so return 0
        return Math.max(ans, 0);
    }
}
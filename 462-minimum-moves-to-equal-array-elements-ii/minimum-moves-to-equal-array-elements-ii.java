import java.util.Arrays;

class Solution {
    public int minMoves2(int[] nums) {
        // Sort the array to easily find the median
        Arrays.sort(nums);
        
        // The median element
        int median = nums[nums.length / 2];
        
        int moves = 0;
        for (int num : nums) {
            moves += Math.abs(num - median);
        }
        
        return moves;
    }
}
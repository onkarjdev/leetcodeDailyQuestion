import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] squares = new long[n];
        
        // Compute squares of all elements
        for (int i = 0; i < n; i++) {
            squares[i] = (long) nums[i] * nums[i];
        }
        
        // Sort squares in ascending order
        Arrays.sort(squares);
        
        long score = 0;
        int k = n / 2; // Number of elements to be subtracted
        
        // Subtract the smallest k squares and add the rest
        for (int i = 0; i < n; i++) {
            if (i < k) {
                score -= squares[i];
            } else {
                score += squares[i];
            }
        }
        
        return score;
    }
}
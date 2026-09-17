import java.util.Arrays;

class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] minLen = new int[n];
        Arrays.fill(minLen, Integer.MAX_VALUE);
        int left = 0;
        int sum = 0;
        int minSoFar = Integer.MAX_VALUE;
        int ans = Integer.MAX_VALUE; 
        for (int right = 0; right < n; right++) {
            sum += arr[right];
            while (sum > target) {
                sum -= arr[left];
                left++;
            }
            if (sum == target) {
                int currLen = right - left + 1;
                if (left > 0 && minLen[left - 1] != Integer.MAX_VALUE) {
                    ans = Math.min(ans, currLen + minLen[left - 1]);
                }
                
                minSoFar = Math.min(minSoFar, currLen);
            }
            minLen[right] = minSoFar;
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
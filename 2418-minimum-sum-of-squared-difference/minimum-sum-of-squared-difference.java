class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long totalK = (long) k1 + k2;
        long[] count = new long[100001];
        long totalDiffSum = 0;
        
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            count[diff]++;
            totalDiffSum += diff;
        }
        if (totalDiffSum <= totalK) {
            return 0;
        }
        for (int d = 100000; d > 0 && totalK > 0; d--) {
            if (count[d] > 0) {
                long take = Math.min(totalK, count[d]);
                count[d] -= take;
                count[d - 1] += take;
                totalK -= take;
            }
        }
        long result = 0;
        for (int d = 1; d <= 100000; d++) {
            if (count[d] > 0) {
                result += count[d] * (long) d * d;
            }
        }
        
        return result;
    }
}
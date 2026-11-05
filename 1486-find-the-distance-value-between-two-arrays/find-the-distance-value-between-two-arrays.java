import java.util.Arrays;

class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);
        int distanceValue = 0;

        for (int x : arr1) {
            if (isValid(arr2, x, d)) {
                distanceValue++;
            }
        }

        return distanceValue;
    }

    private boolean isValid(int[] arr2, int target, int d) {
        int left = 0;
        int right = arr2.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            // If the element is within range [target - d, target + d], it violates the condition
            if (Math.abs(arr2[mid] - target) <= d) {
                return false;
            }

            if (arr2[mid] < target - d) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return true;
    }
}
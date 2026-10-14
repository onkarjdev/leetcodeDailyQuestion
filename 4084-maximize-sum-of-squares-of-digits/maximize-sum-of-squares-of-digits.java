import java.util.Arrays;

class Solution {
    public String maxSumOfSquares(int num, int sum) {
        // If the maximum possible sum with 'num' digits is less than 'sum', return ""
        if ((long) num * 9 < sum) {
            return "";
        }
        
        int n9 = sum / 9;
        int rem = sum % 9;
        
        StringBuilder sb = new StringBuilder();
        
        // Append all the '9's
        for (int i = 0; i < n9; i++) {
            sb.append('9');
        }
        
        // Append the remainder digit if it exists
        if (rem > 0) {
            sb.append((char) ('0' + rem));
        }
        
        // Fill the rest of the positions with '0's to match length 'num'
        while (sb.length() < num) {
            sb.append('0');
        }
        
        return sb.toString();
    }
}
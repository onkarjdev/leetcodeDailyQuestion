import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] l = new int[26];
        int[] r = new int[26];
        Arrays.fill(l, -1);
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (l[c] == -1) {
                l[c] = i;
            }
            r[c] = i;
        }
        List<String> result = new ArrayList<>();
        int right = -1; 
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (i == l[c]) {
                int newRight = checkSubstring(s, i, l, r);
                if (newRight != -1) {
                    if (i > right) {
                        result.add(""); 
                    }
                    right = newRight;
                    result.set(result.size() - 1, s.substring(i, right + 1));
                }
            }
        }

        return result;
    }
    private int checkSubstring(String s, int i, int[] l, int[] r) {
        int right = r[s.charAt(i) - 'a'];
        for (int j = i; j <= right; j++) {
            int c = s.charAt(j) - 'a';
            if (l[c] < i) {
                return -1;
            }
            right = Math.max(right, r[c]);
        }
        return right;
    }
}
class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        String result = "";
        int minLen = Integer.MAX_VALUE;
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '1') continue;
            
            int ones = 0;
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(j) == '1') {
                    ones++;
                }
                
                if (ones == k) {
                    String sub = s.substring(i, j + 1);
                    int len = sub.length();
                    
                    if (len < minLen) {
                        minLen = len;
                        result = sub;
                    } else if (len == minLen && sub.compareTo(result) < 0) {
                        result = sub;
                    }
                    break; 
                }
            }
        }
        
        return result;
    }
}
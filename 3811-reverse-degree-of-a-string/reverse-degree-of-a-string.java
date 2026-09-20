class Solution {
    public int reverseDegree(String s) {
        int totalDegree = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int reversedAlphabetIdx = 26 - (c - 'a');
            int stringIdx = i + 1;
            totalDegree += reversedAlphabetIdx * stringIdx;
        }
        
        return totalDegree;
    }
}
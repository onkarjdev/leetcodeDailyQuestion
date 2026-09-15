class Solution {
    private int[] memo;
    private boolean[][] isPalindrome;
    private String s;
    private int n;
    private int k;
    public int maxPalindromes(String s, int k) {
        this.n = s.length();
        this.s = s;
        this.k = k;
        this.memo = new int[n];
        Arrays.fill(memo, -1);
        this.isPalindrome = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(isPalindrome[i], true);
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                isPalindrome[i][j] = (s.charAt(i) == s.charAt(j)) && isPalindrome[i + 1][j - 1];
            }
        }
      
        // Start DFS from index 0 to find maximum palindromes
        return dfs(0);
    }

    /**
     * Depth-first search with memoization to find the maximum number of
     * non-overlapping palindromic substrings starting from index i.
     * 
     * @param i the starting index
     * @return the maximum number of palindromes from index i to the end
     */
    private int dfs(int i) {
        // Base case: reached the end of the string
        if (i >= n) {
            return 0;
        }
      
        // Return memoized result if already computed
        if (memo[i] != -1) {
            return memo[i];
        }
      
        // Option 1: Skip current character and continue from next position
        int maxPalindromes = dfs(i + 1);
      
        // Option 2: Try to form a palindrome starting at index i
        // Check all possible ending positions j where substring length >= k
        for (int j = i + k - 1; j < n; j++) {
            // If substring s[i..j] is a palindrome
            if (isPalindrome[i][j]) {
                // Take this palindrome and continue from j+1
                maxPalindromes = Math.max(maxPalindromes, 1 + dfs(j + 1));
            }
        }
      
        // Store the result in memoization array
        memo[i] = maxPalindromes;
        return maxPalindromes;
    }
}

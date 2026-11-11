class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long c1 = cost1;
        long c2 = cost2;
        long cBoth = costBoth;
        long n1 = need1;
        long n2 = need2;
        
        // Function to calculate total cost for a given number of type 3 items (i)
        // We can use a helper or evaluate for specific candidate values of i.
        // Candidate values for i: 0, n1, n2, Math.min(n1, n2), Math.max(n1, n2)
        // Also, since the function is convex, ternary search between 0 and Math.max(n1, n2) works in O(log(MAX_NEED)).
        
        long low = 0;
        long high = Math.max(n1, n2);
        long minCost = calculateCost(low, c1, c2, cBoth, n1, n2);
        minCost = Math.min(minCost, calculateCost(high, c1, c2, cBoth, n1, n2));
        
        while (high - low > 3) {
            long m1 = low + (high - low) / 3;
            long m2 = high - (high - low) / 3;
            
            long costM1 = calculateCost(m1, c1, c2, cBoth, n1, n2);
            long costM2 = calculateCost(m2, c1, c2, cBoth, n1, n2);
            
            if (costM1 < costM2) {
                high = m2;
            } else {
                low = m1;
            }
        }
        
        for (long i = low; i <= high; i++) {
            minCost = Math.min(minCost, calculateCost(i, c1, c2, cBoth, n1, n2));
        }
        
        return minCost;
    }
    
    private long calculateCost(long i, long c1, long c2, long cBoth, long n1, long n2) {
        long cost = i * cBoth;
        long rem1 = Math.max(0L, n1 - i);
        long rem2 = Math.max(0L, n2 - i);
        
        cost += rem1 * Math.min(c1, cBoth);
        cost += rem2 * Math.min(c2, cBoth);
        return cost;
    }
}
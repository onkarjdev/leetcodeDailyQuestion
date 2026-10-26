class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int validIslandsCount = 0;
        
        // Directions for 4-directional movement (up, down, left, right)
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If we find an unvisited land cell, explore the island
                if (grid[i][j] > 0) {
                    long islandSum = 0;
                    java.util.Queue<int[]> queue = new java.util.LinkedList<>();
                    
                    queue.offer(new int[]{i, j});
                    islandSum += grid[i][j];
                    grid[i][j] = 0; // Mark as visited by setting to 0
                    
                    while (!queue.isEmpty()) {
                        int[] curr = queue.poll();
                        int r = curr[0];
                        int c = curr[1];
                        
                        for (int d = 0; d < 4; d++) {
                            int nr = r + dr[d];
                            int nc = c + dc[d];
                            
                            if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] > 0) {
                                islandSum += grid[nr][nc];
                                grid[nr][nc] = 0; // Mark as visited
                                queue.offer(new int[]{nr, nc});
                            }
                        }
                    }
                    
                    // Check if the total value of the island is divisible by k
                    if (islandSum % k == 0) {
                        validIslandsCount++;
                    }
                }
            }
        }
        
        return validIslandsCount;
    }
}
class Solution {
    private int matchingNodeCount = 0;

    public int averageOfSubtree(TreeNode root) {
        matchingNodeCount = 0;
        dfs(root);
        return matchingNodeCount;
    }

    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);
        int currentSum = node.val + left[0] + right[0];
        int currentCount = 1 + left[1] + right[1];
        if (node.val == currentSum / currentCount) {
            matchingNodeCount++;
        }

        return new int[]{currentSum, currentCount};
    }
}
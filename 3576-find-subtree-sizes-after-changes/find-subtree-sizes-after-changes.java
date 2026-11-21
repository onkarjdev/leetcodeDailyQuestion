import java.util.*;

class Solution {
    private int[] newParent;
    private List<Integer>[] origAdj;
    private List<Integer>[] newAdj;
    private int[] answer;

    @SuppressWarnings("unchecked")
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        origAdj = new ArrayList[n];
        newAdj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            origAdj[i] = new ArrayList<>();
            newAdj[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            origAdj[parent[i]].add(i);
        }

        newParent = new int[n];
        newParent[0] = -1;
        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        // Step 1: Find the new parent for every node using original ancestors
        dfsFindParents(0, parent, s, lastSeen);

        // Step 2: Build the new tree
        for (int i = 1; i < n; i++) {
            newAdj[newParent[i]].add(i);
        }

        // Step 3: Compute subtree sizes in the final tree
        answer = new int[n];
        dfsSubtreeSize(0);
        return answer;
    }

    private void dfsFindParents(int u, int[] parent, String s, int[] lastSeen) {
        int c = s.charAt(u) - 'a';
        if (u > 0) {
            newParent[u] = (lastSeen[c] != -1) ? lastSeen[c] : parent[u];
        }

        int prev = lastSeen[c];
        lastSeen[c] = u;

        for (int v : origAdj[u]) {
            dfsFindParents(v, parent, s, lastSeen);
        }

        lastSeen[c] = prev;
    }

    private int dfsSubtreeSize(int u) {
        int size = 1;
        for (int v : newAdj[u]) {
            size += dfsSubtreeSize(v);
        }
        answer[u] = size;
        return size;
    }
}
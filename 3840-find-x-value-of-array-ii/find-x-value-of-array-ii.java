class Solution {
    private int K;
    private int[] endVal;
    private int[] count;
    private int currentP;
    private int ans;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.K = k;
        int n = nums.length;

        // Flattened arrays for memory efficiency and speed
        endVal = new int[4 * n * k];
        count = new int[4 * n * k * k];

        build(1, 0, n - 1, nums);

        int qLen = queries.length;
        int[] res = new int[qLen];

        for (int i = 0; i < qLen; i++) {
            int idx = queries[i][0];
            int val = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            // 1. Persistent update on nums[idx]
            update(1, 0, n - 1, idx, val);

            // 2. Query range [start, n - 1]
            currentP = 1 % K; // Handles k = 1 edge case
            ans = 0;
            query(1, 0, n - 1, start, n - 1, x);

            res[i] = ans;
        }

        return res;
    }

    private void build(int node, int l, int r, int[] nums) {
        if (l == r) {
            setLeaf(node, nums[l]);
            return;
        }
        int mid = l + (r - l) / 2;
        build(2 * node, l, mid, nums);
        build(2 * node + 1, mid + 1, r, nums);
        merge(node, 2 * node, 2 * node + 1);
    }

    private void setLeaf(int node, int val) {
        int modVal = val % K;
        int baseEnd = node * K;
        int baseCnt = node * K * K;

        for (int p = 0; p < K; p++) {
            int newP = (p * modVal) % K;
            endVal[baseEnd + p] = newP;
            for (int x = 0; x < K; x++) {
                count[baseCnt + p * K + x] = (x == newP) ? 1 : 0;
            }
        }
    }

    private void merge(int node, int left, int right) {
        int baseEndNode = node * K;
        int baseEndLeft = left * K;
        int baseEndRight = right * K;

        int baseCntNode = node * K * K;
        int baseCntLeft = left * K * K;
        int baseCntRight = right * K * K;

        for (int p = 0; p < K; p++) {
            int midP = endVal[baseEndLeft + p];
            endVal[baseEndNode + p] = endVal[baseEndRight + midP];

            int pK = p * K;
            int midPK = midP * K;

            for (int x = 0; x < K; x++) {
                count[baseCntNode + pK + x] = 
                    count[baseCntLeft + pK + x] + count[baseCntRight + midPK + x];
            }
        }
    }

    private void update(int node, int l, int r, int idx, int val) {
        if (l == r) {
            setLeaf(node, val);
            return;
        }
        int mid = l + (r - l) / 2;
        if (idx <= mid) {
            update(2 * node, l, mid, idx, val);
        } else {
            update(2 * node + 1, mid + 1, r, idx, val);
        }
        merge(node, 2 * node, 2 * node + 1);
    }

    private void query(int node, int l, int r, int ql, int qr, int targetX) {
        if (ql <= l && r <= qr) {
            int baseEndNode = node * K;
            int baseCntNode = node * K * K;

            ans += count[baseCntNode + currentP * K + targetX];
            currentP = endVal[baseEndNode + currentP];
            return;
        }

        int mid = l + (r - l) / 2;
        if (ql <= mid) {
            query(2 * node, l, mid, ql, qr, targetX);
        }
        if (qr > mid) {
            query(2 * node + 1, mid + 1, r, ql, qr, targetX);
        }
    }
}
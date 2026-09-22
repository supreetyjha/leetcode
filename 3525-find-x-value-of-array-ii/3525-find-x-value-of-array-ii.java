class Solution {
    static class Node {
        int[] remain = new int[5];
        int prod = 1;

        Node() {}

        Node(int val, int k) {
            val %= k;
            remain[val] = 1;
            prod = val;
        }
    }

    private Node merge(Node left, Node right, int k) {
        Node res = new Node();
        res.prod = (left.prod * right.prod) % k;

        // Copy prefix remainders from the left segment
        for (int i = 0; i < k; i++) {
            res.remain[i] = left.remain[i];
        }

        // Add shifted prefix remainders from the right segment
        for (int i = 0; i < k; i++) {
            int rem = (i * left.prod) % k;
            res.remain[rem] += right.remain[i];
        }

        return res;
    }

    private void build(int treeIdx, int lo, int hi, int[] nums, int k, Node[] tree) {
        if (lo == hi) {
            tree[treeIdx] = new Node(nums[lo], k);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        build(2 * treeIdx + 1, lo, mid, nums, k, tree);
        build(2 * treeIdx + 2, mid + 1, hi, nums, k, tree);
        tree[treeIdx] = merge(tree[2 * treeIdx + 1], tree[2 * treeIdx + 2], k);
    }

    private void update(int treeIdx, int lo, int hi, int idx, int val, int k, Node[] tree) {
        if (lo == hi) {
            tree[treeIdx] = new Node(val, k);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        if (idx <= mid) {
            update(2 * treeIdx + 1, lo, mid, idx, val, k, tree);
        } else {
            update(2 * treeIdx + 2, mid + 1, hi, idx, val, k, tree);
        }
        tree[treeIdx] = merge(tree[2 * treeIdx + 1], tree[2 * treeIdx + 2], k);
    }

    private Node query(int treeIdx, int lo, int hi, int ql, int qr, int k, Node[] tree) {
        if (ql <= lo && hi <= qr) {
            return tree[treeIdx];
        }
        int mid = lo + (hi - lo) / 2;
        if (qr <= mid) {
            return query(2 * treeIdx + 1, lo, mid, ql, qr, k, tree);
        }
        if (ql > mid) {
            return query(2 * treeIdx + 2, mid + 1, hi, ql, qr, k, tree);
        }

        Node leftRes = query(2 * treeIdx + 1, lo, mid, ql, qr, k, tree);
        Node rightRes = query(2 * treeIdx + 2, mid + 1, hi, ql, qr, k, tree);
        return merge(leftRes, rightRes, k);
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        int n = nums.length;
        Node[] tree = new Node[4 * n];
        build(0, 0, n - 1, nums, k, tree);

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int idx = queries[i][0];
            int val = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            // Point update (persists)
            update(0, 0, n - 1, idx, val % k, k, tree);

            // Query the range [start, n - 1]
            Node res = query(0, 0, n - 1, start, n - 1, k, tree);
            ans[i] = res.remain[x];
        }

        return ans;
    }
}
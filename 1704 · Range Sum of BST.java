//My Solution: find correct node, and dfs to extend, do not traverse every node
public class Solution {
    /**
     * @param root: the root node
     * @param L: an integer
     * @param R: an integer
     * @return: the sum
     */
    int ans = 0;
    public int rangeSumBST(TreeNode root, int L, int R) {
        if(root == null) return 0;
        if(root.val < L) return rangeSumBST(root.right, L, R);
        if(root.val > R) return rangeSumBST(root.left, L, R);
        ans += root.val;
        rangeSumBST(root.right, L, R);
        rangeSumBST(root.left, L, R);
        return ans;
    }
}

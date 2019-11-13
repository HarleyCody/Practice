/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    int ans = 0;
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        // 1: curLevel + subMaxLevel;
        return ans = 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}

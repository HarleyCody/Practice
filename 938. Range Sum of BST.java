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
    // recursion. left right, BST not BT. BST left < root < right;
    int ans = 0;
    public int rangeSumBST(TreeNode root, int L, int R) {
        if(root == null) return 0;
        if(L <= root.val && root.val <= R){
            ans += root.val;
        }
        rangeSumBST(root.left, L, R);
        rangeSumBST(root.right, L, R);
        return ans;
    }
}

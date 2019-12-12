__________________________________________My Solution(reversed inorder traverse)______________________________________________
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
    int max = 0;
    public TreeNode bstToGst(TreeNode root) {
        if(root == null) return null;
        helper(root);
        return root;
    }
    // right mid left;
    private void helper(TreeNode root){
        if(root == null) return;
        if(root.right != null) helper(root.right);
        root.val += max;
        max = Math.max(root.val, max);
        if(root.left != null) helper(root.left);
    }
}

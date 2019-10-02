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
    // in order traverse from right, mid, left.
    int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        if(root == null) return root;
        // traverse left
        if(root.right != null){
            convertBST(root.right);
        }
        // record sum of every node.
        sum += root.val;
        // update current value for current node.
        root.val = sum;
        // traverse right
        if(root.left != null){
            convertBST(root.left);
        }
        return root;
    }
}

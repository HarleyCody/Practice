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
    List<Integer> ans;
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        ans = new ArrayList();
        if(root == null) return ans;
        ans.add(root.val);
        leftmost(root.left);
        // leaves in left subtree
        leaves(root.left);
        // leaves in right subtree
        leaves(root.right);
        rightmost(root.right);
        return ans;
    }
    // find leftmost node, leaves that are leftmost can be added by leaves( ), if current leave is left most, then there is no other leaves can be more left than the leaves
    private void leftmost(TreeNode root){
        // path to the leftmost leaf node
        if(root == null || root.left == null && root.right == null) return;
        ans.add(root.val);
        // only has right node, root has left and right will not invoke this step, it will recurse back; its possible child nodes of this node is rightmos
        if(root.left == null) leftmost(root.right);
        leftmost(root.left);
    }
    //find leaves, invoked from root.left and root.right, anti-colockwise
    private void leaves(TreeNode root){
        if(root == null) return;
        if(root.left == null && root.right == null)ans.add(root.val);
        leaves(root.left);
        leaves(root.right);
        return;
    }
     // find rightmost node, leaves that are rightmost can be added by leaves( ), if current leaf is right most, then there is no other leaves can be more right than the leaf
    // add value at last(reverse order): reach last first then add value. leaves will be ignored
    private void rightmost(TreeNode root){
        // path to the leftmost leaf node
        if(root == null || root.left == null && root.right == null)return;
        // only has right node, root has left and right will not invoke this step, it will recurse back; its possible child nodes of this node is rightmost
        if(root.right == null) rightmost(root.left);
        else rightmost(root.right);
        ans.add(root.val);
    }
}

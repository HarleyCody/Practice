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
    public boolean isValidBST(TreeNode root) {
        if(root == null) return true;
        return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);//每个结点限定最大最小值
    }
    public boolean helper(TreeNode root, long min, long max){
        if(root == null) return true;
        if(root.val <= min || root.val >= max)return false; // 超出范围返回false;
        return (helper(root.left,min, root.val) && helper(root.right, root.val, max));//递归调用，左节点限定最大值，右节点限定最小值
    }
}

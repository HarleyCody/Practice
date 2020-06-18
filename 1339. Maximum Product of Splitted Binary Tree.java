/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    //same like calculate max area for sum of two boarder.
    //the most closest two boarders make max area.
    // static sum of boarder, find way to split it to two lines so they made up a rectangle with max area
    int mod = 1000000007;
    int diff = Integer.MAX_VALUE, sum = 0, half = 0;
    public int maxProduct(TreeNode root) {
        sum = treeSum(root);
        treeSum(root);
        return (int)((long)half * (sum - half) % 1000000007);
    }
    private int treeSum(TreeNode root){
        if(root == null){
            return 0;
        }
        int ans = root.val;
        ans += treeSum(root.left);
        ans += treeSum(root.right);
        if(sum != 0 && Math.abs(sum / 2 - ans) < diff){
            diff = Math.abs(sum / 2 - ans);
            half = ans;
        }
        return ans;
    }
}

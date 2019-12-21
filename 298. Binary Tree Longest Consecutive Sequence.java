_______________________________________________________My Solution(best)_______________________________________________________
class Solution {
// pick up max in every node. recursive traverse whole tree once
    int ans = 0;
    public int longestConsecutive(TreeNode root) {
        if(root == null) return 0;
        helper(root, 1);
        return ans;
    }
    // record cosecutive length. compare with ans;
    private void helper(TreeNode root, int len){
        ans = Math.max(len, ans);
        if(root.left != null){
            // consecutive len + 1
            if(root.val == root.left.val - 1){
                helper(root.left, len + 1);
            // not consecutive reset len as start;
            }else{
                helper(root.left, 1);
            }
        }
        // same as left;
        if(root.right != null){
            if(root.val == root.right.val - 1){
                helper(root.right, len + 1);
            }else{
                helper(root.right, 1);
            }
        }
    }
}

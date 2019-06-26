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
    List<Integer> ans = new LinkedList();
    public List<Integer> rightSideView(TreeNode root) {
        helper(root, 0);
        return ans;
    }
    
    //Preorder Traverse -> record from top to bottom, recursion will update data if current level is recorded in list
    public void helper(TreeNode root, int level){
        if(root == null) return;
        if(level >= ans.size()) ans.add(root.val);
        else ans.set(level, root.val);
        helper(root.left, level + 1);
        helper(root.right, level + 1);
    }
}

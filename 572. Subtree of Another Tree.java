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
    // check every possible node in s 
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if(t == null && s == null) return true;
        else if(t == null || s == null)return false;
        if(s.val == t.val && isSame(s, t)){
            return true;
        }
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }
    // if s is same tree  with t;
    private boolean isSame(TreeNode s, TreeNode t){
        if(s == null && t == null) return true;
        else if(s == null || t == null) return false;
        return(s.val == t.val && isSame(s.left, t.left) && isSame(s.right, t.right));
    }
}

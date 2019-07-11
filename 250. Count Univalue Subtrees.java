_________________________________________________________Best Solution_________________________________________________________


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
    private int result;
    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) {
            return 0;
        }
        result = 0;
        helper(root);
        return result;
    }
    
    private int helper(TreeNode node) {
        // leaves node is a valid answer
        if (node.left == null && node.right == null) {
            result ++;
            return node.val;
        }
        int left = node.val;
        if (node.left != null) {
            left = helper(node.left);
        }
        
         
        int right = node.val;
        
        if (node.right != null) {
            right = helper(node.right);
        }
        
        if (left == Integer.MIN_VALUE || right == Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        // all nodes are node.val
        if (left == right && right == node.val) {
            result ++;
            return node.val;
        }
        return Integer.MIN_VALUE;
    }
}
_________________________________________________________My Solution___________________________________________________________
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
    public int countUnivalSubtrees(TreeNode root) {
        if(root == null) return 0;
        // traverse every node try to find a valid substree
        if(isUniTree(root, root.val)){
            ++ans;
        }
        countUnivalSubtrees(root.left);
        countUnivalSubtrees(root.right);
        return ans;
    }
    // check is valid
    private boolean isUniTree(TreeNode root, int val){
        if(root == null) return true;
        if(root.val != val) return false;
        return isUniTree(root.left, val) && isUniTree(root.right, val);
    }
}

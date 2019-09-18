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
    // recursively find check root isBST, and return it's size;
    public int largestBSTSubtree(TreeNode root) {
        if(root == null) return 0;
        int ans = isBST(root, null, null);
        if(ans > 0) return ans;
        ans = Math.max(largestBSTSubtree(root.left), ans);
        ans = Math.max(largestBSTSubtree(root.right), ans);
        return ans;
    }
    // check is root is rootNode of a BST, if is return it's size;
    private int isBST(TreeNode root, TreeNode min, TreeNode max){
        if(root == null) return 0;
        
        if(max != null && root.val >= max.val) return 0;
        if(min != null && root.val <= min.val) return 0;
        
        int size = 1;

        if(root.left != null){
            //left is not bst, root will not be bst return 0 
            int lSize = isBST(root.left, min, root);
            if(lSize == 0) return 0;
            size += lSize;
        }
        if(root.right != null){
            //right is not bst, root will not be bst return 0 
            int rSize = isBST(root.right, root, max);
            if(rSize == 0) return 0;
            size += rSize;
        }
        return size;
    }
}

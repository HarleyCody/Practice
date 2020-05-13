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
    // serialize tree into list;
    // reconstruct recursivly by list;
    List<TreeNode> recorder = new ArrayList();
    public TreeNode balanceBST(TreeNode root) {
        serializeTree(root);
        return reconstructTree(0, recorder.size());
    }
    
    private void serializeTree(TreeNode root){
        if(root.left != null){
            serializeTree(root.left);
        }
        
        recorder.add(root);
        
        if(root.right != null){
            serializeTree(root.right);
        }
    }
    
    private TreeNode reconstructTree(int s, int e){
        if(s == e){
            return null;
        }
        
        int mid = (s + e) / 2;
        TreeNode root = recorder.get(mid);
        root.left = reconstructTree(s, mid);
        root.right = reconstructTree(mid + 1, e);
        return root;
    }
}

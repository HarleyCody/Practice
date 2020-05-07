________________________________________________________________My(Best) Solution____________________________________________________________
class Solution {
    //rule, move left to right and connect right to lastNode of left;
    TreeNode lastNode = null;
    public void flatten(TreeNode root) {
        if(root == null) return;
        // lastNode of flattern;
        if(root.left == null && root.right == null){
            lastNode = root;
            return;
        }
        // move left to right
        TreeNode right = null;
        if(root.left != null){
            right = root.right;
            root.right = root.left;
            root.left = null;
        }
        // after move left to right, flatten left Node which is root.right currently;
        flatten(root.right);
        
        // connect and flatten right nodes;
        if(right != null){
            lastNode.right = right;
            flatten(right);
        }
    }
}

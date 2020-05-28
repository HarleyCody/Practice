class Solution {
    // recursion
    // if children both are null, return min of them
    // one of them is not null return min of the child
    public int minDepth(TreeNode root) {
        if(root == null){
            return 0;
        }else if(root.left != null && root.right != null){
            return 1 + Math.min(minDepth(root.left), minDepth(root.right));
        }else if(root.left != null){
            return 1 + minDepth(root.left);
        }else{
            return 1 + minDepth(root.right);
        }
    }
}

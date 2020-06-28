class Solution {
    // one scan get height layer by layer, once heights differ than 1 return -1, reach bottom go up;
    public boolean isBalanced(TreeNode root) {
        if(root == null){
            return true;
        }
        return height(root) >= 0;
    }
    
    private int height(TreeNode root){
        if(root == null){
            return 0;
        }
        int left = 1 + height(root.left);
        int right = 1 + height(root.right);
        
        if(left == 0 || right == 0){
            return -1;
        }
        if(Math.abs(left - right) >= 2){
            return -1;
        }
        return Math.max(left, right);
    }
}

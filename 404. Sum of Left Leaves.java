_____________________________________________________________________________My Solution________________________________________________________________________________________
// preorder traverse, Only add left leaves
class Solution {
    int sum = 0;
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null){
            return 0;
        }
        if(root.left != null){
            getSum(root.left, 1);
        }
        if(root.right != null){
            getSum(root.right, 0);
        }
        
        return sum;
    }
    
    private void getSum(TreeNode root, int isLeft){
        if(root.left == null && root.right == null){
            sum += root.val * isLeft;
            return;
        }
        if(root.left != null){
            getSum(root.left, 1);
        }
        if(root.right != null){
            getSum(root.right, 0);
        }
    }
}

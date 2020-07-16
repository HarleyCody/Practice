class Solution {
    //include cur node or not incude curNode
    // if not include curNode, the max will be
    // include left but not right, include right but not left
    // include left and right, do not include both of them
    int ans = 0;
    public int rob(TreeNode root) {
        if(root == null){
            return 0;
        }
        robSum(root);
        
        return ans;
    }
    private int[] robSum(TreeNode root){
        if(root == null){
            return new int[]{0, 0};
        }
        int[] l = robSum(root.left);
        int[] r = robSum(root.right);
        
        int curIn = l[0] + r[0] + root.val;
        int curEx = Math.max(Math.max(Math.max(l[1] + r[0], l[0] + r[1]), l[1] + r[1]), l[0] + r[0]);
        ans = Math.max(Math.max(curIn, curEx), ans);
        return new int[]{curEx, curIn};
    }
}

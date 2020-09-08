____________________________________________________________________________________My Solution______________________________________________________________________
class Solution {
// from top to bottom, at leave just add leave value, otherwise add value and times 2, start with presum 0;
// update ans at leave
    int ans = 0;
    public int sumRootToLeaf(TreeNode root) {
        if(root == null){
            return 0;
        }
        getSum(root, 0);
        return ans;
    }
    
    private void getSum(TreeNode cur, int preSum){
        if(cur.left == null && cur.right == null){
            ans += preSum + cur.val;
            return;
        }
        int curSum = (preSum + cur.val) * 2;
        if(cur.left != null){
            getSum(cur.left, curSum);
        }
        if(cur.right != null){
            getSum(cur.right, curSum);
        }
    }
}

______________________________________________________My Solution 1______________________________________________________________
class Solution {
    // bottom up getNum meanwhile sumUp for root, 
    // calculate double average and choose max
    double ans = 0.0;
    public double maximumAverageSubtree(TreeNode root) {
        getNum(root);
        return ans;
    }
    private int getNum(TreeNode root){
        if(root == null) return 0;
        int num = 1;
        if(root.left != null){
            num += getNum(root.left);
            root.val += root.left.val;
        }
        if(root.right != null){
            num += getNum(root.right);
            root.val += root.right.val;
        }
        ans = Math.max(ans, (double)root.val/num);
        return num;
    }
}
______________________________________________________My Solution 2______________________________________________________________
class Solution {
    // one function. 
    // sum double value of sum of subtree, change val to record num of node
    // return double if root is not og otherwise return ans;
    
    double ans = 0.0;
    boolean flag = true;
    TreeNode og;
    public double maximumAverageSubtree(TreeNode root) {
        if(flag){
            flag = false;
            og = root;
        }
        if(root == null) return 0.0;
        double cur = root.val;
        root.val = 1;
        if(root.left != null){
            cur += maximumAverageSubtree(root.left);
            root.val += root.left.val;
        }
        if(root.right != null){
            cur += maximumAverageSubtree(root.right);
            root.val += root.right.val;
        }
        if(root.right == null && root.left == null){
            root.val = 1;
        }
        ans = Math.max(ans, cur/root.val);
        return root == og ? ans : cur;
    }
}

// compare every node and distance if distance is smaller set ans to root(set ans while narrowing down distane);
class Solution {
    double dis = Double.MAX_VALUE;
    TreeNode ans = new TreeNode(0);
    public int closestValue(TreeNode root, double target) {
        if(root == null) return 0;
        if(Math.abs(root.val - target) < dis){
            ans = root;
            dis = Math.abs(root.val - target);
        }
        closestValue(root.left, target);
        closestValue(root.right, target);
        return ans.val;
    }
}

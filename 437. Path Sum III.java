class Solution {
    // pathSum = start from cur + start from left + start from right
    public int pathSum(TreeNode root, int sum) {
        if(root == null) return 0;
        int ans = subPath(root, sum);
        return ans + pathSum(root.left, sum) + pathSum(root.right, sum);  
    }
    // check how many path start from cur
    private int subPath(TreeNode root, int sum){
        if(root == null) return 0;
        return (root.val == sum ? 1 : 0) + subPath(root.left, sum - root.val) + subPath(root.right, sum - root.val);
    }
}

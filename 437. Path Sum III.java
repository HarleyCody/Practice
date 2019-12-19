___________________________________________________Best Solution_______________________________________________________________
class Solution {    
     public int pathSum(TreeNode root, int sum) {
        HashMap<Integer, Integer> preSum = new HashMap();
        preSum.put(0,1);
        return helper(root, 0, sum, preSum);
    }
    
    public int helper(TreeNode root, int currSum, int target, HashMap<Integer, Integer> preSum) {
        if (root == null) {
            return 0;
        }
        
        currSum += root.val;
        // curSum - target: path is found, num of preSum that had occur in current path covers node val = 0;
        int res = preSum.getOrDefault(currSum - target, 0);
        preSum.put(currSum, preSum.getOrDefault(currSum, 0) + 1);
        
        res += helper(root.left, currSum, target, preSum) + helper(root.right, currSum, target, preSum);
        // only record sum in current path; left or right path 
        preSum.put(currSum, preSum.get(currSum) - 1);
        return res;
    }
}
___________________________________________________Solution(DFS)_______________________________________________________________
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

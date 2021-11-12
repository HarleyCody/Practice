//Best Solution: in-order traverse Collect number that occurs with maxSum tiems
//Improve: Handle null in the beginning of dfs and use list to add when curFreq >= maxFreq
class Solution {
    int curVal = Integer.MIN_VALUE;
    int curSum = 0;
    int maxSum = 0;
    List<Integer> ansList;
    public int[] findMode(TreeNode root) {
        ansList = new ArrayList();
        dfs(root);
        int[] ans = new int[ansList.size()];
        for(int i = 0; i < ansList.size(); ++i){
            ans[i] = ansList.get(i);
        }
        
        return ans;
    }
    
    private void dfs(TreeNode root){
        if(root == null) return;
        dfs(root.left);
        if(root.val != curVal){
            curVal = root.val;
            curSum = 0;
        }
        ++curSum;
        if(curSum >= maxSum){
            if(curSum > maxSum){
                ansList = new ArrayList();
                maxSum = curSum;
            }
            ansList.add(root.val);
        }
        
        dfs(root.right);
    }
}
//My Solution: in-order traverse Collect number that occurs with maxSum tiems
class Solution {
    int curVal = Integer.MIN_VALUE;
    int curSum = 0;
    int maxSum = 0;
    Set<Integer> ansSet;
    public int[] findMode(TreeNode root) {
        ansSet = new HashSet();
        dfs(root);
        int[] ans = new int[ansSet.size()];
        int i = 0;
        for(int n : ansSet){
            ans[i++] = n;
        }
        
        return ans;
    }
    
    private void dfs(TreeNode root){
        if(root.left != null){
            dfs(root.left);
        }
        if(root.val != curVal){
            curVal = root.val;
            curSum = 0;
        }
        ++curSum;
        if(curSum > maxSum){
            ansSet.clear();
            maxSum = curSum;
        }
        if(curSum == maxSum){
            ansSet.add(root.val);
        }
        
        if(root.right != null){
            dfs(root.right);
        }
    }
}

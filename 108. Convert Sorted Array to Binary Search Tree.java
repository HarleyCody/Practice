____________________________________________________________________My Solution____________________________________________________________
class Solution {
    // binary build recursivly
    public TreeNode sortedArrayToBST(int[] nums) {
        int len = nums.length;
        if(len == 0){
            return null;
        }
        return buildTree(nums, 0, len - 1);
    }
    
    public TreeNode buildTree(int[] nums, int s, int e){
        if(s == e){
            return new TreeNode(nums[s]);
        }
        if(s > e){
            return null;
        }
        int m = (s + e) / 2;
        TreeNode cur = new TreeNode(nums[m]);
        cur.left = buildTree(nums, s, m - 1);
        cur.right = buildTree(nums, m + 1, e);
        return cur;
    }
}

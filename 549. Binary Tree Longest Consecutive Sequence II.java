___________________________________________________Best Solution_______________________________________________________________
public class Solution {
    // helper function will return increase and decrease
    // ans = max(ans, increase + decrease - 1
    int maxval = 0;
    public int longestConsecutive(TreeNode root) {
        longestPath(root);
        return maxval;
    }
    public int[] longestPath(TreeNode root) {
        if (root == null)
            return new int[] {0,0};
        int inr = 1, dcr = 1;
        if (root.left != null) {
            int[] l = longestPath(root.left);
            if (root.val == root.left.val + 1)
                inr = l[0] + 1;
            else if (root.val == root.left.val - 1)
                dcr = l[1] + 1;
        }
        if (root.right != null) {
            int[] r = longestPath(root.right);
            if (root.val == root.right.val + 1)
                inr = Math.max(inr, r[0] + 1);
            else if (root.val == root.right.val - 1)
                dcr = Math.max(dcr, r[1] + 1);
        }
        maxval = Math.max(maxval, dcr + inr - 1);
        return new int[] {inr, dcr};
    }
}
_____________________________________________________My Solution_______________________________________________________________
class Solution {
    // len can be longest left node, right node, or left + right
    // get diff find the max length with diff in right and left
    // only root - root.left == root.right - root, left and right can connect
    int ans = 0;
    public int longestConsecutive(TreeNode root) {
        if(root == null){
            return 0;
        }
        int leftLen = 1, rightLen = 1;
        if(root.left != null){
            if(Math.abs(root.val - root.left.val) == 1){
                leftLen += lenWithDiff(root.left, root.val - root.left.val);
            }
            longestConsecutive(root.left);
        }
        
        if(root.right != null){
            if(Math.abs(root.val - root.right.val) == 1){
                rightLen += lenWithDiff(root.right, root.val - root.right.val);   
            }
            longestConsecutive(root.right);
        }
        int curMax = Math.max(rightLen, leftLen);
        if(root.left != null && root.right != null && 
           root.left.val - root.val == root.val - root.right.val){
            curMax = rightLen + leftLen - 1;
        }
        
        ans = Math.max(curMax, ans);
        return ans;
    }
    
    private int lenWithDiff(TreeNode root, int diff){
        if(root == null){
            return 0;
        }
        int leftLen = 1, rightLen = 1;
        if(root.left != null && root.val - root.left.val == diff){
            leftLen += lenWithDiff(root.left, diff);
        }
        if(root.right != null && root.val - root.right.val == diff){
            rightLen += lenWithDiff(root.right, diff);
        }
        return Math.max(leftLen, rightLen);
    }
}

________________________________________________________________________Best Solution_____________________________________________________________________
// check leftMax and right min make sure Lmax < cur < Rmin and collect ans
class Solution {
    int greatestBSTSum = 0;
    public int maxSumBST(TreeNode root) {
        getBSTSum(root);
        return greatestBSTSum;
    }
    
    private int getBSTSum(TreeNode thisNode) {
        if (thisNode == null) {
            return 0;
        }
        
        int left = getBSTSum(thisNode.left);
        int right = getBSTSum(thisNode.right);
        if (left == Integer.MIN_VALUE || right == Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        if (thisNode.left != null) {
            TreeNode greatestLeft = thisNode.left;
            while (greatestLeft.right != null) {
                greatestLeft = greatestLeft.right;
            }
            if (greatestLeft.val >= thisNode.val) {
                return Integer.MIN_VALUE;
            }
        }
        if (thisNode.right != null) {
            TreeNode smallestRight = thisNode.right;
            while (smallestRight.left != null) {
                smallestRight = smallestRight.left;
            }
            if (smallestRight.val <= thisNode.val) {
                return Integer.MIN_VALUE;
            }
        }
        
        int sum = left + right + thisNode.val;
        if (sum > greatestBSTSum) {
            greatestBSTSum = sum;
        }
        return sum;
    }
}
_________________________________________________________________________My Solution________________________________________________________________________________________
// bottom up, post order 
// record min max in each subtree.
// if in curNode it cannot be form BST, sign set to zero, val,min,max == cur.val(means bst ends here, continue bottom up);
// otherwise, min = l.min max = right.max
// if in current node cur.val <= l.max || cur.val >= r.min, it cannot be valid bst, bst ends at current node
class Solution {
    int ans = 0;
    public int maxSumBST(TreeNode root) {
        if(root == null){
            return ans;
        }
        
        isBST(root);
        
        return ans;
    }
    // int[]{sign, sum, min, max}
    private int[] isBST(TreeNode cur){
        int curSign = 1, curVal = cur.val, curMin = cur.val, curMax = cur.val;
         
        int[] l = new int[4], r = new int[4];
        if(cur.left != null){
            l = isBST(cur.left);
            curSign *= l[0];
            curMin = l[2];
        } 
        if(cur.right != null){
            r = isBST(cur.right);
            curSign *= r[0];
            curMax = r[3];
        }
        
        if(cur.left != null && l[3] >= cur.val || cur.right != null && r[2] <= cur.val){
            curSign = 0;
        }
        
        if(curSign == 1){
            curVal += l[1] + r[1];
            ans = Math.max(ans, curVal);
        }
        return new int[]{curSign, curVal, curMin, curMax};
    }
}

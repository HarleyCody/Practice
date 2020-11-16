________________________________________________________________________________________MySolution_______________________________________________________________________________
class Solution {
    // recursion. left right, BST not BT. BST left < root < right;
    // prunning: search left only when left node is possible in range; srearch right only when right nodes is possible in range
    int ans = 0;
    public int rangeSumBST(TreeNode root, int L, int R) {
        if(root == null) return 0;
        if(L <= root.val && root.val <= R){
            ans += root.val;
        }
        if(root.val >= L){
            rangeSumBST(root.left, L, R);
        }
        if(root.val <= R){
            rangeSumBST(root.right, L, R);
        }
        return ans;
    }
}
________________________________________________________________________________My Solution_____________________________________________________________________________________
// sum(high) - sum(low - 1)
class Solution {
    public int rangeSumBST(TreeNode root, int low, int high) {
        return sum(root, high) - sum(root, low - 1);
    }
    
    private int sum(TreeNode root, int high){
        int ans = 0;
        if(root == null){
            return ans;
        }
        ans = sum(root.left, high);
        if(root.val <= high){
            ans += root.val;
        }else{
            return ans;
        }
        ans += sum(root.right, high);
        
        return ans;
    }
}
________________________________________________________________________________My Solution_____________________________________________________________________________________
class Solution {
    // recursion. left right, BST not BT. BST left < root < right;
    int ans = 0;
    public int rangeSumBST(TreeNode root, int L, int R) {
        if(root == null) return 0;
        if(L <= root.val && root.val <= R){
            ans += root.val;
        }
        rangeSumBST(root.left, L, R);
        rangeSumBST(root.right, L, R);
        return ans;
    }
}

class Solution {
    // recursion
    // skip returnning current node if its out of range
    // return trim left if too larger;
    // return trum right if too small;
    // trim subtrees
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if(root == null){
            return root;
        }
        if(root.val < L){
            return trimBST(root.right, L, R);
        }
        if(root.val > R){
            return trimBST(root.left, L, R);
        }
        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);
        return root;
    }
}

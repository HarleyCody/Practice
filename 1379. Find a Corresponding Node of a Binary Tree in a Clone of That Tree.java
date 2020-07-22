_________________________________________________________________________________My Solution___________________________________________________________________________
class Solution {
    // structure is same, so do traversal with same direction
    TreeNode ans = new TreeNode();
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if(original == null){
            return ans;
        }
        if(original == target){
            ans = cloned;
            return ans;
        }
        
        getTargetCopy(original.left, cloned.left, target);
        getTargetCopy(original.right, cloned.right, target);
        
        return ans;
    }
}

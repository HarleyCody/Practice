//My Solution: detect left and right has node 1 detach the left or right child accordingly
class Solution{
	public TreeNode pruneTree(TreeNode root){
	    if(root == null) return root;
	
	    return hasOne(root) ? root : null;
    }

    private boolean hasOne(TreeNode root){
        if(root == null) return false;
        boolean leftHasOne = hasOne(root.left);
        boolean rightHasOne = hasOne(root.right);
        if(!leftHasOne){
            root.left = null;
        }
        if(!rightHasOne){
            root.right = null;
        }
        return leftHasOne || rightHasOne || root.val == 1;
    }
}

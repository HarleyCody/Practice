________________________________________________________________________________My Solution________________________________________________________________________
class Solution {
    // post order traverse, delete layer by layer, root node is decided by child nodes
    // root can be leave if all children are deleted. so post order
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        deleteNode(root, target, null);
        if(root.val == target && root.left == null && root.right == null){
            return null;
        }
        return root;
    }
    
    private void deleteNode(TreeNode cur, int tar, TreeNode prevNode){
        if(cur.left != null){
            deleteNode(cur.left, tar, cur);
        }
        if(cur.right != null){
            deleteNode(cur.right, tar, cur);
        }
        if(cur.val == tar && cur.left == null && cur.right == null){
            if(prevNode == null){
                cur = null;
            }else if(prevNode.right == cur){
                prevNode.right = null;
            }else{
                prevNode.left = null;
            }
        }
    }
}

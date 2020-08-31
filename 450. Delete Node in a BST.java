________________________________________________________________________________My Solution________________________________________________________________________________
// find target node with prev pointer and delete it by connecting prev and tar;
// change tar according to tar.left and tar.right;
// two connectings, 1. connect left.mostright with right;
// connect prev and tar.next;
class Solution {
    TreeNode tar = null, prev = null;
    public TreeNode deleteNode(TreeNode root, int key) {
        findNode(root, key);
        
        if(tar == null){
            return root;
        }
        
        System.out.println(tar.val);
        TreeNode left = tar.left;
        TreeNode right = tar.right;
        if(left == null){
            tar = tar.right;
        }else if(right == null){
            tar = tar.left;
        }else{
            tar = tar.left;
            left = tar;
            while(left.right != null){
                left = left.right;
            }
            left.right = right;
        }
        if(prev == null){
            return tar;
        }
        if(prev.left != null && prev.left.val == key){
            prev.left = tar;
        }else if(prev.right != null && prev.right.val == key){
            prev.right = tar;
        }
        return root;
    }
    
    private void findNode(TreeNode r, int k){
        tar = r;
        if(r == null || r.val == k){
            return;
        }
        prev = r;
        if(k < r.val){
            findNode(r.left, k);
        }else{
            findNode(r.right, k);
        }
    }
}

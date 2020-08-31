______________________________________________________________________________Best Solution________________________________________________________________________________
// delete node recursively
// delete strategy: find target, set tar to min right or max left, and delete min right or max left from right or left; 
class Solution {
    
    //Get the min value in the right subtree
    public int getRightMin(TreeNode root){
        root = root.right;
        while(root.left != null)
            root = root.left;
        
        return root.val;
    }
    // Get the max value in the left subtree
    public int getLeftMax(TreeNode root){
        root = root.left;
        while(root.right != null)
            root = root.right;
        
        return root.val;
    }
    // To delete the nodes
    public TreeNode deleteNode(TreeNode root, int key) {
        
        // Base condition
        if(root == null)
           return root;
        
        // Iterate in the right of the tree
        if(key > root.val)
            root.right = deleteNode(root.right, key);
        
        // Iterate in the left of the tree
        else if(key < root.val)
            root.left = deleteNode(root.left, key);
        
        // Delete the current node
        else{
            
            // If root is at the leave
            if(root.left == null && root.right == null){
                root = null;
            }else if(root.right != null){
                // Get the least value of the right substree
                // Pass it as the delete key
                // When your program raches it as it is leave so it will delete it
                root.val = getRightMin(root);
                root.right = deleteNode(root.right, root.val);
            }else{
                // Get the max value of the left subtree
                // Pass it as the delete key
                // When your program raches it as it is leave so it will delete it
                root.val = getLeftMax(root);
                root.left = deleteNode(root.left, root.val);
            }
            
        }
        
        return root;
    }
}
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

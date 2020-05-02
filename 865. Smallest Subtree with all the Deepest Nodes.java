___________________________________________________________My (Best) Solution___________________________________________________________
//same as 1123
// maxDepth lable and find
class Solution {
    int maxDepth;
    boolean[] recorder = new boolean[501];
    TreeNode ans;
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        if(root == null){
            return root;
        }
        
        getMaxDepth(root, 0);
        lableNodes(root, 0);
        findNode(root);
        
        return ans;
    }
    
    private void getMaxDepth(TreeNode root, int level){
        if(root == null){
            return;
        }
        
        maxDepth = Math.max(maxDepth, level);
        
        getMaxDepth(root.left, level + 1);
        getMaxDepth(root.right, level + 1);
    }
    
    private boolean lableNodes(TreeNode root, int level){
        if(root == null){
            return false;
        }
        if(level == maxDepth){
            recorder[root.val] = true;
            return true;
        }
        
        recorder[root.val] = lableNodes(root.left, level + 1) | lableNodes(root.right, level + 1);
        
        return recorder[root.val];
    }
    
    private void findNode(TreeNode root){
        ans = root;
        if(root.left != null && root.right != null){
            int leftIdx = root.left.val;
            int rightIdx = root.right.val;
            
            if(recorder[leftIdx] && recorder[rightIdx]){
                return;
            }else if(recorder[leftIdx]){
                findNode(root.left);
            }else if(recorder[rightIdx]){
                findNode(root.right);
            }
        }else if(root.left != null && recorder[root.left.val]){
            findNode(root.left);
        }else if(root.right != null && recorder[root.right.val]){
            findNode(root.right);
        }
    }
}

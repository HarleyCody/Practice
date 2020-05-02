______________________________________________________________Best Solution(Mine)_________________________________________________________
// rules: find max depth, lable the path,  and find node
class Solution {
    boolean[] recorder = new boolean[1001];
    int maxDepth = -1;
    TreeNode ans = null;
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        getMaxDepth(root, 0);
        lableNodes(root, 0);
        findRoot(root);
        
        return ans;
    }
    
    private void getMaxDepth(TreeNode cur, int curLevel){
        if(cur == null){
            return;
        }
        maxDepth = Math.max(curLevel, maxDepth);
        
        getMaxDepth(cur.left, curLevel + 1);
        getMaxDepth(cur.right, curLevel + 1);
    }
    
    private boolean lableNodes(TreeNode cur, int level){
        if(cur == null){
            return false;
        }
        
        if(level == maxDepth){
            recorder[cur.val] = true;
            return true;
        }
        
        int idx = cur.val;
        recorder[idx] = lableNodes(cur.left, level + 1) | lableNodes(cur.right, level + 1);
        
        return recorder[idx];
    }
    
    // keep finding unless the road is invalid, which means it cannot find a Node with max depth from this node.
    private void findRoot(TreeNode cur){
        ans = cur;
        if(cur.left != null && cur.right != null){
            int leftIdx = cur.left.val;
            int rightIdx = cur.right.val;
            //System.out.println("left is " + recorder[leftIdx] + " right is " + recorder[rightIdx] + " at Node " + cur.val);
            if(recorder[leftIdx] && recorder[rightIdx]){
                return;
            }else if(recorder[leftIdx]){
                findRoot(cur.left);
            }else{
                findRoot(cur.right);
            }
        }else if(cur.left != null && recorder[cur.left.val]){
            findRoot(cur.left);
        }else if(cur.right != null && recorder[cur.right.val]){
            findRoot(cur.right);
        }
    }
}

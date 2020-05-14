_____________________________________________________________My(Best) Solution_________________________________________________________________
class Solution {
    // serialize the level of tree
    // Due to complete BST feature, every time enter to next level, number of node in next level == 2 * number of Node in root
    // index of node in next level can be calculated by levelleftIdx == 2 * (rootIdx), rightIdx == leftIdx + 1;
    int ans = 0, left = 0;
    List<Integer> min = new ArrayList();
    public int widthOfBinaryTree(TreeNode root) {
        if(root == null){
            return 0;
        }
        
        dfs(root, 0, -1);
        return ans;
    }
    
    private void dfs(TreeNode root, int level, int val){
        
        if(min.size() <= level){
            min.add(val);
        }else{
            left = Math.min(val, min.get(level));
            min.set(level, left);
        }
        ans = Math.max(ans, val - min.get(level) + 1);
        if(root.left != null){
            int v = val * 2;
            dfs(root.left, level + 1, v);
        }
        if(root.right != null){
            int v = val * 2 + 1;
            dfs(root.right, level + 1, v);
        }
    }
}

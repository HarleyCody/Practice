____________________________________________________________Best Solution(DFS is fastest)________________________________________________
class Solution {
    List<Integer> ans = new ArrayList();
    Queue<TreeNode> recorder = new LinkedList();
    public List<Integer> largestValues(TreeNode root) {
        if(root == null) return ans;
        
        
        dfs(root, 0);
        /* bfs solution
        recorder.add(root);
        bfs(root);
        */
        return ans;
    }
    
    private void dfs(TreeNode root, int level){
        if(root == null){
            return;
        }
        if(level == ans.size()){
            ans.add(root.val);
        }else{
            int val = Math.max(root.val, ans.get(level));
            ans.set(level, val);
        }
        dfs(root.left, level + 1);
        dfs(root.right, level + 1);
    }
    
    private void bfs(TreeNode root){
        while(!recorder.isEmpty()){
            int max = Integer.MIN_VALUE;
            int size = recorder.size();
            for(int i = 0; i < size; ++i){
                TreeNode curNode = recorder.poll();
                max = Math.max(curNode.val, max);
                if(curNode.left != null){
                    recorder.offer(curNode.left);
                }
                if(curNode.right != null){
                    recorder.offer(curNode.right);
                }
            }
            ans.add(max);
        }
    }
}

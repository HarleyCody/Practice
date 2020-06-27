________________________________________________________________________________My Solution______________________________________________________________________________________
class Solution {
    // bfs delete node, until all node is delete return immediately
    List<TreeNode> ans = new ArrayList();
    HashSet<Integer> delete = new HashSet();
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        if(root == null){
            return ans;
        }
        for(int d : to_delete){
            delete.add(d);
        }
        
        bfsDelete(root);
        
        return ans;
    }
    
    private void bfsDelete(TreeNode root){
        Queue<TreeNode> q = new LinkedList();
        q.offer(root);
        
        if(!delete.contains(root.val)){
            ans.add(root);
        }
        
        int dNum = 0;
        while(!q.isEmpty() && dNum != delete.size()){
            
            int size = q.size();
            for(int s = 0; s < size; ++s){
                TreeNode cur = q.poll();
                
                if(cur.left != null){
                    q.offer(cur.left);
                    if(delete.contains(cur.left.val)){
                        cur.left = null;
                    }
                }
                if(cur.right != null){
                    q.offer(cur.right);
                    if(delete.contains(cur.right.val)){
                        cur.right = null;
                    }
                }
                
                if(delete.contains(cur.val)){
                    //delete Node add childs to ans
                    if(cur.left != null){
                        ans.add(cur.left);
                    }
                    if(cur.right != null){
                        ans.add(cur.right);
                    }
                    ++dNum;
                }
            }
        }
    }
}

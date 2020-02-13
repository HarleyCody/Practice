___________________________________________________________________My Solution(Best)_______________________________________________________
//check layer by layer
class Solution {
    public boolean isCompleteTree(TreeNode root) {
        if(root == null) return true;
        Queue<TreeNode> q = new LinkedList();
        q.offer(root);
        boolean isLast = false;
        while(!q.isEmpty()){
            int size = q.size();
            boolean isNull = false;
            for(int i = 0; i < size; ++i){
                TreeNode cur = q.poll();
                if((isLast || isNull) && (cur.left != null || cur.right != null)){
                    return false;
                }
                
                if(cur.left == null && cur.right != null ){
                    return false;
                }
                
                if(cur.left == null || cur.right == null){
                    if(isNull && cur.left != null || cur.right != null){
                        return false;
                    }
                    isNull = true;
                }
                if(cur.left != null)q.offer(cur.left);
                if(cur.right != null)q.offer(cur.right);
            }
            if(size * 2 > q.size()){
                isLast = true;
            }
        }
        return true;
    }
}

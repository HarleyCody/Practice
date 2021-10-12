// My Solution: Morris Time average O(1), Space average O(1);
// Transfer tree to link when it is trying to get next(). 
// Connect left.MaxRight to current, if it is connected, cur.left has been linked, just remove the left part
// if current.left == null, return the current, current move to its right;
public class BSTIterator {
    TreeNode cur;
    public BSTIterator(TreeNode root) {
        cur = root;
    }
    public boolean hasNext() {
        return cur != null;
    }
    public TreeNode next() {
        TreeNode ans = null;
        while(true){
            //It is linked, cur node is ans;
            if(cur.left == null){
                ans = cur;
                cur = cur.right;
                break;
            }
            // Connect right max to current
            TreeNode connect = cur.left;
            while(connect.right != null && connect.right != cur){
                connect = connect.right;
            }
            //Update link and current pointer to smaller value
            if(connect.right == null){
                connect.right = cur;
                cur = cur.left;
            }else{
                cur.left = null;
            }
        }
        return ans;
    }
}
// My Solution: Store node in a list and retrieve node
public class BSTIterator {
    Queue<TreeNode> q = new LinkedList<TreeNode>();
    public BSTIterator(TreeNode root) {
        init(root);
    }
    public boolean hasNext() {
        return !q.isEmpty();
    }
    public TreeNode next() {
        return q.poll();
    }

    private void init(TreeNode root){
        if(root == null) return;
        init(root.left);
        q.offer(root);
        init(root.right);
    }
}

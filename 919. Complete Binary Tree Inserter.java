//My Solution: bfs, use a queue to store the nodes that needs to be inserted from top to bot
//Pop up a node to connect new val, if left is null then connect new node to left and head keeps unchanged, otherwise connect to its right and delete node;
class CBTInserter {
    Queue<TreeNode> q = new LinkedList<TreeNode>();
    TreeNode rt;
    public CBTInserter(TreeNode root) {
        rt = root;
        TreeNode cur = root;
        q.offer(cur);
        while(cur.left != null && cur.right != null){
            q.offer(cur.left);
            q.offer(cur.right);
            q.poll();
            cur = q.peek();
        }
        if(cur.left != null){
            q.offer(cur.left);
        }
    }
    
    public int insert(int val) {
        TreeNode parent = q.peek();
        TreeNode cur = new TreeNode(val);
        if(parent.left == null){
            parent.left = cur;
        }else{
            parent.right = cur;
            q.poll();
        }
        q.offer(cur);
        return parent.val;
    }
    
    public TreeNode get_root() {
        return rt;
    }
}

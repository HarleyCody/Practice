________________________________________________________Best Solution__________________________________________________________
class Solution {
    public boolean isSymmetric(TreeNode root) {
		    if(root == null) return true;
	      return helper(root.left,root.right);
    }
    // compare l.l, r.r && l.r, r.l recursively
    private boolean helper(TreeNode left, TreeNode right) {
        if(left == null && right == null) return true;
        if(left != null && right != null && left.val == right.val)
        // compare l.l with r.r and l.r with r.l
		        return helper(left.left, right.right) && helper(left.right,right.left);
	      else return false;
    }
}
________________________________________________________My Solution____________________________________________________________
class Solution {
    public boolean isSymmetric(TreeNode root) {
        // null 
        if(root == null) return true;
        // only has root node
        if(root.left == null && root.right == null) return true;
        // only one of them is null
        if((root.left != null && root.right == null) 
           || (root.right != null && root.left == null)) return false;
        // sencond layer is not symmetric
        if(root.left.val != root.right.val) return false;
        // record left tree
        Deque<TreeNode> leftRec = new LinkedList();
        // record right tree
        Deque<TreeNode> rigtRec = new LinkedList();
        
        leftRec.offer(root.left);
        rigtRec.offer(root.right);
        while(!leftRec.isEmpty()){
            // l retrive from head
            TreeNode l = leftRec.poll();
            // r retrive from tail
            TreeNode r = rigtRec.pollLast();
            if(l == null) continue;
            if(r.val != l.val) return false;
            // current pair match with each other, l.l r.r
            if(l.left != null && r.right != null || (l.left == null && r.right == null)){
                leftRec.offer(l.left);
                rigtRec.push(r.right);
            }else return false;
            //current pair match with each other, l.r r.l
            if(l.right != null && r.left != null || (r.left == null && l.right == null)){
                leftRec.offer(l.right);
                rigtRec.push(r.left);
            }else return false;
        }
        return true;
    }
}

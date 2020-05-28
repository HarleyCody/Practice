______________________________________________________Best Solution(Recursion)____________________________________________________________
class Solution {
    // iterarive way,
    // move node to leftmost and break connection with prev during moving
    // in leftmost if there is a right node push it to stack, it will be root as next iteration
    // break connection between root and root.right;
    // only add when(root.left == null && root.right == null);
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList();
        if(root == null){
            return ans;
        }
    
        TreeNode cur = root;
        Stack<TreeNode> nodes = new Stack<TreeNode>();
        nodes.push(cur);
        while(!nodes.isEmpty()){
            TreeNode prev = null;
            cur = nodes.peek();
            while(cur.left != null){
                prev = cur;
                cur = cur.left;
                prev.left = null;
                nodes.push(cur); 
            }
            if(cur.right != null){
                nodes.push(cur.right);
                cur.right = null;
            }else{
                ans.add(cur.val);
                cur = nodes.pop();
            }
        }
        return ans;
    }
}
_______________________________________________________My Solution(Recursion)____________________________________________________________
class Solution {
    List<Integer> ans = new ArrayList();
    public List<Integer> postorderTraversal(TreeNode root) {
        postOrder(root);
        
        return ans;
    }
    
    private void postOrder(TreeNode root){
        if(root == null){
            return;
        }
        if(root.left != null){
            postOrder(root.left);
        }
        if(root.right != null){
            postOrder(root.right);
        }
        ans.add(root.val);
    }
}

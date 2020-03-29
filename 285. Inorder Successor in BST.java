___________________________________________Best Solution(iterative Inorder with BST) One Pass_________________________________________________
class Solution {
    // use feature of BST to narrow down range then store the possible one
    // ans will be the first node that larger than target, so when cur > target, cur can be candidate.
    // go left iff cur.val > p.val, ans will be root, so store root as ans here.
    // go right iff cur.val <= p.val, ans will be one of child nodes
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode cur = root;
        TreeNode candidate = null;
        while (cur != null){
            if (cur.val > p.val){
                candidate = cur;
                cur = cur.left;
            }
            else {
                cur = cur.right;
            }
        }
        return candidate;
    }
}
_______________________________________________My Solution(Inorder with BST & prev Node)___________________________________________________
class Solution {
    TreeNode prev = null, ans = null;
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(root.val >= p.val && root.left != null){
            inorderSuccessor(root.left, p);
        }
        
        if(prev == p){
            ans = root;
        }
        prev = root;
        
        if(root.val <= p.val && root.right != null){
            inorderSuccessor(root.right, p);
        }
        return ans;
    }
}

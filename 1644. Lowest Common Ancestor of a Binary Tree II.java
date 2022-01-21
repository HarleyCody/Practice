//Best Solution: try to find p and q. if p is root found then validate q is in p, if q is root found try to find the p in q return p or q if it is found otherwise return null
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode lca = lca(root, p, q);
        if (lca != p && lca != q) {
            return lca;
        } else if (lca == p) {
            return lca(p, q, q) != null ? lca : null;
        } else {
            return lca(q, p, p) != null ? lca : null;
        }
    }
    
    private TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode l = lca(root.left, p, q);
        TreeNode r = lca(root.right, p, q);
        if (l == null || r == null) {
            return l == null ? r : l;
        }
        return root;
    }
}
//My Solution: try to find p and q, return the result from left and right when root is not p or q
class Solution{
	TreeNode ans = null;
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q){
		boolean[] rlt = findNode(root, p, q);
        //System.out.println(rlt[0] + " " + rlt[1]);
		return rlt[0] && rlt[1] ? ans : null;
}

    private boolean[] findNode(TreeNode root, TreeNode p, TreeNode q){
        if(root == null) return new boolean[]{false, false};
        boolean[] leftRlt = findNode(root.left, p, q);
        boolean[] rightRlt = findNode(root.right, p, q);
        //System.out.println(root.val + " " + " " + leftRlt[0] + " " + leftRlt[1] + " " + rightRlt[0] + " " + rightRlt[1]);
        if((leftRlt[0] || rightRlt[0]) && (leftRlt[1] || rightRlt[1]) && ans == null){
            ans = root;
            return new boolean[]{true, true};
        }
        if(root == p){
            if(rightRlt[1] || leftRlt[1]){
                ans = root;
            }
            return new boolean[]{true, rightRlt[1] || leftRlt[1]};
        }else if(root == q){
            if(rightRlt[0] || leftRlt[0]){
                ans = root;
            }
            return new boolean[]{rightRlt[0] || leftRlt[0], true};
        } 
        return new boolean[]{rightRlt[0] || leftRlt[0], rightRlt[1] || leftRlt[1]};
    }
}

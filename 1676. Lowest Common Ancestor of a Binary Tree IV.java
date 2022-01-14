//Best Solution: find first node that is found in left and right
//              find in both left and right, root is ans
//              only found in left return left 
//              only found in right return right
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        if (root == null) {
            return root;
        }
        
        for (TreeNode node: nodes) {
            if (root == node) {
                return node;
            }
        }
        
        TreeNode left = lowestCommonAncestor(root.left, nodes);
        TreeNode right = lowestCommonAncestor(root.right, nodes);
        if (left == null && right == null) {
            return null;
        }
        if (left != null && right != null) {

            return root;
        }
        if (left == null) {
            return right;
        }
        return left;
        
    }
}
//My Solution: record number of candidates found at current root, if number is same as number of candidate update ans when ans is null
class Solution {
    TreeNode ans = null;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        Set<Integer> candidates = new HashSet();
        for(TreeNode node : nodes){
            candidates.add(node.val);
        }
        findNodes(root, candidates);
        return ans;
    }
    
    private int findNodes(TreeNode root, Set<Integer> candidates){
        if(root == null){
            return 0;
        }
        int found = 0;
        if(candidates.contains(root.val)){
            found = 1;
        }
        found += findNodes(root.left, candidates);
        found += findNodes(root.right, candidates);
        if(ans == null && found == candidates.size()){
            ans = root;
        }
        
        return found;
    }
}

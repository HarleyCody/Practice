class Solution {
    // recursively add val and "->" to path, int the leaves(left == null && right == null) only add val;
    // new StringBuilder to pass to next recursion
    List<String> ans;
    public List<String> binaryTreePaths(TreeNode root) {
        ans = new ArrayList();
        if(root == null){
            return ans;
        }
        appendPath(root, new StringBuilder());
        return ans;
    }
    private void appendPath(TreeNode root, StringBuilder prevSb){
        if(root.left == null && root.right == null){
            prevSb.append(root.val);
            ans.add(prevSb.toString());
            return;
        }
        
        prevSb.append(root.val).append("->");
        if(root.left != null){
            appendPath(root.left, new StringBuilder(prevSb));
        }
        if(root.right != null){
            appendPath(root.right, prevSb);
        }
    }
}

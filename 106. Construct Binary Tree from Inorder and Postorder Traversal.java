_____________________________________________________Best Solution_______________________________________________________________
class Solution {
    // use hashmap to record Inorder pos of last postorder element
    private Map<Integer, Integer> indexMap = new HashMap<>();
    
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        return helper(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }
    
    private TreeNode helper(int[] inorder, int is, int ie, int[] postorder, int ps, int pe) {
        if (is > ie) {
            return null;
        }
        if (is == ie) {
            return new TreeNode(inorder[is]);
        }
        TreeNode root = new TreeNode(postorder[pe]);
        int index = indexMap.get(root.val);
        int numLeftNodes = index - is;
        root.left = helper(inorder, is, index - 1, postorder, ps, ps + numLeftNodes - 1);
        root.right = helper(inorder, index + 1, ie, postorder, ps + numLeftNodes, pe - 1);
        return root;
    }
}
___________________________________________________My Solution_______________________________________________________________
// the length of inorder and post order in next level is same so only need to find Inorder postion of root(last element in Post)
// i is length of inorder also is length of postorder
class Solution {
    int[] IN, POST;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        IN = inorder;
        POST = postorder;
        if(inorder.length == 0 || postorder.length == 0 || inorder.length != postorder.length) return null;
        return helper(0, inorder.length - 1, 0, postorder.length - 1);
    }
    private TreeNode helper(int si, int ei, int sp, int ep){
        if(si > ei || sp > ep) return null;
        if(si== ei && sp == ep) return new TreeNode(IN[si]);
        TreeNode root = new TreeNode(POST[ep]);
        int i = si;
        while(i < ei){
            if(IN[i] == root.val) break;
            ++i;
        }
        root.left = helper(si, i - 1, sp, sp + i - si - 1);
        root.right = helper(i + 1, ei, sp + i - si, ep - 1);
        return root;
    }
}

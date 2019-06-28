/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class BSTIterator {
    // store preorder of BST nodes which means it is from small to large.
    List<TreeNode> recorder;
    int next = 0;
    public BSTIterator(TreeNode root) {
        recorder = new ArrayList();
        storeNodes(root);
    }
    
    /** @return the next smallest number */
    public int next() {
        return recorder.get(next++).val;
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        if(recorder.size() > next) return true;
        return false;
    }
    // preorder store
    private void storeNodes(TreeNode cur) {
        if(cur == null) return;
        storeNodes(cur.left);
        recorder.add(cur);
        storeNodes(cur.right);
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */

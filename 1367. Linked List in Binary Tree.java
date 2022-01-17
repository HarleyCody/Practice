//My Solution: Preorder traverse tree and start comparing from every node to see if it is a path
class Solution {
    public boolean isSubPath(ListNode head, TreeNode root) {
        if(root == null) return head == null;
        return isSub(head, root) || isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    private boolean isSub(ListNode head, TreeNode root){
        if(head == null) return true;
        if(root == null) return false;

        return root.val == head.val && (isSub(head.next, root.left) || isSub(head.next, root.right));
    }
}

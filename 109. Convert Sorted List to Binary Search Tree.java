/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    // merge sort
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null) return null;
        // slow fast find mid;
        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = new ListNode(0);
        // prepare to cut root from previous node;
        pre.next = head;
        
        // eg 1,2,3,4 slow = 3, pre = 2, right = 4
        while(fast != null && fast.next != null){
            pre = pre.next;
            slow = slow.next;
            fast = fast.next.next;
        }
        // disconnect root with previous Node;
        pre.next = null;
        // start of right Tree;
        ListNode right = slow.next;
        // disconnect root with right tree;
        slow.next = null;
        // slow(mid point) is root;
        TreeNode root = new TreeNode(slow.val);
        // head has not been added as root;
        if(slow != head)root.left = sortedListToBST(head);
        // right has not been added into tree;
        if(right != null)root.right = sortedListToBST(right);
        return root;
    }
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode partition(ListNode head, int x) {
        if(head == null) return head;
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;
        ListNode findX = head;
        ListNode insert = dummy;
        
        //find first element no less than x
        //prev points to the postion need to be inserted
        while(findX != null && findX.val < x){
            findX = findX.next;
            insert = insert.next;
        }
        if(findX == null) return head;
        
        // find element less than x after first element no less than x
        ListNode less = findX;
        // points to prev node, so after change, less can point back to prev to continuly find less elements.  
        ListNode prev = findX;

        while(less.next != null){
            less = less.next;
            if(less.val < x){
                // less needed to be isolated, then insert it after insert node
                // insert move to next to assure relative order
                // less point = prev to proceed rest less nodes
                prev.next = less.next;
                less.next = insert.next;
                insert.next = less;
                insert = insert.next;
                less = prev;
            }else{
                prev = prev.next;
            }

        }
        return dummy.next;
    }
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        ListNode start = dummy,first = dummy;
        dummy.next = head;
        // steps from start to end
        int time = 0, total = n, s2e = n - m;
        // Position before start;
        while(--m > 0 && start != null){
            start = start.next;
        }
        first = start.next;
        //reverse start from null
        ListNode pre = null;
        // first element in reverse List.
        ListNode cur = start.next;
        ListNode tem = null;
        // reverse ListNode
        while(s2e-- >= 0 && cur != null){
            tem = cur.next;
            cur.next = pre;
            //change pre to its next in original list(cur)
            pre = cur;
            // change cur to its next in original list
            cur = tem;
        }
        // connect start to head of reverse node(pre)
        start.next = pre;
        // connect end of reverse node to rest(tem)
        first.next = tem;
        return dummy.next;
    }
}

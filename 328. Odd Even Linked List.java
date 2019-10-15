/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    // 1 ->2 ->3 ->4 ->5 odd even pointer, connect odd, and connect even, connect tail of odd and head of even
    // odd 1 ->3 ->4 ->5; even = 2->3->4->5; cur = 2,
    // odd 1 ->3 ->4 ->5; even = 2->4->5; cur = 3;
    // odd 1 ->3 ->5;     even = 2->4->5; cur = 4;
    // odd 1 ->3 ->5;     even = 2->4; cur = 5;
    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode odd = head, cur = head;
        ListNode even = head.next;
        // record start for connecting odd and even
        ListNode eStart = even;
        boolean isOdd = true;
        while(cur != null && cur.next != null){
            // change cur to next
            // otherwise head will be changed, cur will goes to next odd or even(wrong order)
            cur = cur.next;
            if(isOdd){
                odd.next = cur.next;
                if(odd.next != null){
                    odd = odd.next;
                }
            }else{
                even.next = cur.next;
                even = even.next;
            }
            isOdd = !isOdd;
        }
        odd.next = eStart;
        return head;
    }
}

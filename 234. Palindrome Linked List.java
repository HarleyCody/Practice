________________________________________________________Best Solution__________________________________________________________
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        //record Head of first half of ListNode in reverse order
        ListNode reverseHead = null;
        // find mid node while reverse first half nodes.
        while(fast != null && fast.next != null){
        // move fast node first, so it will not be affected by reverse process
            fast = fast.next.next;
            ListNode slowNext = slow.next;
            slow.next = reverseHead;
            reverseHead = slow;
            slow = slowNext;
        }
        // length is odd.
        if(fast != null){
            slow = slow.next;
        }
        //compare slow to reverseHead;
        while(slow != null){
            if(reverseHead.val != slow.val) return false;
            slow = slow.next;
            reverseHead = reverseHead.next;
        }
        return true;
    }
}
________________________________________________________My Solution____________________________________________________________
class Solution {
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) return true;
        // use stack to store reverse order of first half of ListNode
        Stack<Integer> recorder = new Stack();
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null){
            recorder.push(slow.val);
            slow = slow.next;
            fast = fast.next.next;
        }
        // length is odd.
        if(fast != null){
            slow = slow.next;
        }
        //compare value to value
        while(slow != null){
            if(recorder.pop() != slow.val) return false;
            slow = slow.next;
        }
        return true;
    }
}

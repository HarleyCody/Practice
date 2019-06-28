/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public void reorderList(ListNode head) {
        if(head == null || head.next == null) return;
        int count = 0;
        ListNode reverseHead = head;
        // caculate length of ListNode;
        while(reverseHead != null){
            reverseHead = reverseHead.next;
            ++count;
        }
        reverseHead = head;
        int reverseStart = count / 2;
        // move reverseHead to its start
        while(reverseStart-- > 0){
            reverseHead = reverseHead.next;
        }
        // prepare to reverse
        // move to pre every iteration
        ListNode dummy = null;
        // connect dummy
        ListNode pre = reverseHead;
        // find latter node
        reverseHead = reverseHead.next;
        // connect pre
        ListNode lat = reverseHead;
        // start reverse;
        while(reverseHead != null){
            lat = reverseHead;
            // connect with previous node
            pre.next = dummy;
            // move reverseHead out of effected zone, next of latter node, prepare for reverse in next round
            reverseHead = lat.next;
            lat.next = pre;
            //update node that pre should connect in next round;
            dummy = pre;
            // lat node in this round is pre node in next round;
            pre = lat;
        }
        // till now head is split to head and reverseHead, length of reverseHead <= length of Head eg; og 12345(1234) head:123(123) reverseHead:543(43) 
        // insert lat with head;
        pre = head;
        // donot inser last Node of lat as they are, so when lat.next is null stop.
        while(lat != null && lat.next != null){
            ListNode temPre = pre.next;
            ListNode temLat = lat.next;
            pre.next = lat;
            lat.next = temPre;
            pre = temPre;
            lat = temLat;
        }
    }
}

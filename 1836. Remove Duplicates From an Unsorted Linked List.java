//My Solution: recorder the freq of node and delete the node that has freq more than 1;
//              tHead traverse list, prev only record node with 1 frequence
class Solution {
    int[] num;
    public ListNode deleteDuplicatesUnsorted(ListNode head) {
        num = new int[100001];
        ListNode tHead = head;
        while(tHead != null){
            ++num[tHead.val];
            tHead = tHead.next;
        }
        
        while(head != null && num[head.val] > 1){
            head = head.next;
        }
        if(head == null) return null;
        
        tHead = head.next;
        ListNode prev = head;
        while(tHead != null){
            if(num[tHead.val] == 1){
                prev.next = tHead;
                prev = prev.next;
            }
            tHead = tHead.next;
        }
        prev.next = null;
        return head;
    }
}

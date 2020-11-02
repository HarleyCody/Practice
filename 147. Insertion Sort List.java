________________________________________________________________________________________Best Solution________________________________________________________________________
// persist the pointer in last soert, only search from start if last is larger than current, otherwise continue search
class Solution {
    public ListNode insertionSortList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        while(head != null){
            ListNode temp = head.next;
            if(prev.val >= head.val){
                prev = dummy;
            }
            while(prev.next != null && prev.next.val < head.val){
                prev = prev.next;
            }
            head.next = prev.next;
            prev.next = head;
            head = temp;
        }
        return dummy.next;
    }
}__________________________________________________________________________________________My Solution________________________________________________________________________
//find for every element, O(n2);
class Solution {
    public ListNode insertionSortList(ListNode head) {
        if(head == null){
            return head;
        }
        ListNode ans = null;
        while(head != null){
            ans = insert(head.val, ans);
            head = head.next;
        }
        return ans;
    }
    private ListNode insert(int val, ListNode ans){
        ListNode nNode = new ListNode(val);
        if(ans == null){
            ans = nNode;
            return ans;
        }
        ListNode tra = ans;
        ListNode prev = null;
        while(tra != null && tra.val < val){
            prev = tra;
            tra = tra.next;
        }
        if(prev == null){
            nNode.next = ans;
            ans = nNode;
        }else if(tra == null){
            prev.next = nNode;
        }else{
            prev.next = nNode;
            nNode.next = tra;
        }
        return ans;
    }
}

//My Solution: Iterate by stack and add from last
class Solution {
    int carry = 1;
    int layer = 0;
    public ListNode plusOne(ListNode head) {
        if(head == null) return head;
        Stack<ListNode> sta = new Stack();
        ListNode traverse = head;
        while(traverse != null){
            sta.push(traverse);
            traverse = traverse.next;
        }
        int carry = 1;
        while(carry != 0 && !sta.isEmpty()){
            ListNode cur = sta.pop();
            cur.val += carry;
            carry = cur.val / 10;
            cur.val %= 10;
        }
        if(carry != 0){
            ListNode ans = new ListNode(1, head);
            return ans;
        }
        
        return head;
    }
}

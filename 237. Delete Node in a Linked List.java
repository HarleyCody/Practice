//My Solution: Iterate through the list and copy next value to current one by one
class Solution {
    public void deleteNode(ListNode node) {
        ListNode prev = null;
        while(node.next != null){
            node.val = node.next.val;
            prev = node;
            node = node.next;
        }
        prev.next = null;
    }
}

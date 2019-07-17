/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val,Node _next) {
        val = _val;
        next = _next;
    }
};
*/
class Solution {
    public Node insert(Node head, int insertVal) {
        if(head == null) {
            Node first = new Node(insertVal, null);
            first.next = first;
            return first;
        }
        Node slow = head, fast = head, start = head, end = head;
        int meet = 0;
        
        // find smallest value as start, previous node is end
        // meanwhile if there is any node that node.val <= insert <= node.next.val, place has been found, insert value and return;
        while(meet == 0){
            // place found;
            if( insertVal >= slow.val && insertVal <= slow.next.val){
                Node insert = new Node(insertVal, slow.next);
                slow.next = insert;
                return head;
            }
            // found start as later node(start) < current node(end)
            if(slow.next.val <= slow.val){
                end = slow;
                start = slow.next;
            }
            // continue to next node;
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                meet = 1;
            }
        }
        // did not insert in while loop cause current value is largest or smallest one, insert it between end and start.
        Node insert = new Node(insertVal, start);
        end.next = insert;
        return head;
    }
}

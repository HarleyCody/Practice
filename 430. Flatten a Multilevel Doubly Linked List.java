/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node() {}

    public Node(int _val,Node _prev,Node _next,Node _child) {
        val = _val;
        prev = _prev;
        next = _next;
        child = _child;
    }
};
*/
class Solution {
    public Node flatten(Node head) {
        if(head == null) return head;
        Node med = head;
        // one single list may have multiple child, check every node make sure every child has been added to main list.
        while(med != null){
            
            while(med != null && med.child == null){
                med = med.next;
            }
            // didnot have child list
            if(med == null) return head;
            // find a child list
            // disconnect med and med next, med next should be tail;
            // med.next = head of flattened child
            Node next = med.next;
            
            med.next = flatten(med.child);
            med.next.prev = med;
            // free child.
            med.child = null;
            // point to last node to add tail(next);
            while(med.next != null){
                med = med.next;
            }
            // has tail
            if(next != null){
                med.next = next;
                next.prev = med;
            }
        }
        
        return head;
    }
}

_______________________________________________________Best Solution___________________________________________________________
class Solution {
    public Node copyRandomList(Node head) {
        if(head == null) return null;
        copyList(head);
        Node traverse = head;
        // copy random
        while(traverse != null){
            if(traverse.random != null){
                traverse.next.random = traverse.random.next;
            }
            traverse = traverse.next.next;
        }
        // seperate copy and original
        // original linkedlist need to be recovered
        Node ans = head.next;
        Node split = ans;
        while(head.next.next != null){
            head.next = head.next.next;
            split.next = split.next.next;
            head = head.next;
            split = split.next;
        }
        head.next = null;
        return ans;
    }
    private void copyList(Node head){
        while(head != null){
            Node insert = new Node();
            insert.val = head.val;
            insert.next = head.next;
            head.next = insert;
            head = head.next.next;
        }
    }
}
_______________________________________________________My Solution____________________________________________________________
/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
};
*/
class Solution {
    public Node copyRandomList(Node head) {
        if(head == null) return null;
        Node ans = new Node();
        Node copy = ans;
        HashMap<Node, Node> copyMap = new HashMap();
        Node og = head;
        // copy next
        while(og != null){
            copy.val = og.val;
            if(og.next != null) copy.next = new Node();
            if(og.random != null) copy.random = new Node();
            copyMap.put(og, copy);
            og = og.next;
            copy = copy.next;
        }
        // copy random
        og = head;
        copy = ans;
        while(og != null){
            if(og.random != null){
                copy.random = copyMap.get(og.random);
            }
            copy = copy.next;
            og = og.next;
        }
        return ans;
    }
}

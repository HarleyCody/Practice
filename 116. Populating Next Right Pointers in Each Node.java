/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val,Node _left,Node _right,Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/
class Solution {
    // BFS
    public Node connect(Node root) {
        if(root == null) return root;
        Queue<Node> recorder = new LinkedList();
        Queue<Node> nLevel = new LinkedList();
        recorder.offer(root);
        while(!recorder.isEmpty()){
            Node cur = recorder.poll();
            Node next = recorder.peek();
            cur.next = next == null ? null : next;
            
            if(cur.left != null){
                nLevel.add(cur.left);
            }
            if(cur.right != null){
                nLevel.add(cur.right);
            }
            if(recorder.isEmpty()){
                recorder = new LinkedList(nLevel);
                nLevel.clear();
            }
        }
        return root;
    }
}
____________________________________________________________Best Solution(Recursion)_____________________________________________________
class Solution {
// use next pointer
    public Node connect(Node root) {
        if(root == null) return root;
        if(root.left != null) root.left.next = root.right;
        if(root.right != null && root.next != null) root.right.next = root.next.left;
        connect(root.left);
        connect(root.right);
        return root;
    }
}

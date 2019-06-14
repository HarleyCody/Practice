/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/
class Solution {
    Node prev = null;
    public Node treeToDoublyList(Node root) {
	    if (root == null) return null;
	    Node dummy = new Node(0, null, null);
        // clone the beginning of list, use it for connecting head and tail.
	    prev = dummy;
	    helper(root);
	    //connect head and tail
	    prev.right = dummy.right;
	    dummy.right.left = prev;
	    return dummy.right;
    }
    // mid-order traval, result will be sorted.
    private void helper (Node cur) {
	    if (cur == null) return;
        // get to leftmost Node
	    helper(cur.left);
        // prev is leftmost node, connect it with cur.
	    prev.right = cur;
	    cur.left = prev;
	    prev = cur;
	    helper(cur.right);
    }
}

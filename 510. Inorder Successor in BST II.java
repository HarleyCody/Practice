/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
};
*/

class Solution {
// if has right node, return right node
// find the parent whose left contains current node and return parent
    public Node inorderSuccessor(Node node) {
        if(node == null){
            return null;
        }
        
        if(node.right != null){
            node = node.right;
            while(node != null && node.left != null){
                node = node.left;
            }
            return node;
        }
        Node parent = node.parent;
        while(parent != null){
            if(parent.left == node){
                return parent;
            }
            node = parent;
            parent = node.parent;
        }
        return null;
    }
}

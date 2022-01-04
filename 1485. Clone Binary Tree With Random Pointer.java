//My Solution: Copy Tree and copy Random
class Solution {
    HashMap<Node, NodeCopy> copyMap = new HashMap();
    public NodeCopy copyRandomBinaryTree(Node root) {
        if(root == null) return null;
        copyTree(root);
        return copyRandom(root);
    }
    
    private NodeCopy copyTree(Node root){
        if(root == null) return null;
        NodeCopy nc = new NodeCopy(root.val);
        nc.left = copyTree(root.left);
        nc.right = copyTree(root.right);
        copyMap.put(root, nc);
        
        return nc;
    }
    
    private NodeCopy copyRandom(Node root){
        if(root == null) return null;
        NodeCopy nc = copyMap.get(root);
        nc.random = copyMap.get(root.random);
        copyRandom(root.left);
        copyRandom(root.right);
        return nc;
    }
}

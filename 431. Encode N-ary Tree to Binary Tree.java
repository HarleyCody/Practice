_______________________________________________________________My Solution____________________________________________________________
// encode: put the All children to left or right, and the children of the child to the other way(left, right,left...)
// decode: the first is root, the others to its left(or right) is the node in children;
// idea: turn chidren to a linkedlist and make it as a branch to the root, the other branch will be the nodes of chid's children
class Codec {
    // Encodes an n-ary tree to a binary tree.
    public TreeNode encode(Node root) {
        if(root == null){
            return null;
        }
        return encode(root, true);
    }
    
    private TreeNode encode(Node root, boolean isRight){
        TreeNode cur = new TreeNode(root.val);
        
        TreeNode next = cur;
        for(Node n : root.children){
            if(isRight){
                next.left = encode(n, !isRight);
                next = next.left;
            }else{
                next.right = encode(n, !isRight);
                next = next.right;
            }
        }
        return cur;
    }
	
    // Decodes your binary tree to an n-ary tree.
    public Node decode(TreeNode root) {
        if(root == null){
            return null;
        }
        return decode(root, false);
    }
    
    private Node decode(TreeNode root, boolean isRight){
        Node cur = new Node(root.val);
        cur.children = new ArrayList();
        TreeNode next = isRight ? root.right : root.left;
        
        while(next != null){
            cur.children.add(decode(next, !isRight));
            next = isRight ? next.right : next.left;
        }
        
        return cur;
    }
}

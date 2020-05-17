____________________________________________________________________My Solution__________________________________________________________
class Solution {
    // connect from right to left, if first start from left, the right subtree is not connected, so breaks;
    // get next from right first;
    // connect right.next = next;
    // left.next = root.right == null ? root.right : next; 
    public Node connect(Node root) {
        if(root == null){
            return null;
        }
        Node next = null;
        Node rootNext = root.next;
        
        while(next == null && rootNext != null){
            if(rootNext.left != null){
                next = rootNext.left;
            }else if(rootNext.right != null){
                next = rootNext.right;
            }
            rootNext = rootNext.next;
        }
        
        if(root.right != null){
            root.right.next = next;
        }
        
        if(root.left != null){
            if(root.right == null){
                root.left.next = next;
            }else{
                root.left.next = root.right;
            }
        }
        connect(root.right);
        connect(root.left);
        return root;
    }
}

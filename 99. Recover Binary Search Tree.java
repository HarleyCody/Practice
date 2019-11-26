______________________________________________________Best Solution___________________________________________________________
// when pre > cur, first is pre;
// when pre > cur, second is cur;
// Inorder traverse
class Solution {
    TreeNode swap1, swap2, pre;
    public void recoverTree(TreeNode root) {
        if(root == null) return;
        inOrder(root);
        int temp = swap1.val;
        swap1.val = swap2.val;
        swap2.val = temp;
    }
    private void inOrder(TreeNode root){
        if(root == null) return;
        inOrder(root.left);
        if(pre != null && pre.val > root.val){
            if(swap1 == null){
                swap1 = pre;
                swap2 = root;
            }else{
                swap2 = root;
                return;
            }
        }
        pre = root;
        inOrder(root.right);
    }
}
______________________________________________________My Solution_____________________________________________________________
// inorder the tree, a valid bst should create a ascending sequence
// compare sorted recorder with og recorder, find the different element with same index, different pairs are targets being swapped
class Solution {
    List<TreeNode> recorder = new ArrayList();
    public void recoverTree(TreeNode root) {
        if(root == null) return;
        inOrderTraverse(root);
        TreeNode swap1 = null, swap2 = null;
        List<TreeNode> og = new LinkedList(recorder);
        Collections.sort(recorder, (x,y) -> {if(x.val == Integer.MIN_VALUE) return -1;
                                             else if (y.val == Integer.MIN_VALUE) return 1;
                                            return x.val - y.val;});
        for(int i = 0; i < recorder.size(); i++){
            swap1 = recorder.get(i);
            swap2 = og.get(i);
            if(swap1.val != swap2.val){
                int temp = swap1.val;
                swap1.val = swap2.val;
                swap2.val = temp;
                return;
            }
        }
    }
    private void inOrderTraverse(TreeNode root){
        if(root == null) return;
        if(root.left != null){
            inOrderTraverse(root.left);
        }
        recorder.add(root);
        if(root.right != null){
            inOrderTraverse(root.right);
        }
    }
}

____________________________________________________Better Solution____________________________________________________________
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int countNodes(TreeNode root) {
        // TreeNode is already a complete TreeNode, so 12345null6  is not a case in this question.
        // get the layer of left and right node, if they are same then formula  n = log2(N + 1) ->
        // N = Math.pow(n,2) - 1
        // if they are not same, add complete tree of left and right + current level(1);
        int left=getLeft(root);
        int right=getRight(root);
        if(left==right){
            return (1<<left)-1;//N = Math.pow(n,2) - 1
        }else{
            return 1+countNodes(root.left)+countNodes(root.right);
        }
        
    }
    private int getLeft(TreeNode root){
        int height=0;
        while(root!=null){
            root=root.left;
            height++;
        }
        return height;
    }
    private int getRight(TreeNode root){
        int height=0;
        while(root!=null){
            root=root.right;
            height++;
        }
        return height;
    }    
}
_________________________________________________________My Solution___________________________________________________________
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    // traverse through TreeNode layer by layer, store every node that is not null in current layer;
    public int countNodes(TreeNode root) {
        if(root == null) return 0;
        //store every node that is not null in current layer
        List<TreeNode> recorder = new ArrayList();
        recorder.add(root);
        int ans = 1, index = 0;
        // return ans when meet first null;
        while(!recorder.isEmpty()){
            
            TreeNode cur = recorder.get(index++);
            
            if(cur.left != null){
                recorder.add(cur.left);
                ++ans;
            }else return ans;
            
            if(cur.right != null){
                recorder.add(cur.right);
                ++ans;
            }else return ans;
            
        }
        return ans;
    }
}

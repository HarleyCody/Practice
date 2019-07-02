/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
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
    //record <inOrder[i], i> index of value in inOrder Array;
    HashMap<Integer,Integer> recorder = new HashMap();
    public TreeNode buildTree(int [] preorder, int[] inorder){
        for(int i = 0; i < inorder.length; ++i){
            recorder.put(inorder[i], i);
        }
        TreeNode root = recur(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
        return root;
    }
    private TreeNode recur(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd){
        // do not has more child Node
        if(preStart > preEnd || inStart > inEnd){
            return null;
        }
        // 
        TreeNode root = new TreeNode(pre[preStart]);
        int inRoot = recorder.get(root.val);
        // for root in the right node, 
        //eg inorder 3 2 1 4 5 preorder is 1 2 3 4 5, 1 is root then 2 3 belongs to left, start of right is index of 1 + size of its left Tree( ie 2, 3).
        // it is because no matter in inorder or preorder, left has priority to right. inorder: left, root, right. preorder: root, left, right
        int leftHalf = inRoot - inStart; 

        
        root.left = recur(pre, preStart + 1, preStart + leftHalf, in, inStart, inRoot - 1);
        root.right = recur(pre, preStart + leftHalf + 1, preEnd, in, inRoot + 1, inEnd);
        
        return root;
    }
}

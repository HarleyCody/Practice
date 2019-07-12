___________________________________________________________Best Solution______________________________________________________
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
    int ans = 0;
    int count;
    public int kthSmallest(TreeNode root, int k) {
        count = k;
        kth(root);
        return ans;
    }
    // inorder traverse to get acending element order, only need stop when count is 0;
    private void kth(TreeNode root){
        if(root.left != null){
            kth(root.left);
        }
        count--;
        if(count == 0){
            ans = root.val;
            return;
        }
        if(root.right != null){
            kth(root.right);
        }
    }
}
___________________________________________________________My Solution______________________________________________________
class Solution {
// use priorityqueue to record K element, and poll till the last one
    PriorityQueue<Integer> recorder = new PriorityQueue();
    public int kthSmallest(TreeNode root, int k) {
        kth(root, k);
        while(k-- > 1){
            recorder.poll();
        }
        return recorder.poll();
    }
    private void kth(TreeNode root, int k){
        if(root == null)return;
        kth(root.left, k);
        recorder.offer(root.val);
        if(recorder.size() == k) return;
        kth(root.right, k);
    }
}

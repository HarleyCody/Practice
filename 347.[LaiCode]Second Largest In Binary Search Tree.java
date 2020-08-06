______________________________________________________________________________My Solution__________________________________________________________________________
// preorder traversal
// record prev, update ans node only if prev is not null, update prev to cur after update ans node
public class Solution {
    TreeNode ans = null, prev = null;
    public int secondLargest(TreeNode root) {
        // Write your solution here
        find(root);
        return ans == null ? Integer.MIN_VALUE : ans.key;
    }

    private void find(TreeNode cur){ 
        if(cur == null){
          return;
        }
        find(cur.left);
        if(prev != null){
          ans = prev;
        }
        prev = cur;
        find(cur.right);
    }
}

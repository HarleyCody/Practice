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
    List<Integer> ans = new ArrayList();
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        // add valid node below target;
        sub(target, K);
        // add valid node above target
        if(root != target) above(root, K, target);
        return ans;
    }
    // add sub valid nodes
    private void sub(TreeNode cur, int K){
        if(cur == null)return;
        if(0 == K) ans.add(cur.val);
        if(0 < K){
            sub(cur.left, K - 1);
            sub(cur.right, K - 1);
        }
    }
    // add above node
    private int above(TreeNode cur, int k, TreeNode tar){
        if(cur == null) return Integer.MIN_VALUE;
        // at target level, dis from tar 2 tar is 0;
        if(cur == tar) return 0;
        // distance from cur(above target) to target
        int dis = 0;
        
        if(search(cur.left, tar)){// target is in left tree;
            dis = 1 + above(cur.left, k, tar);
            // search from right tree with k - dis - 1(-1 cuz search from cur.right not cur)
            if(k - dis > 0)sub(cur.right, k - dis - 1);
        }
        // (k - dis > 0) the other tree is worth to search
        else{// target is in right tree
            // current distance == subNode distance + 1;
            dis = 1 + above(cur.right, k, tar);
            // search from right tree with k - dis - 1(-1 cuz search from cur.right not cur)
            if(k - dis > 0)sub(cur.left, k - dis - 1);
        }
        // current node is an answer( k - dis )
        if(dis == k) ans.add(cur.val);
        return dis;
    }
    // check target is in left tree or right tree
    private boolean search(TreeNode cur, TreeNode target){
        if(cur == null) return false;
        if(cur == target) return true;
        return search(cur.left, target) || search(cur.right,target);
    }
}

//My Solution: record max of path, compare root.val with max. Backtrach the prevMax at current node so right track is not affected by max in left track
class Solution {
    int prevMax = Integer.MIN_VALUE;
    public int goodNodes(TreeNode root) {
        if(root == null) return 0;
        int ans = 0;
        if(root.val >= prevMax){
            ans = 1;
        }
        int tmp = prevMax;
        prevMax = Math.max(prevMax, root.val);
        ans += goodNodes(root.left);
        ans += goodNodes(root.right);
        prevMax = tmp;
        return ans;
    }
}

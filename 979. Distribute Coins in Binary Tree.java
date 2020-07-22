__________________________________________________________________My Solution_____________________________________________________________________________
// Improve: do not need top down
// assign every node with one coind(-1)
// it will be accumulated throught the path
// -1 also record the distance to reach to all the node here
// think all node walk to wards root to trade, thats the path need to go
class Solution {
    int res = 0;
    public int distributeCoins(TreeNode root) {
        dfs(root);
        return res;
    }

    public int dfs(TreeNode root) {
        if (root == null) return 0;
        int left = dfs(root.left), right = dfs(root.right);
        res += Math.abs(left) + Math.abs(right);
        return root.val + left + right - 1;
    }
}
__________________________________________________________________My Solution_____________________________________________________________________________
class Solution {
    // bottom up and top down
    // collect to root and distributed to bottom;
    int ans;
    public int distributeCoins(TreeNode root) {
        collectCoins(root);
        return ans;
    }
    
    private int collectCoins(TreeNode cur){
        if(cur == null){
            return 0;
        }
        int l = collectCoins(cur.left);
        if(l != 0){
            ans += l;
        }
        int r = collectCoins(cur.right);
        if(r != 0){
            ans += r;
        }
        
        int all = l + r + cur.val;
        if(all > 0){
            cur.val = 1;
            --all;
        }
        if(all == 0){
            return 0;
        }
        if(l == 0){
            all = distribute(cur.left, all, 1);
        }
        if(r == 0){
            all = distribute(cur.right, all, 1);
        }

        return all;
    }
    
    private int distribute(TreeNode node, int coins, int steps){
        if(node == null || coins == 0){
            return coins;
        }
        if(node.val == 0){
            node.val = 1;
            ans += steps;
            --coins;
        }
        coins = distribute(node.left, coins, steps + 1);
        coins = distribute(node.right, coins, steps + 1);
        return coins;
    }
}

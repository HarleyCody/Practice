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
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
    
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        //Queue save the order of node from left to right and from top to bottom
        Queue<TreeNode> q = new LinkedList<>();
        //Queue record col of nodes in queue
        Queue<Integer> cols = new LinkedList<>();

        q.add(root);// root is always in first col
        cols.add(0);// first col

        int min = 0;
        int max = 0;
    
        while (!q.isEmpty()) {
            TreeNode node = q.poll();// get one from queue FIFO
            int col = cols.poll();
        
            if (!map.containsKey(col)) {
                map.put(col, new ArrayList<Integer>());
            }
            map.get(col).add(node.val);// add node to List(col);

            if (node.left != null) {// put left into queue
                q.add(node.left); 
                cols.add(col - 1);
                min = Math.min(min, col - 1);
            }
        
            if (node.right != null) {// put right into queue
                q.add(node.right);
                cols.add(col + 1);
                max = Math.max(max, col + 1);
            }
        }
        
        for (int i = min; i <= max; i++) {// combine list in each col
            res.add(map.get(i));
        }
        return res;
    }
}

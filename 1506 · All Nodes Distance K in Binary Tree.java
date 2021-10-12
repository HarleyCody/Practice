//Best Solution: BFS, dfs + hashmap to get parent, start from target to extend, set null for increase dist
class Solution {
    Map<TreeNode, TreeNode> parent;
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        parent = new HashMap();
        dfs(root, null);

        Queue<TreeNode> queue = new LinkedList();
        queue.add(null);
        queue.add(target);

        Set<TreeNode> seen = new HashSet();
        seen.add(target);
        seen.add(null);

        int dist = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                if (dist == K) {
                    List<Integer> ans = new ArrayList();
                    for (TreeNode n: queue)
                        ans.add(n.val);
                    return ans;
                }
                queue.offer(null);
                dist++;
            } else {
                if (!seen.contains(node.left)) {
                    seen.add(node.left);
                    queue.offer(node.left);
                }
                if (!seen.contains(node.right)) {
                    seen.add(node.right);
                    queue.offer(node.right);
                }
                TreeNode par = parent.get(node);
                if (!seen.contains(par)) {
                    seen.add(par);
                    queue.offer(par);
                }
            }
        }

        return new ArrayList<Integer>();
    }

    public void dfs(TreeNode node, TreeNode par) {
        if (node != null) {
            parent.put(node, par);
            dfs(node.left, node);
            dfs(node.right, node);
        }
    }
}
// My Solution: Find below and use dfs to get the distance between curRoot to target when target is under the root.
public class Solution {
    List<Integer> ans;
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        ans = new ArrayList();
        if(root == null) return ans;
        dfs(root, target, K);
        return ans;
    }
    private int dfs(TreeNode node, TreeNode target, int k){
        if(node == null) return 0x3f3f3f3f;
        if(node == target){
            findBelow(node, k);
            return 0;
        }
        int left = dfs(node.left, target, k);
        int right = dfs(node.right, target, k);
        if(left == right && right == 0x3f3f3f3f) return left;
        if(left < right){
            if(left + 1 == k) ans.add(node.val);
            findBelow(node.right, k - left - 2);
            return left + 1;
        }else{
            if(right + 1 == k) ans.add(node.val);
            findBelow(node.left, k - right - 2);
            return right + 1;
        }
    }
    private void findBelow(TreeNode root, int k){
        if(root == null) return;
        if(k == 0){
            ans.add(root.val);
            return;
        }
        findBelow(root.left, k - 1);
        findBelow(root.right, k - 1);
    }
}

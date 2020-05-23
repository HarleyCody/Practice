____________________________________________________________Best Solution_______________________________________________________________
// also break at left tree,
class Solution {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        LinkedList<Integer> result = new LinkedList<Integer>();
        helper(result, root, target, k);
        return result;
    }
    
    public boolean helper(LinkedList<Integer> result, TreeNode root, double target, int k) {
        if(root == null) return false;
        if(helper(result, root.left, target, k)) return true;
        if(result.size() < k) {
            result.add(root.val);
        } else if(Math.abs(target - result.getFirst()) > Math.abs(target - root.val)) {
            result.removeFirst();
            result.add(root.val);
        }  else {
            return true;
        }
        return helper(result, root.right, target, k);
    }
}
_____________________________________________________My Improved Solution_______________________________________________________________
// Improve as preorder traverse makes all nodes in increase order, so stop when cur head is invalid(farther than peek node in q);
// donot need pq as inorder traverse bst gurantee increasing order
class Solution {
    //max heap, traverse all nodes 
    LinkedList<TreeNode> pq = new LinkedList();
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        find(root, k, target);
        List<Integer> ans = new ArrayList();
        for(TreeNode n : pq){
            ans.add(n.val);
        }
        return ans;
    }
    private void find(TreeNode root, int k, double target){
        if(root.left != null){
            find(root.left, k, target);
        }
        if(pq.size() < k){
            pq.add(root);
        }else if(Math.abs(pq.peek().val - target) > Math.abs(root.val - target)){
            pq.removeFirst();
            pq.add(root);
        }else{
            return;
        }
        if(root.right != null){
            find(root.right, k, target);
        }
    }
}
____________________________________________________________My Solution_________________________________________________________________
class Solution {
    //max heap, traverse all nodes
    PriorityQueue<TreeNode> pq;
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        pq = new PriorityQueue(new Comparator<TreeNode>(){
            @Override
            public int compare(TreeNode t1, TreeNode t2){
                double val1 = Math.abs(t1.val - target);
                double val2 = Math.abs(t2.val - target);
                return val1 < val2 ? 1 : -1;
            }
        });
        
        find(root, k);
        List<Integer> ans = new ArrayList();
        for(TreeNode n : pq){
            ans.add(n.val);
        }
        return ans;
    }
    private void find(TreeNode root, int k){
        if(root.left != null){
            find(root.left, k);
        }
        pq.offer(root);
        while(pq.size() > k){
            pq.poll();
        }
        if(root.right != null){
            find(root.right, k);
        }
    }
}

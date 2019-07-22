_______________________________________________________Best Solution___________________________________________________________
public class Solution {
    //add element from bottom to top;
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        height(root, res);
        return res;
    }
    // get height , if node == null return - 1 then the height of first leaves layer will be 0
    // add all nodes in same layer to the ans Array
    private int height(TreeNode node, List<List<Integer>> res){
        if(null == node)  return -1;
        int level = 1 + Math.max(height(node.left, res), height(node.right, res));
        // as it adds answer level by level(increase 1), so the when size == level means new level occurs, should initilize a new ArrayList for current level
        if(res.size() == level)  res.add(new ArrayList<>());
        res.get(level).add(node.val);
        return level;
    }
}
_______________________________________________________My Solution_____________________________________________________________
class Solution {
    // recursion inorder.
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> ans = new ArrayList();
        if(root == null) return ans;
        // record all nodes that has been added to answerList
        List<TreeNode> totalAdded = new ArrayList();
        while(!totalAdded.contains(root)){
            // record value of leaves
            List<Integer> med = new ArrayList();
            // recorder leaves in current status
            List<TreeNode> curAdded = new ArrayList();
            add(root, totalAdded, curAdded, med);
            // add med to anslist
            ans.add(med);
            // update totaladded nodes
            totalAdded.addAll(curAdded);
        }
        return ans;
    }
    // inorder recursion
    private void add(TreeNode root, List<TreeNode> totalAdded, List<TreeNode> curAdded, List<Integer> med){
        // has been added will not be added, return directly;
        if(root == null || totalAdded.contains(root)) return;
        add(root.left, totalAdded, curAdded, med);
        // if child has been added or null or one of child is null anther one has been added, cur is leaf
        if( (totalAdded.isEmpty() && root.left == null && root.right == null)
           || (totalAdded.contains(root.left) && (root.right == null || totalAdded.contains(root.right)))
           || (totalAdded.contains(root.right) && (root.left == null || totalAdded.contains(root.left)))){
            // pending to be added into total list
            curAdded.add(root);
            // add to current list with value;
            med.add(root.val);
            return;
        }
        add(root.right, totalAdded, curAdded, med);
    }
}

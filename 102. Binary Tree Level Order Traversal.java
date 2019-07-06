_____________________________________________________Best Solution(Recur)________________________________________________________________
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        helper(root,res,0);
        return res;
    }
    // recur with recording level(which is index of node list in ansList), order is preorder: assure from top to bottom
    private void helper(TreeNode node, List<List<Integer>> lst, int level) {
        if (node == null) return;
        if (lst.size()<=level) {
            lst.add (new LinkedList<Integer>());
        }
        lst.get(level).add(node.val);
        helper(node.left, lst, level+1);
        helper(node.right,lst,level+1);
    }
}
_____________________________________________________My Solution(Recur)__________________________________________________________________
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        helper(root,res,0);
        return res;
    }
    // add value level by level ,record current and next level by Queue
    private void helper(TreeNode node, List<List<Integer>> lst, int level) {
        if (node == null) return;
        while (lst.size()<=level) {
            lst.add (new LinkedList<Integer>());
        }
        lst.get(level).add(node.val);
        helper(node.left, lst, level+1);
        helper(node.right,lst,level+1);
    }
}
______________________________________________________My Solution(No recur)______________________________________________________________
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
    
    public List<List<Integer>> levelOrder(TreeNode root) {
        // ansList
        List<List<Integer>> ans = new ArrayList();
        if(root == null) return ans;
        // record node.val in current level
        List<Integer> med = new ArrayList();
        // use queue to record node level by level
        Queue<TreeNode> recorder = new LinkedList();
        // nextLevel record child nodes of current level
        Queue<TreeNode> nextLevel = new LinkedList();
        // put root in
        recorder.offer(root);
        
        // add nodes till no nodes to add in recorder
        while(!recorder.isEmpty()){
            
            TreeNode cur = recorder.poll();
            if(cur != null){
                med.add(cur.val);
                nextLevel.offer(cur.left);
                nextLevel.offer(cur.right);
            }
            // all nodes in current level have been added to list med 
            if(recorder.isEmpty()){
                //check if med is valid: has added actual value
                if(!med.isEmpty()){
                    //add to ansList
                    ans.add(med);
                }
                // should start next level, renew recorder
                recorder = nextLevel;
                // new nextLevel as it address reference, clear() will affect recorder above
                nextLevel = new LinkedList();
                // new med to record node.val in next level, clear() will affect med in ansList( all med will be empty);
                med = new ArrayList();
            }
        }
        return ans;
    }
}

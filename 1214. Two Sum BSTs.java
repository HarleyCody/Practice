____________________________________________________Beset Solution_____________________________________________________________
// directly, choose root1 search in root2
// donot recorder anything
class Solution {
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        if(root1 == null) return false;
        return search(root2, target - root1.val) || twoSumBSTs(root1.left, root2, target) ||
            twoSumBSTs(root1.right, root2, target);
    }
    private boolean search(TreeNode cur, int target){
        if(cur == null) return false;
        if(cur.val == target) return true;
        if(target < cur.val){
            return search(cur.left, target);
        }else{
            return search(cur.right, target);
        }
    }
}
____________________________________________________My Solution________________________________________________________________
// choose from tree 1 check tree2 
// set for pruning.
class Solution {
    HashSet<Integer> passed = new HashSet();
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        if(root1 == null) return false;
        return search(root2, target - root1.val) || twoSumBSTs(root1.left, root2, target) ||
            twoSumBSTs(root1.right, root2, target);
    }
    private boolean search(TreeNode cur, int target){
        if(cur == null) return false;
        if(passed.contains(target) || cur.val == target) return true;
        passed.add(cur.val);
        
        if(target < cur.val){
            search(cur.left, target);
        }else{
            search(cur.right, target);
        }
        return false;
    }
}

______________________________________________________My Solution____________________________________________________________
// inOrder traverse, if left can be found, then return true; 
// if cur is can be complete by set.contains(K - cur.val) return true
// return result of right;
class Solution {
    HashSet<Integer> recorder = new HashSet();
    public boolean findTarget(TreeNode root, int k) {
        if(root == null) return false;
        if(root.left != null && findTarget(root.left, k)) return true; 
        if(recorder.contains(k - root.val))return true;
        recorder.add(root.val);
        return findTarget(root.right, k);
    }
}

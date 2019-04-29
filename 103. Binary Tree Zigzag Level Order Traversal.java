class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        recAdd(root, 0, ans);
        return ans;
    }
    public void recAdd(TreeNode root, int level, List<List<Integer>> ans){
        if(root == null) return;
        if(ans.size()<=level){
            ans.add(new ArrayList<Integer>());
        }
        if(level % 2 == 0){ // left to right
            ans.get(level).add(root.val);
        }else{ // right to left
            ans.get(level).add(0,root.val);
        }
        recAdd(root.left, level+1, ans);
        recAdd(root.right, level+1, ans);
    }
}

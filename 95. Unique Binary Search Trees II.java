____________________________________________________________________My Solution__________________________________________________________
// add from list<TreeNode> subs, l and r can be empty
// mem for faster
class Solution {
    List<List<List<TreeNode>>> mem;
    public List<TreeNode> generateTrees(int n) {
        mem = new ArrayList();
        for(int i = 0; i <= n; ++i){
            mem.add(new ArrayList());
            for(int j = 0; j <= n; ++j){
                mem.get(i).add(new ArrayList());
            }
        }
        return plant(1, n);
    }
    
    private List<TreeNode> plant(int start, int end){
        if(start > end){
            return new ArrayList();
        }
        
        List<TreeNode> ans = mem.get(start).get(end);
        if(!ans.isEmpty()){
            return ans;
        }

        if(start == end){
            ans.add(new TreeNode(start));
            mem.get(start).set(end, ans);
            return ans;
        }
        

        for(int i = start; i <= end; ++i){
            List<TreeNode> leftTree = plant(start, i - 1);
            List<TreeNode> rightTree = plant(i + 1, end);
            
            if(leftTree.isEmpty()){
                for(TreeNode r : rightTree){
                    TreeNode root = new TreeNode(i);
                    root.right = r;
                    ans.add(root);
                }
            }else if(rightTree.isEmpty()){
                for(TreeNode l : leftTree){
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    ans.add(root);
                }
            }else{
                for(TreeNode l : leftTree){
                    for(TreeNode r : rightTree){
                        TreeNode root = new TreeNode(i);
                        root.left = l;
                        root.right = r;
                        ans.add(root);
                    }
                }
            }
        }
        mem.get(start).set(end, ans);
        return ans;
    }
}

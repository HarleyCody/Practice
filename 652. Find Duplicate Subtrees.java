_____________________________________________________________________________________Best Solution____________________________________________________________________
class Solution {
    //Serialize tree in postorder
    //Map record freq of treeNode
    //add freq > 1 to ans
    HashMap<String, List<TreeNode>> recorder = new HashMap();
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> ans = new ArrayList();
        if(root == null){
            return ans;
        }
        
        find(root);
        
        for(Map.Entry<String, List<TreeNode>> e : recorder.entrySet()){
            List<TreeNode> list = e.getValue();
            if(list.size() > 1){
                ans.add(list.get(0));
            }
        }
        
        return ans;
    }
    
    private String find(TreeNode cur){
        if(cur == null){
            return  "";
        }
        StringBuilder sb = new StringBuilder("(");
        sb.append(find(cur.left));
        sb.append(cur.val);
        sb.append(find(cur.right));
        sb.append(")");
        
        String curStr = sb.toString();
        if(!recorder.containsKey(curStr)){
            recorder.put(curStr, new ArrayList());
        }
        recorder.get(curStr).add(cur);
        
        return curStr;
    }
}

class Solution {
    //construct in binary way
    //find max make it node and connect left and right in range
    int[] data;
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        data = nums;
        return constructInRange(0, nums.length);
    }
    
    private TreeNode constructInRange(int l , int r){
        if(l >= r){
            return null;
        }
        if(l == r - 1){
            return new TreeNode(data[l]);
        }
        int s = l, e = r;
        int rootVal = Integer.MIN_VALUE, rootIdx = -1; 
        for(int i = s; i < e; ++i){
            if(rootVal < data[i]){
                rootVal = data[i];
                rootIdx = i;
            }
        }
        TreeNode cur = new TreeNode(rootVal);
        cur.left = constructInRange(l, rootIdx);
        cur.right = constructInRange(rootIdx + 1, r);
        
        return cur;
    }
}

class Solution {
    // bottom up: update min max from bottom to up
    // update max Diff by max(cur - min, cur - max, max Diff in child (could be from left child and right child)); 
    public int maxAncestorDiff(TreeNode root) {
        return getMaxDiff(root)[2];
    }
    // Info[0] = min in child
    // Info[1] = max in child
    // Info[2] = maxDiff in child
    private int[] getMaxDiff(TreeNode root){
        int min, max, val;
        if(root.left != null && root.right != null){
            int[] leftInfo = getMaxDiff(root.left);
            int[] rightInfo = getMaxDiff(root.right);
            
            min = Math.min(leftInfo[0], rightInfo[0]);
            max = Math.max(leftInfo[1], rightInfo[1]);
            val = Math.max(leftInfo[2], rightInfo[2]);
        }else if(root.left != null){
            int[] leftInfo = getMaxDiff(root.left);
            
            min = leftInfo[0];
            max = leftInfo[1];
            val = leftInfo[2];
            
        }else if(root.right != null){
            int[] rightInfo = getMaxDiff(root.right);
            min = rightInfo[0];
            max = rightInfo[1];
            val = rightInfo[2];
            
        }else{
            min = root.val;
            max = root.val;
            val = 0;
        }
        val = Math.max(val, Math.abs(root.val - max));
        val = Math.max(val, Math.abs(root.val - min));
        min = Math.min(root.val, min);
        max = Math.max(root.val, max);

        return new int[]{min, max, val};
    }
}

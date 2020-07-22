___________________________________________________________________________My Solution__________________________________________________________________________________________
//Construct with pointer
class Solution {
    int[] nums;
    public TreeNode bstFromPreorder(int[] preorder) {
        nums = preorder;
        return construct(0, nums.length);
    }
    
    private TreeNode construct(int s, int e){
        if(s == e){
            return null;
        }
        
        TreeNode cur = new TreeNode(nums[s]);
        int r = s + 1;
        while(r < e && nums[r] < nums[s]){
            ++r;
        }
        
        cur.left = construct(s + 1, r);
        cur.right = construct(r, e);
        
        return cur;
    }
}
___________________________________________________________________________My Solution__________________________________________________________________________________________
//Construct with copy
class Solution {
    int[] nums;
    public TreeNode bstFromPreorder(int[] preorder) {
        if(preorder == null || preorder.length == 0){
            return null; 
        }
        TreeNode root = new TreeNode(preorder[0]);
        
        int rStart = preorder.length;
        for(int i = 0; i < preorder.length; ++i){
            if(preorder[i] > preorder[0]){
                rStart = i;
                break;
            }
        }
        root.left = bstFromPreorder(Arrays.copyOfRange(preorder,1, rStart));
        root.right = bstFromPreorder(Arrays.copyOfRange(preorder, rStart, preorder.length));
        
        return root;
    }
}

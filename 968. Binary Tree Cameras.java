class Solution {
    // post order traverse
    // cur min == left min + right min;
    // if left and right are all not monitered, add monitor at cur
    // start install monitor at parent nodes cloest to leaves
    public int minCameraCover(TreeNode root) {
        if(root == null){
            return 0;
        }
        
        if(root.left == null && root.right == null){
            return 1;
        }
        
        int ans = monitor(root, new TreeNode(0));
        if(root.val == 0){
            root.val = 1;
            ++ans;
        }
        return ans;
    }
    
    private int monitor(TreeNode cur, TreeNode prev){
        TreeNode l = cur.left;
        TreeNode r = cur.right;
        
        int ans = 0;
        if(l != null){
            if(l.left == null && l.right == null){
                cur.val = 1; 
                l.val = prev.val = 1;
                
                if(r != null){
                    r.val = 1;
                }
                
                ++ans;
            }else{
                ans += monitor(l, cur);
            }
        }
        
        
        if(r != null){
            if(r.left == null && r.right == null){
                if(r.val != 1){
                    cur.val = 1;
                    if(l != null){
                        l.val = 1;
                    }
                    r.val = prev.val = 1;
                    ++ans;
                }
            }else{
                ans += monitor(r, cur);
            }
        }

        if((l != null && l.val == 0) || (r != null && r.val == 0)){
            ++ans;
            cur.val = prev.val = 1;
            if(r != null){
                r.val = 1;
            }else{
                l.val = 1;
            }
        }
        return ans;
    }
}

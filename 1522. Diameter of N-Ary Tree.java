//My Solution: DFS to find the max depth1 and max depth2, diameter = max(max1 + max2, diameter); return max depth
class Solution {
    int ans = 0;
    public int diameter(Node root) {
        findDiameter(root);
        
        return ans;
    }
    
    private int findDiameter(Node root){
        if(root == null) return 0;
        
        int max1 = 0, max2 = 0;
        for(Node n : root.children){
            int depth = findDiameter(n);
            if(depth >= max1){
                max2 = max1;
                max1 = depth;
            }else if(depth > max2){
                max2 = depth;
            }
        }
        ans = Math.max(max1 + max2, ans);
        
        return 1 + max1;
    }
}

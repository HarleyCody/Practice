class Solution {
    public int[] pourWater(int[] heights, int V, int K) {
        for(int i = V; i > 0; --i){
            int pos = checkPos(heights, K);
            ++heights[pos];
        }
        return heights;
    }
    public int checkPos(int[] height, int K){
        int ans = K, min = height[K];
        // find toward left first, 
        for(int i = K - 1; i >= 0; --i){
            //if left is heigher, can flow to left, break || if lowest point is found return
            if(height[i] > height[K] || min < height[i]) break;
            if(min > height[i]){ // keep finding lowest point
                min = height[i];
                ans = i;
            }
        }
        if(height[ans] < height[K]) return ans;
        // find toward right
        for(int i = K; i < height.length; ++i){
            //if right is heigher, can flow to right, break || lowest point is found, return
            if(height[i] > height[K] || min < height[i]) break;
            if(min > height[i]){ // keep finding lowesr point
                min = height[i];
                ans = i;
            }
        }
        return ans;
    }
}

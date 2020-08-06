_____________________________________________________________________________________My Solution______________________________________________________________________
class Solution {
    // only when current mid is larger, need to find new range otherwise use range from last time to continue find boarders
    Integer l = null, r = null;
    int len;
    public int largestRectangleArea(int[] heights) {

        len = heights.length;
        
        int ans = 0, area = 0;
        for(int m = 0; m < len; ++m){
            if(m != 0 && heights[m] <= heights[m - 1]){
                area = deepSearch(heights, m);
            }else{
                l = m;
                r = m;
                area = deepSearch(heights, m);
            }
            ans = Math.max(ans, area);
        }
        
        return ans;
    }
    
    private int deepSearch(int[] heights, int m){
        if(l == null){
            l = m;
        }
        while(0 <= l && heights[l] >= heights[m]){
            --l;
        }
        
        if(r == null){
            r = m;
        }
        while(r < len && heights[m] <= heights[r]){
            ++r;
        }
        
        return (r - l - 1) * heights[m];
    }
}

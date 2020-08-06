__________________________________________________________________________________Best Solution______________________________________________________________________
class Solution {
    // mono stack, stack only save the rectangle that is not higher than current, so faster to find boarder
    // for loop to find all the area has rectangle higher than height[i];
    // stack is in a increasing order so the previous is the start of current;
    public int largestRectangleArea(int[] h) {
        int max = 0;
        Stack<Integer> s = new Stack<>();
        s.push(0);
        
        for (int i = 1; i <= h.length; i++) {
            if (i == h.length || h[i] < h[i-1]) {
                int newHeight = i == h.length ? 0 : h[i];
                // calculate size of all rectangles that just ended
                while (!s.isEmpty() && h[s.peek()] > newHeight) {
                    int rectHeight = s.pop();
                    int startIx    = s.isEmpty() ? 0 : s.peek() + 1;
                    int area       = h[rectHeight] * (i - startIx);
                    max            = Math.max(max, area);
                }
            } else if (h[i] == h[i-1]) {
                s.pop(); // same as prev, move the boundary marker
            }
            s.push(i);
        }
        return max;
    }
}
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

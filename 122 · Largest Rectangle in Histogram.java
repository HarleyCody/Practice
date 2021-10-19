//My Solution: Monotone stack: Detect the left and right boundary for num[i]
//Left Boundary: pop left as long as heights[sta.peek()] is smaller than heights[i]
//Right Boundary: pop right as long as heights[sta.peek()] is smaller than heights[i]
//IMPROVE!: user array and pointer to simulate stack, when update idx = leftB[idx];(like find parent of Union find)
public class Solution {
    public int largestRectangleArea(int[] heights) {
         int ans = 0;
         int idx = -1;
         int len = heights.length;
         int[] leftB = new int[len];
         leftB[0] = idx;
         for(int i = 0; i < len; ++i){
             while(idx >= 0 && heights[i] <= heights[idx]){
                 idx = leftB[idx];
             }
             leftB[i] = idx;
             idx = i;
         }

         idx = len;
         int[] rightB = new int[len];
         rightB[len - 1] = idx;
         for(int i = len - 1; 0 <= i; --i){
             while(idx < len && heights[i] <= heights[idx]){
                 idx = rightB[idx];
             }
             rightB[i] = idx;
             idx = i;
             ans = Math.max(ans, heights[i] * (rightB[i] - leftB[i] - 1));
         }

         return ans;
    }
}

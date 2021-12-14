
//My Solution: right to left get the idx of current max
class Solution {
    public int[] findBuildings(int[] heights) {
        int len = heights.length;
        int idx = len - 1;
        int aIdx = len - 1;
        int max = 0;
        while(0 <= idx){
            if(heights[idx] > max){
                max = heights[idx];
                heights[aIdx--] = idx;
            }
            --idx;
        }
        
        ++aIdx;
        int[] ans = new int[heights.length - aIdx];
        for(int a = 0; a < ans.length; ++a, ++aIdx){
            ans[a] = heights[aIdx];
        }
        
        return ans;
    }
}
//My Solution: right to left get the idx of current max
class Solution {
    public int[] findBuildings(int[] heights) {
        List<Integer> ansList = new ArrayList();
        int idx = heights.length - 1;
        
        int max = 0;
        while(0 <= idx){
            if(heights[idx] > max){
                max = heights[idx];
                ansList.add(idx);
            }
            --idx;
        }
        
        int[] ans = new int[ansList.size()];
        int aIdx = 0;
        for(int s = ansList.size() - 1; 0 <= s; --s){
            ans[aIdx++] = ansList.get(s);
        }
        
        return ans;
    }
}

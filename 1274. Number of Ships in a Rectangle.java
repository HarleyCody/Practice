//My Solution: Binary Search, only search area has ship. for left = 1 and right = 2,  mid == 1 we search left mid and mid + 1 to right;
class Solution {
    public int countShips(Sea sea, int[] topRight, int[] bottomLeft) {
        if(!sea.hasShips(topRight, bottomLeft)) return 0;
        if(bottomLeft[0] == topRight[0] && bottomLeft[1] == topRight[1]) return 1;
        int left = bottomLeft[0];
        int right = topRight[0];
        int bot = bottomLeft[1];
        int top = topRight[1];
        int midX = (left + right) / 2;
        int midY = (bot + top) / 2;
        
        int ans = countShips(sea, topRight, new int[]{midX + 1, midY + 1});
        
        ans += countShips(sea, new int[]{midX, midY}, bottomLeft);
        
        ans += countShips(sea, new int[]{midX, top}, new int[]{left, midY + 1});
        
        ans += countShips(sea, new int[]{right, midY}, new int[]{midX + 1, bot});

        return ans;
    }
}

class Solution {
    // left to right and right to left
    // adding from left to right make sure right students with higher rating gets more candies
    // adding from right to left, make sure left students with higher rating gets more candies
    // sum up candies
    public int candy(int[] ratings) {
        int len = ratings.length;
        int[] candies = new int[len];
        candies[0] = 1;
        for(int i = 1; i < len; ++i){
            if(ratings[i] > ratings[i - 1]){
                candies[i] = candies[i - 1] + 1;
            }else{
                candies[i] = 1;
            }
        }
        
        int ans = candies[len - 1];
        for(int i = len - 2; 0 <= i; --i){
            if(ratings[i] > ratings[i + 1]){
                candies[i] = Math.max(candies[i + 1] + 1, candies[i]);
            }
            ans += candies[i];
        }
        return ans;
    }
}

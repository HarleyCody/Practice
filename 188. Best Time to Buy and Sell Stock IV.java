______________________________________________________________________________Best dp Solution______________________________________________________________________________
// Seperate the situation：
//  1. if we have enough chance to buy and sell all stock, we can just calcualte the sum of all stocks profit;
// 2. otherwise, dp to answer;
// dp is different here, dp[i][j] means at point i buy k times the max profit
// current times depend on previous times
// benefit, donot need to check if max has reached as maybe just need 3 times to get max profit from 100 days stocks
// so depend on times is better than depend on days. key diff is
// depend on days need to check current times has already reach the max, depend on times do not need this three lines
/*
            if(sell[len - 1] == prevSell[len - 1]){
                return sell[len - 1];
            }
*/
class Solution {
    public int maxProfit(int K, int[] prices) {
        int len = prices.length;
        if (K == 0 || len == 0) return 0;
        if (K * 2 < len){
            int[] buy = new int[k], sell = new int[k];
            for (int i = 0; i < k; i++){
                buy[i] = Integer.MIN_VALUE;
            }
            for (int price: prices){
                for (int i = k-1; i >= 0; i--){
                    sell[i] = Math.max(sell[i], buy[i] + price);
                    buy[i] = Math.max(buy[i], i == 0? -price: sell[i-1] - price);
                }
            }
        }
        else{
            int result = 0;
            for (int i = 0; i < len - 1; i++){
                result += Math.max(prices[i + 1] - prices[i], 0);
            } 
            return result;
        }
    }
}
______________________________________________________________________________My 1d dp Solution______________________________________________________________________________
// improve conduced 2d to 1d as k only related to k - 1
// delete buy[], as buy only related to prevBuy at k and sell[k - 1][p - 1];
// stop when at k is same as at k - 1. Do not need transaction exactly k times to reach max
class Solution {
     
    public int maxProfit(int K, int[] prices) {
        int len = prices.length;
        if(len == 0){
            return 0;
        }
        int[] sell = new int[len];
        int[] prevSell = new int[len];

        for(int k = 0; k < K; ++k){
            int curBuy = 0, prevBuy = -prices[0], start = Math.max(1, 2 * k - 2);
            for(int p = start; p < len; ++p){
                curBuy = Math.max(prevSell[p - 1] - prices[p], prevBuy);
                sell[p] = Math.max(prevBuy + prices[p], sell[p - 1]);
                prevBuy = curBuy;
            }
            if(sell[len - 1] == prevSell[len - 1]){
                return sell[len - 1];
            }
            prevSell = sell;
            sell = new int[len];
        }
        
        return prevSell[len - 1];
    }
}
______________________________________________________________________________My 2d dp Solution(MLE)______________________________________________________________________________
// Solve MLE use 1D dp
class Solution {
    public int maxProfit(int K, int[] prices) {
        int len = prices.length;
        if(len == 0){
            return 0;
        }
        int[][] buy = new int[K][len];
        int[][] sell = new int[K][len];
        
        int ans = 0;
        for(int k = 0; k < K; ++k){
            buy[k][0] = -prices[0];
            for(int p = 1; p < len; ++p){
                if(k == 0){
                    buy[0][p] = Math.max(-prices[p], buy[0][p - 1]);
                    sell[0][p] = Math.max(buy[0][p - 1] + prices[p], sell[0][p - 1]);
                }else{
                    buy[k][p] = Math.max(sell[k - 1][p - 1] - prices[p], buy[k][p - 1]);
                    sell[k][p] = Math.max(buy[k][p - 1] + prices[p], sell[k][p - 1]);
                }
                if(k == K - 1){
                    ans = Math.max(ans, sell[k][p]);
                }
            }
        }
        return ans;
    }
}

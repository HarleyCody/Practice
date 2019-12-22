________________________________________________________Best Solution______________________________________________________________________
class Solution {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length < 2){
            return 0;
        }
        int len = prices.length;
        // left first deal end at i;
        int[] left = new int[len];
        // second deal start at i
        int[] right = new int[len];
        
        left[0] = 0;
        int min = prices[0];
        
        for(int i = 1; i < prices.length; i++){
            // first buy find min price to buy;
            min = Math.min(min, prices[i]);
            // max profit with first sell at i;
            left[i] = Math.max(left[i-1], prices[i]-min);
        }
        
        right[prices.length-1] = 0;
        int max = prices[prices.length-1];
        
        for(int i= prices.length-2; i>=0; i--){
            // sell at i; find max price to sell
            max = Math.max(max, prices[i]);
            // max profit of second buy at i with sell price after i;
            right[i]=Math.max(right[i+1], max-prices[i]);
        }
        int profit = 0;
        for(int i=0; i<prices.length; i++){
            // max profit of sum firt deal and second deal
            profit = Math.max(profit, left[i]+right[i]);
        }
        return profit;
    }
}
_________________________________________________________My Solution_______________________________________________________________________
class Solution {
// dp1 max profit with first by at day i; dp1 = max(dp1[i - 1], 0 - price[i])
// dp2 max profit with first sell at day i; dp2 = max(dp2[i - 1], dp1[i - 1] + price[i])
// dp3 max profit with second sell at day i; dp3 = max(dp3[i - 1], dp2[i - 1] - prcies[i])
// dp4 max profit with second buy at day i; dp4 = max(dp4[i - 1], dp3[i - 1] + prcies[i]
// transition relation dp2 from dp1, dp3 from dp2 dp4 from dp3
    public int maxProfit(int[] prices) {
        if(prices.length == 0) return 0;
        int len = prices.length;
        int[] dp1 = new int[len];
        int[] dp2 = new int[len];
        int[] dp3 = new int[len];
        int[] dp4 = new int[len];
        dp1[0] = 0 - prices[0];
        if(len > 2)dp3[1] = 0 - prices[1];
        for(int i = 1; i < len; ++i){
            if(i > 0){
                dp1[i] = Math.max(dp1[i - 1], 0 - prices[i]);
                dp2[i] = Math.max(dp2[i - 1], dp1[i - 1] + prices[i]);
                
            }
            if(i > 1){
                dp3[i] = Math.max(dp3[i - 1], dp2[i - 1] - prices[i]);
                
            }
            if(i > 2){
                dp4[i] = Math.max(dp4[i - 1], dp3[i - 1] + prices[i]);
            }
        }
        return Math.max(dp2[len - 1], dp4[len - 1]);
    }
}

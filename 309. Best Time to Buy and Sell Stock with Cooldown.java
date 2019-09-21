_________________________________________________________________DP with O(1) space________________________________________________________
class Solution {
    // dp with array;
    // buy: record maxProfit with ith days last operation is buy; Buy comes after(lastSell or previous day is buy);
    // sell: record maxProfit with ith day last operation is sell; sell comes after(previous day is sell)
    /* rest: is ignored here cause rest = Math.Max( rest[i - 1], sell[i - 1], buy[i - 1]); 
    
    buy < = rest as it comes after rest, rest <= sell as it comes after buy and sell. so rest is not needed here,
    only need to record lastSell in i - 2 days and lastBuy in i - 1 days; two variables is enough to record them.
    
    buy[i] = max(sell[i-2] - price, buy[i-1])
    sell[i] = max(buy[i-1] + price, sell[i-1])
    */
    public int maxProfit(int[] prices) {
        if(prices.length == 0) return 0;
        // length + 1: as day zero come after day -1, so make zero as initial status. 
        int lastBuy = 0, lastSell = 0, buy = Integer.MIN_VALUE, sell = 0;
        for(int i = 0; i < prices.length; i++){
            // buy comes after rest or buy. if buy in current day, cost money Prices[i];
            // i + 1 cause 0 is initial status, 0 in prices is 1 in buy sell and rest;
            lastBuy = buy;
            buy = Math.max(lastSell - prices[i], lastBuy);
            // renew last sell here as it may affect buy in lastSell - prices[i], should be previous sell. 
            // if it is below next line(sell =..) lastSell will be newest value;
            lastSell = sell;
            sell = Math.max(lastBuy + prices[i], lastSell);
        }
        return sell;
    }
}
_________________________________________________________________DP with Array_____________________________________________________________
class Solution {
    // dp with array;
    // buy: record maxProfit with ith days last operation is buy; Buy comes after(cooldown or previous day is buy);
    // sell: record maxProfit with ith day last operation is sell; sell comes after(previous day is sell)
    // rest: record maxProfit with ith day last operation is doing nothing(not sell, not buy, cooldown); rest comes after(previous day is rest or sell);
    public int maxProfit(int[] prices) {
        if(prices.length == 0) return 0;
        // length + 1: as day zero come after day -1, so make zero as initial status. 
        int[] buy = new int[prices.length + 1]; 
        int[] rest = new int[prices.length + 1];
        int[] sell = new int[prices.length + 1];
        // buy[0] is MIN_VALUE cause before day 0 no stock is holding, 
        // so when we sell in day zero, we should not get any profit, profit is still MIN_VALUE;
        buy[0] = Integer.MIN_VALUE;
        for(int i = 0; i < prices.length; i++){
            // buy comes after rest or buy. if buy in current day, cost money Prices[i];
            // i + 1 cause 0 is initial status, 0 in prices is 1 in buy sell and rest;
            buy[i + 1] = Math.max(rest[i] - prices[i], buy[i]);
            sell[i + 1] = buy[i] + prices[i];
            rest[i + 1] = Math.max(sell[i], rest[i]);
        }
        return Math.max(sell[prices.length], rest[prices.length]);
    }
}

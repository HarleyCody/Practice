//Best Solution: Binary Serach to find the threshold that close to fill all orders. O(n) for settle down the remain
class Solution {
     public int maxProfit(int[] inventory, int orders) {
        int max = 0;
        for (int in : inventory) {
            max = Math.max(max, in);
        }
        int lo = 0, hi = max;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (getBallCnt(inventory, mid, orders) > orders) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        int remainMax = lo;
        long res = 0L;
        for (int cnt : inventory) {
            if (cnt <= remainMax) continue;
            res += (long) (cnt + remainMax + 1) * (long) (cnt - remainMax) / 2;
            orders -= cnt - remainMax;
        }
        res += (long) remainMax * (long) orders;

        return (int) (res % 1000000007);
    }

    private int getBallCnt(int[] inventory, int remainMax, int total) {
        int res = 0;
        for (int cnt : inventory) {
            if (cnt <= remainMax) continue;
            res += (cnt - remainMax);
            if (res > total) break;
        }
        return res;
    }
}
//My Solution: count the volumn and calculate price from larger to smaller
//             Sort array and start from highest price sale higheset price prices

class Solution {
    public int maxProfit(int[] inventory, int orders) {
        Arrays.sort(inventory);
        int mod = (int) 1e9 + 7;
        int len = inventory.length;
        int idx = len - 1;
        
        int start = 0;
        int end = 0;
        int diff = 0;
        
        long ans = 0;
        while(0 <= idx && orders > 0){
            while(0 <= idx && inventory[idx] == inventory[len - 1]){
                --idx;
            }
            start = 0;
            end = inventory[len - 1];
            if(idx >= 0){
                start = inventory[idx] + 1;
            }
            int colors = len - 1 - idx;
            int count = end - start + 1;
            long volumn = colors * (end - start + 1);
            
            if(volumn <= orders){
                orders -= volumn;
                inventory[len - 1] = inventory[idx];
            }else{
                int remain = orders % colors;
                start = end - orders / colors;
                ans += (long)remain * start;
                ++start;
                
                count = end - start + 1;
                orders = 0;
            }
            ans += (long)colors * (start + end) * count / 2;
        }
        return (int) (ans % 1000000007);
    }
}

___________________________________________________________Best Solution_________________________________________________________________
class Solution {
    // improvement1  calculate time by (p + m - 1) / m instad of p / m + (p % m == 0 ? 0 : 1);
    // improvement2  complete in one function
    // 1 improves 7ms 2 improves 2ms
    public int minEatingSpeed(int[] piles, int H) {
        int l = 1, r = 1000000000;
        while (l < r) {
            int m = (l + r) / 2, total = 0;
            for (int p : piles){
                total += (p + m - 1) / m;
            }
            if (total > H){
                l = m + 1; 
            }else{
                r = m;
            }
        }
        return l;
    }
}
________________________________________________________My Solution(Binary Search)________________________________________________________
class Solution {
// left = average, right = max speed, binary search
    public int minEatingSpeed(int[] piles, int H) {
        int len = piles.length;
        int sum = 0, max = 0;
        for(int p : piles){
            sum += p;
            max = Math.max(max, p);
        }
        if(len == H){
            return max;
        }
        
        int left = sum / H + (sum % H == 0? 0 : 1), right = max;
        
        while(left < right){
            int mid = (left + right) / 2;
            if(eatAtSpeed(mid, piles, H)){
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        return right;
    }
    private boolean eatAtSpeed(int speed, int[] piles, int h){
        int time = 0;
        for(int p : piles){
            if(p < speed){
                ++time;
            }else{
                time += p / speed + (p % speed == 0 ? 0 : 1);
            }
            if(time > h){
                return false;
            }
        }
        return true;
    }
}

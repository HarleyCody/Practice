____________________________________________________________My Solution(Binary Search)___________________________________________________
class Solution {
// Binary Search to narrow down the 
// key is how to get smaller range of min and max
// naive:(0, sum) better(hight, sum) best(0, 500 * length / D) 
    public int shipWithinDays(int[] weights, int D) {
        int len = weights.length;
        if(len == 1) return weights[0];
        
        // set min and max to narrow down the range
        int min = 0, max = (500 * weights.length) / D;
        
        while(min < max){
            int mid = (min + max) / 2;
            
            // mid > actual weight
            if(canShip(weights, D, mid)){
                max = mid;
            }else{
                min = mid + 1;
            }
        }
        return max;
    }
    // check if can be ship with weight limitation
    private boolean canShip(int[] weights, int day, int limit){
        int len = weights.length;
        int w = 0, d = 0;
        while(d < day && w < len){
            int sum = 0;
            while(w < len && sum < limit){
                sum += weights[w];
                
                if(sum > limit)break;
                
                ++w;
            }
            if(w < len) ++d;
        }
        if(d == day) return false;
        return true;
    }
}

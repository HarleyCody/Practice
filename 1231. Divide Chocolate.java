_______________________________________________________________________Fastest BinarySearch Solution______________________________________________________________________
class Solution {
    public int maximizeSweetness(int[] sweetness, int K) {
        
        int sum = 0;
        
        for(int sweet : sweetness) {
            sum += sweet;
        }
        
        sum = sum/(K+1);
        
        return divide(1,sum, sweetness, K);
        
    }
    
    private int divide(int low, int high, int[] sweetness, int k) {
        
        int result = 0;
        while(low<=high) {
            int mid = (high-low)/2 + low;
            if(isPossible(sweetness, k, mid)) {
                result = mid;
                low = mid+1;
            }
            else {
                high = mid-1;
            }
        }
        
        return result;
    }
    
    private boolean isPossible(int[] sweetness, int k, int sum) {
        int prefix = 0, count = 0;
        
        int i=0;
        while(i < sweetness.length) {
            prefix += sweetness[i];
            if(prefix >= sum) {
                prefix = 0;
                count++;
            }
            i++;
        }
        
        if(count >= k+1) {
            return true;
        }
        return false;        
    }
      
}
_______________________________________________________________________BinarySearch Improved Solution______________________________________________________________________
while(min < max){
    // +1 to avoid dead lock
    int mid = (min + max + 1) / 2;
    if(!canSplit(sweetness, mid, K + 1)){
        max = mid - 1;
    }else{
        min = mid;
    }
}
return min;
_____________________________________________________________________________My Solution______________________________________________________________________
class Solution {
    // Binary search the answer
    // if can divide, thresh is too small, then min = mid increae the bar
    // if cannot divide, thresh is too big, then max = mid
    public int maximizeSweetness(int[] sweetness, int K) {
        int len = sweetness.length;
        int min = Integer.MAX_VALUE, sum = 0;
        
        for(int s : sweetness){
            min = Math.min(min, s);
            sum += s;
        }
        int max = sum / (K + 1);
        while(min < max){
            int mid = (min + max + 1) / 2;
            if(!canSplit(sweetness, mid, K + 1)){
                max = mid;
            }else{
                min = mid + 1;
            }
        }
        return canSplit(sweetness, max, K + 1) ? max : max - 1;
    }
    
    private boolean canSplit(int[] sweet, int tar, int num){
        int n = 0, i = 0;
        int sum = 0, len = sweet.length;
        while(i < len){
            while(i < len && sum < tar){
                sum += sweet[i++];
            }
            if(sum < tar){
                break;
            }else{
                sum = 0;
                ++n;
            }
            if(n == num){
                return true;
            }
        }
        return false;
    }
}

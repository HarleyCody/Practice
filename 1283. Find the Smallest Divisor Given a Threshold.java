______________________________________________________________Best Solution______________________________________________________________
class Solution {
    // Improvement: 1 sum += (x + divisor - 1) / divisor
    // 2 val > threshold use l = m + 1 first
    public int smallestDivisor(int[] nums, int threshold) {
        int left = 1, right = 1000000;
        while (left < right) {
            int mid = (left + right) / 2;
            int val = computeThreshold(nums, mid);
            if (val > threshold) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
    
    public int computeThreshold(int[] nums, int divisor) {
        int sum = 0;
        for (int x : nums) {
            sum += (x + divisor - 1) / divisor;
        }
        return sum;
    }
}
____________________________________________________________My Binary Search_____________________________________________________________
class Solution {
    // Binary Search to narrow down the range
    public int smallestDivisor(int[] nums, int threshold) {
        int r = 1000000, l = 0;
        
        while(l < r){
            int m = (l + r) / 2;
            if(m == 0){
                return 1;
            }
            if(sum(nums, m) <= threshold){
                r = m;
            }else{
                l = m + 1;
            }
        }
        return r;
    }
    
    private int sum(int[] nums, int d){
        int ans = 0;
        for(int n : nums){
            ans += (n / d);
            ans += (n % d) == 0 ? 0 : 1;
        }
        return ans;
    }
}

//Best Solution: count and get one by one with a while loop
class Solution {
    public int kthFactor(int n, int k) {
        int base = 1;
        int count = 0;
        
        while (base <= n) {
            if ( n % base == 0) {
                ++count;
            }
            
            if (count == k) {
                return base;
            }
            ++base;
        }
        
        return -1;
    }
}
//My Solution: Compute the list and find answer
class Solution {
    public int kthFactor(int n, int k) {
        int[] factors = new int[n];
        int left = 0;
        int right = n - 1;
        
        int mid = (int)Math.sqrt(n);
        for(int i = 1; i < mid; ++i){
            int r = n / i;
            if(r * i == n){
                factors[left++] = i;
                factors[right--] = r;
            }
        }
        if(n % mid == 0){
            factors[left++] = mid;
            if(mid * mid != n){
                factors[right--] = n / mid;
            }
        }
        int size = left + n - right - 1;
        
        if(k > size) return -1;
        return k <= left ? factors[k - 1] : factors[right + k - left];
    }
}

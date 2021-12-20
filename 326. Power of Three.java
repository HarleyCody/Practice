//My Solution: Binary search log(N)
class Solution {
    public boolean isPowerOfThree(int n) {
        if(n <= 0)return false;
        if(n == 3 || n == 9 || n == 1) return true;
        
        int mid = (int)Math.sqrt(n);
        if(mid * mid == n){
            return isPowerOfThree(mid);
        }
        if(n % 3 == 0) {
            return isPowerOfThree(n / 3);
        }
        return false;
    }
}

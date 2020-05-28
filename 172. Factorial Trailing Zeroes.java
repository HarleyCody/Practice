class Solution {
    //count how many 5 we have in n; 25 countains two 5s, so n == 25 ans = 25 / 5 + 5 / 5
    public int trailingZeroes(int n) {
        int ans = 0;
        while(n > 1){
            ans += n / 5;
            n /= 5;
        }
        return ans;
    }
}

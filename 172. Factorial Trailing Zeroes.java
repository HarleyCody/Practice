________________________________________________________________________Best Solution______________________________________________________
class Solution {
    // even number with 5 contribute a 0, number of 2 in N! will definitely larger than number of 5 in it. 
    //So number of 0 relies on number of 5
    //count how many 5 we have in n; 25 countains two 5s (25 = 5 * 5), so n == 25 ans = 25 / 5 + 5 / 5
    public int trailingZeroes(int n) {
        int ans = 0;
        while(n > 1){
            ans += n / 5;
            n /= 5;
        }
        return ans;
    }
}

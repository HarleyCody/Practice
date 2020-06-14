__________________________________________________________________dp Solution____________________________________________________________
class Solution {
    // dp[i] means max value with reminder i when divided by 3;
    // formula dp[(i + n) % 3] can from dp_prev[(i + n) % 3] or dp[i] + n, choose n or not choose n;
    public int maxSumDivThree(int[] nums) {
        int dp[] = {0, Integer.MIN_VALUE, Integer.MIN_VALUE};
        for(int n : nums){
            int[] dp2 = new int[3];
            for(int i = 0; i < 3; ++i){
                dp2[(i + n) % 3] = Math.max(dp[(i + n) % 3], dp[i] + n);
            }
            dp = dp2;
        }
        return dp[0];
    }
}
__________________________________________________________________My Solution____________________________________________________________
class Solution {
    // sum - minimal number
    // sum of 3 nums all with reminder 1 or reminder 2 can be dividide by 3
    // find how many nums with reminder1 or reminder 2 can not make group of 3;
    // 1 reminder1 can pair with 1 reminder2 to be divided by 3;
    // So try to find do we need to remove nums from which group;
    // group is decided by which remaining nums are larger. 
    // eg 1 has 2 remain, 2 has 1 remain. so 1 remain from 1 can pair with 1 remain from 2 to be divisible by 3;
    // as 1 has 2 remain, so we think delete 1 from 1 remain or delete two from two remain to match extra 1 from 1 remain;
    // choose the min cost to balance;
    // sum - mincost == ans;
    public int maxSumDivThree(int[] nums) {
        int ans = 0, num1 = 0, num2 = 0;
        int n1s1 = 10001, n1s2 = 10001, n2s1 = 10001, n2s2 = 10001;
        for(int n : nums){
            if(n % 3 == 2){
                ++num2;
                if(n2s1 >= n){
                    n2s2 = n2s1;
                    n2s1 = n;
                }else if(n2s2 > n){
                    n2s2 = n;
                }
            }else if(n % 3 == 1){
                ++num1;
                if(n1s1 >= n){
                    n1s2 = n1s1;
                    n1s1 = n;
                }else if(n1s2 > n){
                    n1s2 = n;
                }
            }
            ans += n;
        }

        num1 %= 3;
        num2 %= 3;
        
        if(num1 == 0 && num2 == 0 || num2 == num1){
            return ans;
        }

        int tar = num1 > num2 ? 1 : 2;
        int num = tar == 1 ? num1 - num2 : num2 - num1;
        int crt = 0;
        if(tar == 1){
            if(num == 1){
                crt = Math.min(n1s1, n2s1 + n2s2);
            }else{
                crt = Math.min(n1s1 + n1s2, n2s1);
            }
        }else{
            if(num == 1){
                crt = Math.min(n2s1, n1s1 + n1s2);
            }else{
                crt = Math.min(n2s1 + n2s2, n1s1);
            }
        }
        return ans - crt;
    }
}

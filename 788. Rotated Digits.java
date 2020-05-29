__________________________________________________________________Best Solution_________________________________________________________
class Solution {
    // key wheather we have 2 5 6 9 in previous char, weather it was less than N
    // start from dp[k][true][true] = 1, end at [0][true][false]
    // status dp[k][true][true] it was close to N and have previous invertable char at k 
    // status dp[k][false][false] it was not close to N and donot have previous invertable char at k
    // status dp[k][false][true] it was not close to N and have previous invertable char at k
    // status dp[k][true][false] it was close to N and donot have previous invertable char at k
    // dp[i][true][false] = dp[i + 1][true][false] + dp[i + 1][true][true];
    // dp[i][false][false] = dp[i + 1][false][false] + dp[i + 1][false][true];
    public int rotatedDigits(int N) {
        char[] A = String.valueOf(N).toCharArray();
        int K = A.length;

        int[][][] memo = new int[K+1][2][2];
        memo[K][0][1] = memo[K][1][1] = 1;
        for (int i = K - 1; i >= 0; --i) {
            for (int eqf = 0; eqf <= 1; ++eqf)
                for (int invf = 0; invf <= 1; ++invf) {
                    // We will compute ans = memo[i][eqf][invf],
                    // the number of good numbers with respect to N = A[i:].
                    // If eqf is true, we must stay below N, otherwise
                    // we can use any digits.
                    // Invf becomes true when we write a 2569, and it
                    // must be true by the end of our writing as all
                    // good numbers have a digit in 2569.
                    int ans = 0;
                    for (char d = '0'; d <= (eqf == 1 ? A[i] : '9'); ++d) {
                        if (d == '3' || d == '4' || d == '7') continue;
                        boolean invo = (d == '2' || d == '5' || d == '6' || d == '9');
                        ans += memo[i+1][d == A[i] ? eqf : 0][invo ? 1 : invf];
                    }
                    memo[i][eqf][invf] = ans;
                }
        }
        return memo[0][1][0];
    }
}
__________________________________________________________________Better Solution_________________________________________________________
class Solution {
    /*
        Using a int[] for dp.
        dp[i] = 0, invalid number
        dp[i] = 1, valid and same number
        dp[i] = 2, valid and different number
    */
    // seperate num by last digit and prev digits
    // compare a number is balid only when one of them is 2 and the other one larger than 0
    public int rotatedDigits(int N) {
        int[] dp = new int[N + 1];
        int count = 0;
        for(int i = 0; i <= N; i++){
            if(i < 10){
                if(i == 0 || i == 1 || i == 8) dp[i] = 1;
                else if(i == 2 || i == 5 || i == 6 || i == 9){
                    dp[i] = 2;
                    count++;
                }
            } else {
                int a = dp[i / 10], b = dp[i % 10];
                if(a == 1 && b == 1) dp[i] = 1;
                else if(a >= 1 && b >= 1){
                    dp[i] = 2;
                    count++;
                }
            }
        }
        return count;
    }
}
_____________________________________________________________________My Solution_________________________________________________________
class Solution {
    //check every number one by one
    public int rotatedDigits(int N) {
        int ans = 0;
        while(N > 1){
            if(check(N)){
                ++ans;
            }
            --N;
        }
        return ans;
    }
    
    private boolean check(int n){
        boolean isValid = false;
        while(n > 0){
            int d = n % 10;
            n /= 10;
            if(d == 3 || d == 4 || d == 7){
                return false;
            }
            if(d == 2 || d == 5 || d == 6 || d == 9){
                isValid = true;
            }  
        }
        return isValid;
    }
}

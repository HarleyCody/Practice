_____________________________________________________________Best Solution_______________________________________________________________
class Solution {
    public boolean isValidPalindrome(String s, int k) {
        // find longest palindrom substring, get length of it
        // dp only records the num of chars made up to a palindrome string with range i to j
        // dp[i][j] == dp[i + 1][j - 1] + 2 iff arr[i] == arr[j];
        // only related to previous one status, so 1d dp is enough
        if (s.length() - 1 <= k) return true;
        int n = s.length();
        char[] arr = s.toCharArray();
        int[] dp = new int[n];
        // 1 cuz single char is length 1 palindrome
        Arrays.fill(dp, 1);
        int max = 0;
        for (int i = 0; i < n; i++) {
            int curMax = 0;
            for (int j = i - 1; j >= 0; j--) {//find max seq start with j, end with i, put into dp[j]
                // prev = dp[j][i - 1];
                int prev = dp[j];
                if (arr[i] == arr[j]) {
                    //dp[j][i] = dp[j + 1][i - 1]
                    dp[j] = curMax + 2; //only need update dp[j] if start and end matchs
                }
                //cur updated here would be the longest palidrom in j + 1 and i - 1
                curMax = Math.max(curMax, prev);
            }
        }
        for (int i = 0; i < n; i++) max = Math.max(max, dp[i]);
        return n - max <= k;
    }
}
_______________________________________________________________My Solution_______________________________________________________________
class Solution {
    // recursion and memoization
    // dp[i][j] record highest times left for i , j to be palindrome
    // no greater than dp[i][j] means it will not be true;
    int[][] dp;
    public boolean isValidPalindrome(String s, int k) {
        char[] chs = s.toCharArray();
        int len = chs.length;
        dp = new int[len][len];
        return check(chs, 0, len - 1, k);
    }
    
    private boolean check(char[] chs, int l, int r, int times){
        if(dp[l][r] != 0 && times <= dp[l][r]){
            return false;
        }
        int ogL = l, ogR = r;
        while(l < r && chs[l] == chs[r]){
            ++l;
            --r;
        }
        if(l >= r){
            return true;
        }
        if(times == 0){
            dp[l][r] = 0;
            return false;
        }
        if(check(chs, l + 1, r, times - 1)){
            return true;
        }else if(check(chs, l , r - 1, times - 1)){
            return true;
        }else{
            dp[ogL][ogR] = Math.max(times, dp[ogL][ogR]);
            return false;
        }
    }
}

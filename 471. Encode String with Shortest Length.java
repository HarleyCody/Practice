//Best Solution: DP. Dp[i][j] means shortest encoded string
//                   start with len 1 to find the max repeated string and see if repeat is shorter than before every time find a shorter string update the previous
//                   increase the length to half of current string
class Solution {
    public String encode(String s) {
        int n = s.length();
        String[][] dp = new String[n][n];
        for (int j = 0; j < n; ++j) {
            dp[j][j] = s.substring(j, j+1);
            for (int p = 0; p < j; ++p) {
                dp[p][j] = dp[p][j - 1] + dp[j][j];
            }
            
            for (int i = j - 1; i + 1 >= j / 2; --i) {
                String sub = s.substring(i + 1, j + 1); // s[i+1..j]
                for (int k = i - (j - i) + 1; k >= 0 && sub.equals(s.substring(k, k + j - i)); k -= j - i) {
                    String str = Integer.toString((j + 1 - k) / (j - i)) + "[" + dp[i+1][j] + "]";
                    if (str.length() < dp[k][j].length()) {
                        dp[k][j] = str;
                        for (int p = 0; p < k; ++p) {
                            if (dp[p][k - 1].length() + str.length() < dp[p][j].length()) {
                                dp[p][j] = dp[p][k - 1] + str;
                            }
                        }
                     }
                }
            }
        }
        return dp[0][n-1];
    }
}
//Referenced Solution: DFS with memo, try to find repeate that can exhaust current string, and record the minimal length;
//                                    try to find from left to right without repeate
class Solution {
    Map<String, String> memo = new HashMap();
    public String encode(String s) {
        if(s.length() < 5){
            return s;
        }
        if(memo.containsKey(s)){
            return memo.get(s);
        }
        
        int len = s.length();
        StringBuilder ans = new StringBuilder(s);
        StringBuilder sub = new StringBuilder();
        for(int i = 0; i <= len / 2; ++i){
            sub.append(s.charAt(i));
            if(len % (i + 1) != 0) continue;
            
            if(isRepeated(s, sub.toString())){
                int num = len / (i + 1);
                sub = new StringBuilder(encode(sub.toString()));
                int subNum = getNumLength(num);
                if(sub.length() + subNum + 2< ans.length()){
                    ans = new StringBuilder();
                    ans.append(num);
                    ans.append('[');
                    ans.append(sub);
                    ans.append(']');
                }
            }
        }
        
        for(int i = 1; i < len; ++i){
            StringBuilder left = new StringBuilder(encode(s.substring(0, i)));
            left.append(encode(s.substring(i)));
            if(left.length() < ans.length()){
                ans = new StringBuilder(left);
            }
        }
        memo.put(s, ans.toString());
        return ans.toString();
    }
    
    private boolean isRepeated(String tar, String src){
        int tLen = tar.length();
        int sLen = src.length();
        for(int i = 0; i < tLen; ++i){
            if(tar.charAt(i) != src.charAt(i % sLen)) return false;
        }
        return true;
    }
    
    private int getNumLength(int n){
        if(n < 10){
            return 1;
        }else if(n < 100){
            return 2;
        }
        return 3;
    }
}

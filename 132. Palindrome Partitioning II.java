_____________________________________________________________________________Best Solution____________________________________________________________________________________
O(n ^2)
//dp[i] min cut at string end at i
//expand palindrom from every middle to both side to update mincut;
class Solution {
    public int minCut(String s) {
        // validate input
        if (s == null || s.length() <= 1) {
            return 0;
        }
        // dp
        int N = s.length();
        int[] dp = IntStream.range(0, N).toArray(); // initial value: dp[i] = i

        for (int mid = 1; mid <  N; mid++) { // iterate through all chars as mid point of palindrome
            // CASE 1. odd len: center is at index mid, expand on both sides
            for (int start = mid, end = mid; start >= 0 && end < N && s.charAt(start) == s.charAt(end); start--, end++) {
                int newCutAtEnd = (start == 0) ? 0 : dp[start - 1] + 1;
                dp[end] = Math.min(dp[end], newCutAtEnd);
            }
            // CASE 2: even len: center is between [mid-1,mid], expand on both sides
            for (int start = mid - 1, end = mid; start >= 0 && end < N && s.charAt(start) == s.charAt(end); start--, end++) {
                int newCutAtEnd = (start == 0) ? 0 : dp[start - 1] + 1;
                dp[end] = Math.min(dp[end], newCutAtEnd);
            }
        }
        return dp[N - 1];
    }
}
___________________________________________________________________My Solution(TLE)__________________________________________________________________________________________
//O(n^3)
class Solution {
    HashMap<String, Integer> mem = new HashMap();
    public int minCut(String s) {
        return cut(s);
    }
    
    private int cut(String s){
        int len = s.length();
        if(len == 1 || isPalindrom(s)){
            return 0;
        }
        if(mem.containsKey(s)){
            return mem.get(s);
        }
        int ans = len, curCut = len;
        for(int i = 1; i < len; ++i){
            curCut = 1 + cut(s.substring(0, i)) + cut(s.substring(i));
            ans = Math.min(ans, curCut);
        }
        
        mem.put(s, ans);
        return ans;
    }
    private boolean isPalindrom(String s){
        int l = 0, r = s.length() - 1;
        while(l < r){
            if(s.charAt(l) != s.charAt(r)){
                return false;
            }
            ++l;
            --r;
        }
        return true;
    }
}

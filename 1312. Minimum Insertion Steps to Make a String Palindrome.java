_______________________________________________________________________________dp Solution________________________________________________________________________
class Solution {
    // dp longest palindrom subsequence
    // mbadm longest will be m a m,
    public int minInsertions(String s) {
        return s.length() - lengthOfLPS(s);
    }
    
    private int lengthOfLPS(String s) {
        int L = s.length();
        if (L < 2) {
            return L;
        }
        char[] ca = s.toCharArray();
        int[] curr = new int[L];
        int[] prev = new int[L];
        for (int i = L - 1; i >= 0; --i) {
            curr[i] = 1;
            for (int j = i + 1; j < L; ++j) {
                if (ca[i] == ca[j]) {
                    // dp[i][j] = 2 + dp[i + 1][j - 1];
                    curr[j] = 2 + prev[j - 1];
                } else {
                    // dp[i][j] = max(dp[i + 1][j], dp[i][j - 1]);
                    curr[j] = Math.max(prev[j], curr[j - 1]);
                }
            }
            int[] tmp = prev;
            prev = curr;
            curr = tmp;
        }
        return prev[L-1];
    }
}
_______________________________________________________________________________dp Solution________________________________________________________________________
class Solution {
    // find length of longest common string and length - common string, notice dp will go from one end to the other end, so it calcualte common string between s and s.reverse()
    // not by left half and right half
    // dp[i][j] is longest common string between s and s.reverse()
    public int minInsertions(String s) {
        int n = s.length();
        int[][] dp = new int[n+1][n+1];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                dp[i + 1][j + 1] = s.charAt(i) == s.charAt(n - 1 - j) ? dp[i][j] + 1 : Math.max(dp[i][j + 1], dp[i + 1][j]);
        return n - dp[n][n];
    }
}
________________________________________________________________________________My Solution________________________________________________________________________
class Solution {
    // mutate string from left or right by 1
    // use mem to record min of status when idx = i * len + j, where left at i right at j
    int[] mem;
    public int minInsertions(String s) {
        int len = s.length();
        mem = new int[len * len];
        return mutate(s.toCharArray(), 0, len - 1);
    }
    
    private int mutate(char[] chs, int l, int r){
        while(l < r && chs[l] == chs[r]){
            ++l;
            --r;
        }
        
        if(l >= r){
            return 0;
        }
        int idx = l * chs.length + r;
        if(mem[idx] != 0){
            return mem[idx];
        }
        int left = 1 + mutate(chs, l + 1, r);
        int right = 1 + mutate(chs, l, r - 1);
        mem[idx] = Math.min(left, right);
        return mem[idx];
    }
}

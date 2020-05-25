____________________________________________Best Solution(Constant Space)O(M) worstO(M * N)______________________________________________
class Solution {
    // record pos of star make it match one by one more in string s to reach end
    public boolean isMatch(String str, String pattern) {
        int s = 0, p = 0, match = 0, starIdx = -1;            
        while (s < str.length()){
            // advancing both pointers
            if (p < pattern.length()  && (pattern.charAt(p) == '?' || str.charAt(s) == pattern.charAt(p))){
                s++;
                p++;
            }
            // * found, only advancing pattern pointer
            else if (p < pattern.length() && pattern.charAt(p) == '*'){
                starIdx = p;
                match = s;
                p++;
            }
           // last pattern pointer was *, advancing string pointer
            else if (starIdx != -1){
                p = starIdx + 1;
                match++;
                s = match;
            }
           //current pattern pointer is not star, last patter pointer was not *
          //characters do not match
            else return false;
        }
        
        //check for remaining characters in pattern
        while (p < pattern.length() && pattern.charAt(p) == '*')
            p++;
        
        return p == pattern.length();
    }
}
_______________________________________________________________My Solution_______________________________________________________________
class Solution {
    // matching with mem, if p[i] == s[i] || p[i] == '?' is matching, ++i;
    // match recursively, ATTENTION, record og idx for mem to set, otherwise, idx will change during the matching, mem will be futile
    // find * at pIdx start next match when p[pIdx + 1] == s[sIdx] 
    boolean[][] dp;
    public boolean isMatch(String s, String p) {
        dp = new boolean[p.length() + 1][s.length() + 1];
        return isMatch(s.toCharArray(), 0, s.length(), p.toCharArray(), 0, p.length());
    }
    
    private boolean isMatch(char[] s, int sIdx, int sLen, char[] p, int pIdx, int pLen){
        if(dp[pIdx][sIdx]){
            return false;
        }
        int ogSIdx = sIdx;
        int ogPIdx = pIdx;
        while(sIdx < sLen && pIdx < pLen && (s[sIdx] == p[pIdx] || p[pIdx] == '?')){
            ++sIdx;
            ++pIdx;
        }
        // skip consecutive *
        while(pIdx < pLen && p[pIdx] == '*'){
            ++pIdx;
        }
        if(sIdx == sLen && pIdx == pLen){
            return true;
        }else if(pIdx == p.length && pIdx > 0 && p[pIdx - 1] == '*'){
            return true;
        }else if(pIdx > 0 && p[pIdx - 1] == '*'){
            char tar = p[pIdx];
            while(sIdx < sLen){
                if((p[pIdx] == s[sIdx] || p[pIdx] == '?') &&
                   isMatch(s, sIdx + 1, sLen, p, pIdx + 1, pLen)){
                    return true;
                }
                ++sIdx;
            }
        }
        dp[ogPIdx][ogSIdx] = true;
        return false;
    }
}

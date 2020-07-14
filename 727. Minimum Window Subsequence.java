_________________________________________________________________________Best Solution(AC)__________________________________________________________________________________
class Solution {
    // find char by char;
    // record  how many char in tar has been match;
    // when all match, goes back to find start point;
    // update length;
    public String minWindow(String S, String T) {
        char[] s = S.toCharArray(), t = T.toCharArray();
        int sindex = 0, tindex = 0, slen = s.length, tlen = t.length, start = -1, len = slen;
        while(sindex < slen) {
            if(s[sindex] == t[tindex]) {
                if(++tindex == tlen) {
                    //check feasibility from left to right of T
                    int end = sindex+1;
                    //check optimization from right to left of T
                    // find start
                    while(--tindex >= 0) {
                        // as tindex == tlen, before --tindex and sindex should compare with tlen - 1
                        while(s[sindex--] != t[tindex]);
                    }
                    // go back to the first point they met
                    ++sindex;
                    ++tindex;
                    //record the current smallest candidate
                    if(end - sindex < len) {
                        len = end - sindex;
                        start = sindex;
                    }
                }
            }
            ++sindex;
        }
        return start == -1? "" : S.substring(start, start + len);
    }
}
___________________________________________________________________________My Solution(AC)__________________________________________________________________________________
class Solution {
    //Check directly, find the minlen and record the start and end
    public String minWindow(String S, String T) {
        int sLen = S.length(), tLen = T.length();
        
        int ansStart = -1, ansEnd = S.length();
        for(int i = 0; i <= sLen - tLen; ++i){
            if(S.charAt(i) != T.charAt(0)){
                continue;
            }
            int rlt = check(S, T, tLen, i); 
            if(rlt > 0){
                if(ansEnd - ansStart > rlt - i){
                    ansEnd = rlt;
                    ansStart = i;
                }
            }else{
                break;
            }
        }
        if(ansStart == -1){
            return "";
        }
        return S.substring(ansStart, ansEnd);
    }
    
    private int check(String src, String tar, int tLen, int idx){
        int ans = -1;
        int prev = idx + 1;
        for(int i = 1; i < tLen; ++i){
            int cur = src.indexOf(tar.charAt(i), prev);
            if(cur < 0){
                return -1;
            }
            prev = cur + 1;
        }
        return prev;
    }
}
___________________________________________________________________________My Solution(TLE)___________________________________________________________________________________
class Solution {
    // Binary serach one by one
    public String minWindow(String S, String T) {
        if(S.contains(T)){
            return T;
        }
        
        int start = -1, len = -1;
        int l = T.length(), r = S.length();
        
        while(l < r){
            int mid = (l + r) / 2;
            int rlt = check(S, T, mid);
            if(rlt >= 0){
                start = rlt;
                len = mid;
                r = mid;
            }else{
                l = mid + 1;
            }
        }
        if(start == -1){
            return "";
        }
        
        return S.substring(start, start + len);
    }
    
    private int check(String src, String tar, int len){
        for(int i = 0; i <= src.length() - len; ++i){
            if(src.charAt(i) != tar.charAt(0) && i != src.length() - len){
                continue;
            }
            
            if(validate(src.substring(i , i + len), tar)){
                return i;
            }
        }
        return -1;
    }
    
    private boolean validate(String src, String tar){
        int srcLen = src.length(), tarLen = tar.length();
        int prev = -1;
        for(int i = 0; i < tarLen; ++i){
            int cur = src.indexOf(tar.charAt(i), prev);
            if(cur == -1){
                return false;
            }
            prev = cur + 1;
        }
        return true;
    }
}

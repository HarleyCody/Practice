___________________________________________________________Best Solution(On2)_______________________________________________________
class Solution {
    public int countSubstrings(String s) {
        int count = 0;
        for(int i = 0; i < s.length(); ++i){
            count += palindromic(s, i);
        }
        return count;
    }
    private int palindromic(String s, int i){
        // single char is palindromic
        int count = 1;
        // i is centre
        count += subPalinString(s, i - 1, i + 1);
        // i and i + 1 is centre
        count += subPalinString(s, i, i + 1);
        return count;
    }
    private int subPalinString(String s, int start, int end){
        int count = 0;
        //expand from centre to left and right simultaneously
        while(0 <= start && end < s.length() && s.charAt(start--) == s.charAt(end++)){
            ++count;
        }
        return count;
    }
}
____________________________________________________________My Solution(On3)________________________________________________________
class Solution {
    public int countSubstrings(String s) {
        int len = s.length();
        if(len == 0) return len;
        int ans = len;
        // get substring in the S
        for(int i = 0; i < s.length(); ++i){
            for(int j = i + 2; j <= s.length(); ++j){
                if(s.charAt(i) == s.charAt(j - 1) && isPalindromic(s.substring(i,j))){
                    ++ans;
                }
            }
        }
        return ans;
    }
    // check if its palindromic substring
    private boolean isPalindromic(String s){
        int i = 0, j = s.length() - 1;
        while(i < j){
            if(s.charAt(i++) != s. charAt(j--))return false;
        }
        return true;
    }
}

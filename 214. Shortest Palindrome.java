class Solution {
//narrow down to find mid point (longest palindrome substring start with 0) pre + mid + rest == ans;
// pre = reverse(rest);
// eg "aacecaaa" pre = a: mid = aacecaa + rest = a  ans : a aacecaa a 
    public String shortestPalindrome(String s) {
        int j = 0;
        //magic: largest palindrome possible in the given string.
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == s.charAt(j)) ++j;
        }
        // mid point is not larger than j/2; so string after j need to be add reversely as pre and add to tail. find mid point at 0, j;
        
        // base case
        if (j == s.length()) { return s; }
        String suffix = s.substring(j);
        return new StringBuffer(suffix).reverse().toString() + shortestPalindrome(s.substring(0, j)) + suffix;
    }
}
_________________________________________________________My Solution__________________________________________________________
// find longest palindrom start with from s[0], then add and reverse the rest to previous
class Solution {
    public String shortestPalindrome(String s) {
        if(s.length() < 2) return s;
        int count = 0, decrease = 0, mid = 0, length = 0;
        char[] c = s.toCharArray();
        int j = s.length() - 1;
        while(0 < j){
            if(c[j] == c[0] && isPalindrome(c, j)){
                break;
            }
            --j;
        }
        StringBuilder pre = new StringBuilder(s.substring(j + 1)).reverse();
        return pre.toString() + s;
    }
    private boolean isPalindrome(char[] c, int end){
        int start = 0;
        while(start < end && c[start] == c[end]){
            ++start;
            --end;
        }
        return start >= end;
    }
}

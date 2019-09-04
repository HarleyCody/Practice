/* Comparting char from two sides
if char i != char j, then comparing i + 1 with j and i with j - 1(skip one char). 
return result of the second comparing.
*/
class Solution {
    public boolean validPalindrome(String s) {
        if(s.length() <= 2) return true;
        int l = 0, r= s.length() - 1;
        while(s.charAt(l) == s.charAt(r) && l < r){
            l++;
            r--;
        }
        if(l == r || l == r + 1) return true;
        return (isPalinSub(s, l + 1, r) || isPalinSub(s, l, r - 1));
    }
    private boolean isPalinSub(String s, int l, int r){
        while(l < r){
            if(s.charAt(l++) != s.charAt(r--)){
                return false;
            }
        }
        return true;
    }
}

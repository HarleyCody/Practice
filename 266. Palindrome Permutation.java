//My Solution: Only maximal 1 character can have odd freq to be palindrome
class Solution {
    public boolean canPermutePalindrome(String s) {
        int[] freq = new int[26];
        for(char c : s.toCharArray()){
            ++freq[c - 'a'];
        }
        int oddCount = 0;
        for(int i = 0; i < 26; ++i){
            oddCount += freq[i] % 2;
        }
        
        return oddCount < 2;
    }
}

___________________________________________________________________My Solution___________________________________________________________
class Solution {
    // record freq of T
    // traverse S from left to right to guaratee the order;
    // write one by one into dst.
    // put leftover char into dst 
    public String customSortString(String S, String T) {
        char[] com = S.toCharArray();
        char[] src = T.toCharArray();
        char[] dst = new char[src.length];
        int[] srcFreq = new int[26];
        
        for(char c : src){
            ++srcFreq[c - 'a'];
        }
        
        int dstIdx = 0;
        for(int i = 0; i < com.length; ++i){
            char curChar = com[i];
            while(srcFreq[curChar - 'a']-- > 0){
               dst[dstIdx++] = curChar;
            }
        }
        for(int i = 0; i < 26; ++i){
            while(srcFreq[i]-- > 0){
               dst[dstIdx++] = (char)(i + 'a');
            }
        }
        return new String(dst);
    }
}

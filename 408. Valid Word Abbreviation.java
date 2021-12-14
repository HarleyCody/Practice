//My Solution: Check one by one, accumualte and move pointer in word to match character
//  False for: character not match, one of is end but the other is not
//             at the last pointer to its len is not same
//             leading '0'
class Solution {
    public boolean validWordAbbreviation(String word, String abbr) {
        char[] aChs = abbr.toCharArray();
        char[] wChs = word.toCharArray();
        
        int aIdx = 0;
        int wIdx = 0;
        int aLen = aChs.length;
        int wLen = wChs.length;
        while(wIdx < wLen && aIdx < aLen){
            int num = 0;
            if(aIdx == aLen || aChs[aIdx] == '0') return false;
            
            while(aIdx < aLen && '0' <= aChs[aIdx] && aChs[aIdx] <= '9'){
                num = num * 10 + (aChs[aIdx++] - '0');
            }
            wIdx += num;
            if(wIdx > wLen) return false;
            if(wIdx < wLen && aIdx < aLen && aChs[aIdx] != wChs[wIdx]){
                return false;
            }
            
            ++wIdx;
            ++aIdx;
        }
        return aIdx - aLen == wIdx - wLen;
    }
}

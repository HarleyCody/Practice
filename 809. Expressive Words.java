______________________________________________________________________________Best Solution(same speed but clearer)______________________________________________________________________________________
class Solution {
    public int expressiveWords(String S, String[] words) {
        char[] t = S.toCharArray();
        int len = t.length;
        
        int res = 0;
        
        for (String word : words) {
            if (word.length() <= t.length && isExtensible(t, word.toCharArray())) res++;
        }
        
        return res;
    }
    
    private boolean isExtensible(char[] t, char[] s) {
        int i = 0, j = 0;
        int startIndex = 0;
        
        while (i < t.length) {
            if (j == s.length) {
                int k = i;
                while (k < t.length) {
                    if (t[i - 1] != t[k]) return false;
                    k++;
                }
                
                if (k - startIndex < 3) return false;
                i = k;
            } else {
                if (i == 0 || t[i - 1] != t[i]) startIndex = i;
                
                if (t[i] == s[j]) {
                    i++;
                    j++;
                } else {
                    if (i == startIndex) return false;
                    int k = i + 1;
                    while (k < t.length && t[k] == t[i]) k++;
                    if (k - startIndex < 3) return false;
                    i = k;
                }
            }
        }
        
        return j == s.length;
    }
}
________________________________________________________________________________My Solution______________________________________________________________________________________
class Solution {
    // get String freq and seq
    // compare one by one, 
    // compare char one by one: seq must be same, num of letters must be same, freq either be same or S[i] >= 3 
    public int expressiveWords(String S, String[] words) {
        int len = S.length();
        if(len == 0){
            return 0;
        }
        char[] chs = new char[len];
        int[] freq = new int[len];
        freq[0] = 1; chs[0] = S.charAt(0);
        
        int idx = 0;
        for(int i = 0; i < len - 1; ++i){
            if(S.charAt(i) != S.charAt(i + 1)){
                freq[++idx] = 1;
                chs[idx] = S.charAt(i + 1);
            }else{
                ++freq[idx];
            }
        }
        
        int ans = 0;
        for(String word : words){
            char[] wChars = word.toCharArray();
            int i = 0, w = 0, wLen = wChars.length;
            while(w < wLen && i < idx + 1){
                if(chs[i] != wChars[w]){
                    break;
                }
                
                // equal char collect wChars
                int num = 1;
                while(w + 1 < wLen && wChars[w] == wChars[w + 1]){
                    ++num;
                    ++w;
                }
                if(chs[i] != wChars[w] || num > freq[i] ||
                   num < freq[i] && freq[i] < 3){
                    break;
                }
                ++w;
                ++i;
            }
            if(w == wLen && i == idx + 1){
                ++ans;
            }
        }
        return ans;
    }
}

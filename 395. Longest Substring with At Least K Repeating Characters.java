________________________________________________________________Best Solution_____________________________________________________________
class Solution {
    // find longestSubsring recursivly
    // any two invalid char(freq < k) can form a subString
    // find valid string between two invalid char recursivly;
    public int longestSubstring(String s, int k) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int n = s.length();
        if (k <= 1) {
            return n;
        }
        int counter[] = new int[26];
        boolean valid[] = new boolean[26];

        char[] ss = s.toCharArray();
        for (int i = 0; i < n; i++) {
            ++counter[ss[i] - 'a'];
        }
        boolean fullValid = true;
        for (int i = 0; i < 26; i++) {
            if (counter[i] > 0 && counter[i] < k) {
                valid[i] = false;
                fullValid = false;
            } else {
                valid[i] = true;
            }
        }
        if (fullValid) {
            return s.length();
        }
        int max = 0, start = 0;
        for (int i = 0; i <= n; i++) {
            if (i == n || !valid[ss[i] - 'a']) {
                max = Math.max(max, longestSubstring(s.substring(start, i), k));
                start = i + 1;
            }
        }
        return max;
    }
}
_________________________________________________________________My Solution_____________________________________________________________
class Solution {
    // start from valid i(ie freq[i] >= k) expand ans to end or next invalid i
    public int longestSubstring(String s, int k) {
        
        char[] chs = s.toCharArray();
        int len = chs.length;
        
        int[] freq = new int[26];
        
        //record invalid point
        for(int i = 0; i < len; ++i){
            int idx = chs[i] - 'a';
            ++freq[idx];
        }
        
        int ans = 0;
        for(int i = 0; i < len; ++i){
            int c = chs[i] - 'a';
            if(freq[c] < k){
                continue;
            }
            
            // start from i expend ans string
            // n record number of char whose freq is less than k in string
            int n = 0, j = i;
            int[] subFreq = new int[26];
            while(j < len && freq[chs[j] - 'a'] >= k){
                int idx = chs[j] - 'a';
                // cur char is new char
                if(subFreq[idx] == 0){
                    ++n;
                }
                ++subFreq[idx];
                if(subFreq[idx] >= k){
                    //cur char just turn valid
                    if(subFreq[idx] == k){
                        --n;
                    }
                    if(n == 0){
                        ans = Math.max(ans, j - i + 1);
                    }
                }
                ++j;
            }
            // update invalid point for later expand
            --freq[c];
        }
        return ans;
    }
}

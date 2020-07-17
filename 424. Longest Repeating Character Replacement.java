_______________________________________________________________________________Cleaer Solution________________________________________________________________________
class Solution {
    // only construct ans String with most freq char
    public int characterReplacement(String s, int k) {
        int[] map = new int[256];
        int left = 0;
        int right = 0;
        int max = 0;
        int mostFreq = 0;
        char[] cArr = s.toCharArray();
        
        while (right < cArr.length) {
            map[cArr[right]]++;
            mostFreq = Math.max(mostFreq, map[cArr[right]]);
            while (right - left + 1 - mostFreq > k) {
                map[cArr[left++]]--;
            }
            max = Math.max(max, right - left + 1);
            right++;
        }
        return max;
    }
}
_________________________________________________________________________________My Solution________________________________________________________________________
class Solution {
    //Sliding window, always assures max Lengh;
    
    //check start from every char, if r == len, curLen = Math.min(k + r - l, len);
    public int characterReplacement(String s, int k) {
        int len = s.length();
        if(len < 2){
            return len;
        }
        
        int l = 0, r = 0;
        int[] freq = new int[26];
        char[] chs = s.toCharArray();
        
        int ans = 1, og = k;
        while(l < len){
            while(r < len && k >= 0){
                int cIdx = chs[r] - 'A';
                if(chs[r] != chs[l]){
                    --k;
                }
                ++freq[cIdx];
                ++r;
            }
            if(r == len){
                ans = Math.max(ans, Math.min(r - l + k, len));
            }else{
                ans = Math.max(ans, r - l - 1);
            }
            --freq[chs[l] - 'A'];
            ++l;
            if(l < len && chs[l] != chs[l - 1]){
                k = og - (r - l) + freq[chs[l] - 'A'];
            }
        }
        return ans;
    }
}

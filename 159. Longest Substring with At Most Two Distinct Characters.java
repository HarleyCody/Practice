class Solution {
    //Sliding window to count freq and num of unique char
    //num reach to 3 update ans with r - l;
    //as r is at point with 3 unique char, so len = r - 1 - l + 1 = r - l;
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        char[] freq = new char[256];
        char[] chs = s.toCharArray();
        
        int len = s.length();
        int r = 0, n = 0, ans = 0;
        for(int l = 0; l <= r; ++l){
            while(r < len && n < 3){
                ans = Math.max(ans, r - l);
                char c = chs[r++];
                if(freq[c] == 0){
                    ++n;
                }
                ++freq[c];
            }
            if(n < 3){
                ans = Math.max(ans, r - l);
            }
            if(r == len){
                break;
            }
            --freq[chs[l]];
            if(freq[chs[l]] == 0){
                --n;
            }
        }
        return ans;
    }
}

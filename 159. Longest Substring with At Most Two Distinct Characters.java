___________________________________________________________________________Best Solution_____________________________________________________________________________
// Instead of using number == 2 to record, use two chars to assure the char number is <= 2;
// if one of char match s.charAt(i) continue to find the third one
// if s.charAt(i) is third one, the previous one of third one is first one, third one will be second one
// go from right to left to find the pos of the first one(l is first one, r is the third one)
class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int n = s.length();
        
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        
        char[] sAsArr = s.toCharArray();
        
        int i = 0, j = 1;
        char x = sAsArr[i];
        char y = sAsArr[j];
        int max = 0;
        
        while (x == y && j < n) {
            y = sAsArr[j++];
        }
        
        max = Math.max(max, j - i);

        while (j < n) {
            if (sAsArr[j] == x || sAsArr[j] == y) {
                j++;
            } else {
                i = j - 1;
                x = sAsArr[i];
                while (i >= 0 && x == sAsArr[i - 1]) {
                    i--;
                }
                y = sAsArr[j++];
            }
            max = Math.max(max, j - i);
        }
                
        return max;
    }
}
_____________________________________________________________________________My Solution_____________________________________________________________________________
// two pointers
// right ends at 3, if the last one is valid, r should be at len + 1 instead of len;
// when r reach the end, do not need to calcualte left anymore, just breal;
class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int[] freq = new int[256];
        char[] chs = s.toCharArray();
        
        int len = chs.length, ans = 0;
        int l = 0, r = 0, num = 0;
        while(r < len){
            while(r < len && num < 3){
                ans = Math.max(ans, r - l);
                ++freq[chs[r]];
                num += freq[chs[r]] == 1 ? 1 : 0;
                ++r;
            }
            ans = num < 3 ? Math.max(ans, r - l) : ans;
            // key to be faster
            if(r == len){
                break;
            }
            --freq[chs[l]];
            num -= freq[chs[l]] == 0 ? 1 : 0;
            ++l;
        }
        
        return ans;
    }
}
_____________________________________________________________________________My Solution_____________________________________________________________________________
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

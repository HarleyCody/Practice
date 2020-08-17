____________________________________________________________________________________My Solution______________________________________________________________________
// two pinters, find 'I' and fill from left to right with descending order
class Solution {
    public int[] findPermutation(String s) {
        char[] chs = s.toCharArray();
        int len = chs.length, n = len + 1;
        int low = 1, high = 0, l = 0, r = 0;
        
        int[] ans = new int[n];
        while(r < len){
            while(m < len && chs[m] != 'I'){
                ++m;
            }
            high = low + m - l;
            low += m - l + 1;
            while(l <= m){
                ans[l++] = high--;
            }
            ++m;
        }
        if(ans[n - 1] == 0){
            ans[n - 1] = low;
        }
        return ans;
    }
}

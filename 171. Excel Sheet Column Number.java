class Solution {
    // decimalism to 26lism
    public int titleToNumber(String s) {
        char[] chs = s.toCharArray();
        int ans = 0, idx = 0;
        while(idx < chs.length){
            ans *= 26;
            ans += (chs[idx++] - 'A' + 1);
        }
        return ans;
    }
}

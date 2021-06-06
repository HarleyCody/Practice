//Append + flip = flip + append so append first
// compare s with its model(01... and 10...)

// sliding window to get the min flip
// append s to s, sliding window will cover all append string
class Solution {
    public int minFlips(String s) {
        int len = s.length();
        int ans0 = 0, ans1 = 0, ans = Integer.MAX_VALUE;
        char c0, c1;
        boolean isOdd = false;
        for(int i = 0; i < 2 * len; ++i){
            isOdd = i % 2 != 0;
            c0 = '0'; c1 = '1';
            if(isOdd){
                c0 = '1';
                c1 = '0';
            }
            if(c0 != s.charAt(i)) ++ans0;
            if(c1 != s.charAt(i)) ++ans1;
            
            if(i >= len){
                if(len % 2 != 0){
                    c0 = isOdd? '0' : '1';
                    c1 = isOdd? '1' : '0';
                }
                if(c0 != s.charAt(i - len)) --ans0;
                if(c1 != s.charAt(i - len)) --ans1;
            }
            if(i >= len - 1){
                ans = Math.min(Math.min(ans0, ans1), ans);
            }
        }
        return ans;
    }
}

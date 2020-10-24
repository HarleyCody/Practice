______________________________________________________________________________My Solution______________________________________________________________________________
// two pointers, power for score or score for power
// Greedy, get as more score as it can and use score for largest power
class Solution {
    public int bagOfTokensScore(int[] tokens, int P) {
        int len = tokens.length;
        
        Arrays.sort(tokens);
        
        int l = 0, r = len - 1;
        int curP = P, score = 0;
        int ans = 0;
        
        while(l <= r){
            while(l <= r && curP >= tokens[l]){
                curP -= tokens[l++];
                ++score;
            }
            ans = Math.max(ans, score);
            if(l == r || score <= 0){
                break;
            }
            
            curP += tokens[r--];
            --score;
        }
        
        return ans;
    }
}

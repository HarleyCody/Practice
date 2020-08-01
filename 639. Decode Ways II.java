___________________________________________________________________________My Improved DP Solution________________________________________________________________________________
// As only depend on two previous status just use variable to update every round;
class Solution {
    int mod = (int)1e9 + 7;
    public int numDecodings(String s) {
        
        char[] chs = s.toCharArray();
        int len = chs.length;
        if(chs[0] == '0'){
            return 0;
        }
        
        long prev = chs[0] == '*' ? 9L : 1L;
        long pPrev = 1L, cur = prev;
        for(int i = 1; i < len; ++i){
            
            if(chs[i - 1] == '*'){
                if(chs[i] == '*'){
                    cur = 9 * prev + 15 * pPrev;
                }else if(chs[i] == '0'){
                    cur = 2 * pPrev;
                }else if(chs[i] > '6'){
                    cur = prev + pPrev;
                }else{
                    cur = prev + 2 * pPrev;
                }
            }else if(chs[i - 1] == '1'){
                cur = prev + pPrev;
                if(chs[i] == '*'){
                    //(pPrev + prev) * 9;
                    cur = 9 * pPrev + 9 * prev;
                }else if(chs[i] == '0'){
                    cur = pPrev;
                }
            }else if(chs[i - 1] == '2'){
                if(chs[i] == '*'){
                    cur = pPrev * 6 + prev * 9;
                }else if(chs[i] == '0'){
                    cur = pPrev;
                }else if(chs[i] < '7'){
                    cur = prev + pPrev;
                }
            }else{
                if(chs[i] == '*'){
                    cur *= 9;
                }else if(chs[i] == '0'){
                    cur = 0;
                }
            }
            cur %= mod;
            pPrev = prev;
            prev = cur;
        }
        return (int)(cur);
    }
}
________________________________________________________________________________My dp Solution________________________________________________________________________________
class Solution {
    //dp[i] = dp[i - 1] + dp[i - 2];
    //current depend or independ on previous
    //if prev > 2 independ, same as previous
    //else cur = prev + pPrev;
    //special case for *, it can be any number from 1 - 9;
    int mod = (int)1e9 + 7;
    public int numDecodings(String s) {
        
        char[] chs = s.toCharArray();
        int len = chs.length;
        if(chs[0] == '0'){
            return 0;
        }
        
        long[] dp = new long[len];
        dp[0] = chs[0] == '*' ? 9 : 1;
        for(int i = 1; i < len; ++i){
            long prev = dp[i - 1] % mod, pPrev = i < 2 ? 1L : dp[i - 2] % mod;
            dp[i] = prev;
            
            if(chs[i - 1] == '*'){
                if(chs[i] == '*'){
                    dp[i] = 9 * prev + 15 * pPrev;
                }else if(chs[i] == '0'){
                    dp[i] = 2 * pPrev;
                }else if(chs[i] > '6'){
                    dp[i] = prev + pPrev;
                }else{
                    dp[i] = prev + 2 * pPrev;
                }
            }else if(chs[i - 1] == '1'){
                dp[i] = prev + pPrev;
                if(chs[i] == '*'){
                    //(pPrev + prev) * 9;
                    dp[i] = 9 * pPrev + 9 * prev;
                }else if(chs[i] == '0'){
                    dp[i] = pPrev;
                }
            }else if(chs[i - 1] == '2'){
                if(chs[i] == '*'){
                    dp[i] = pPrev * 6 + prev * 9;
                }else if(chs[i] == '0'){
                    dp[i] = pPrev;
                }else if(chs[i] < '7'){
                    dp[i] = prev + pPrev;
                }
            }else{
                if(chs[i] == '*'){
                    dp[i] = dp[i] * 9;
                }else if(chs[i] == '0'){
                    dp[i] = 0;
                }
            }
        }
        return (int)(dp[len - 1] % mod);
    }
}

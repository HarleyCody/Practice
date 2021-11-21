//My Solution: DP traverse from right to left so there will be no leading zero
//if cur == '1' then update number of subsequence end with 1; cur1 = prev1 + prev0 + 1; prev1 = cur
//   cur == '0' then update number of subseqyence end with 0; cur0 = prev0 + prev1 + 1; prev0 = cur
//So cur == prev0 + prev1 + 1; and cur become prev 
//return end with 1
class Solution {
    public int numberOfUniqueGoodSubsequences(String binary) {
        int len = binary.length();
        if(len < 2) return len;
        char[] chs = binary.toCharArray();
        
        int mod = (int) 1e9 + 7;
        int pre1 = 0;
        int pre0 = 0;
        int num0 = 0;
        for(int idx = len - 1; 0 <= idx; --idx){
            int val = (pre0 + pre1 + 1) % mod;
            if(chs[idx] == '0'){
                pre0 = val;
                num0 = 1;
            }else{
                pre1 = val;
            }
        }
        
        return (pre1 + num0) % mod ;
    }
}

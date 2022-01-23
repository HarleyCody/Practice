//My Solution: DP, sum of dp
// dp[i] : number of answer substring that ends with (char)(i + 'a'); 
class Solution{
	public long numberOfSubstrings(String s){
        char[] chs = s.toCharArray();
        int[] count = new int[26];
        long[] dp = new long[26];
        long sum = 0;
        for(int i = 0; i < chs.length; ++i){
            int cIdx = chs[i] - 'a';
            ++count[cIdx];
            sum -= dp[cIdx];
            dp[cIdx] += count[cIdx];
            sum += dp[cIdx];
            //System.out.println(chs[i] + " " + dp[cIdx]);
        }
        return sum;
    }
}

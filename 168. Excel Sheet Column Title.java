class Solution {
    // shfit 10 to 26 with increasing 1 (Z, A, B, C ... Y) Z is shift from 26(0 -> 26)
    // when rem = 0, means it should be Z with 1 less group, so n = n - 1;(ie ,--n) 
    public String convertToTitle(int n) {
        StringBuilder ans = new StringBuilder();
        while(n > 0){
            int rem = n % 26;
            if(rem == 0){
                --n;
                rem = 26;
            }
            int idx = rem;
            ans.append((char)(idx - 1 + 'A'));
            n /= 26;
        }
        return ans.reverse().toString();
    }
}

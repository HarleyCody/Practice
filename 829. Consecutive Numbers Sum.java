class Solution {
    public int consecutiveNumbersSum(int N) {
        while ((N & 1) == 0) N >>= 1;// 2 to N has only one answer;
        
    // even number has same answer with odd number which is divided by 2 for example, ans(10) = ans(10/2) = ans(5)
        int ans = 1, d = 3;

        while (d * d <= N) {
            int e = 0;
            while (N % d == 0) {
                N /= d;
                e++;
            }
            ans *= e + 1;
            d += 2;
        }

        if (N > 1) ans <<= 1;
        return ans;
    }
}

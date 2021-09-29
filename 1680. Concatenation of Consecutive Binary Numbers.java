//Best Solution: 
/*
n = 3
1 - 1
2 - 10
3 - 11

123 -> 11011 --> 

(1 * 2^4) + (1 * 2 ^3 + 0 * 2 ^ 2) + (1 * 2^1 + 1 * 2^0)

(1 * 2^4) + (2 * 2 ^2 + 0 * 2 ^ 2) + (2 * 2^0 + 1 * 2^0)

(1 * 2^4) + (2 + 0) * 2 ^2  + (2 + 1)* 2^0

(1)* 2^4 + (2) * 2 ^2  + (3)* 2^0

((1)* 2^4 + (2) * 2 ^2)  + (3)* 2^0

((1)* 2^2 + (2)) * 2 ^2)  + (3)* 2^0

(4 + 2) * 2^2 + 3

24 + 3 

27

*/
class Solution {
    public int concatenatedBinary(int n) {
        long MOD = (long)1E9 + 7;
        long sum = 0;
        int length = 0; 
        
        for(int i = 1; i <= n; i++) {
            if((i & (i - 1)) == 0)
                length++;
            sum = ((sum << length) | i) % MOD;
        }
        
        return (int) sum;
    }
}
// More intuitive solution
class Solution {
    // TC - O(n)
    public int concatenatedBinary(int n) {
        int MOD = 1_000_000_007;
        long sum = 0;
        int length = 0;
        
        for(int i = 1; i <= n; i++) {
            if((i & (i - 1)) == 0)
                length++;
            sum = ((sum << length) | i) % MOD;
        }
        
        return (int) sum;
    }
}

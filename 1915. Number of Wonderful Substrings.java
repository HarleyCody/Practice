//Best Solution: DP with bit mask
//At current character consider the frequency of others they can be even and odd so there are 2^10 status
/*Within the for loop, the code is actually trying to find all wonderful substrings that ends at the current character. This should make things clear for the following steps
As explained above, cur is the bitmask that represents the parities of a-j. 'count' is a rolling prefix count dictionary. for example, within the for loop, at each iteration, count[000000001] represent the number of times we encounter a prefix with the same parity (in this case, letter a appears odd times, all else even times) in the past iterations.
res += count[cur]: no matter what your current parity is, for each prefix that we've seen that have the same parity, the substring between that prefix and current character will be a wonderful substring, where each character appears even time. and that's why we add count[cur].
4.res += sum(count[cur ^ (1 << i)] for i in xrange(10)), similar to the point above, no matter what parity we are at right now, the parity that are off by 1 will correspond to a prefix of which we can form a wonderful string from the end of that prefix to current letter. Example: we are at position 3 of aaabaa, of letter b. Here the parity is 11, for both a and b appears odd number of times. the parity that are off by 1 is 10 and 01. we can see that prefix a and aaa corresponds to 01, and that aab and b are th wonderful string formed by these prefix.
*/
class Solution {
    public long wonderfulSubstrings(String word) {
        long res = 0, count[]  = new long[1024];
        int cur = 0;
        count[0] = 1L;
        for (int i = 0; i < word.length(); ++i) {
            cur ^= 1 << (word.charAt(i) - 'a');
            res += count[cur]++;
            for (int j = 0; j < 10; ++j){
                res += count[cur ^ (1 << j)];
            }
        }
        return res;
    }
}

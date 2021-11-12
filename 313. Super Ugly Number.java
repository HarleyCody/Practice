//Best Solution: DP. 
//ugly number will be 
//1. number that does not have prime factor
//2. number that have prime factor but in primes
// ans is the list without unknown primes as well
// so use the ans and ponters for each prime in primes to compose a new ugly number
// if(curPrime[j] == prev) curPrime[j] needs to be updated
// curPrime[j] = prime[j] * uglynumber(index[j]);

class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] uglyNumbers = new int[n];
        uglyNumbers[0] = 1;
        int len = primes.length;
        int min = Integer.MAX_VALUE, prev = 1;
        int[] indexes = new int[len];
        int[] tempPrimes = new int[len];

        Arrays.fill(tempPrimes, 1);

        for (int i = 0; i < n; i++) {
            uglyNumbers[i] = prev;
            for (int j = 0; j < tempPrimes.length; ++j) {
                if (tempPrimes[j] == prev) {
                    tempPrimes[j] = primes[j] * uglyNumbers[indexes[j]];
                    ++indexes[j];
                }
                min = Math.min(tempPrimes[j], min);
            }
            prev = min;
            min = Integer.MAX_VALUE;
        }
        return uglyNumbers[n - 1];
    }
}

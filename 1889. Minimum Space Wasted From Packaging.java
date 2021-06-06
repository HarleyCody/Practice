/* Condition
The elements in boxes[j] are distinct.
one pacakge per box
*/
// waste space = total space - total used
class Solution {
    static int MOD = 1_000_000_007;
    public int minWastedSpace(int[] packages, int[][] boxes) {
        int n = packages.length;
        int m = boxes.length;
        
        int maxP = packages[0];
        long[] count = new long[100001];
        long[] sizes = new long[100001];
        for (int p : packages) {
            maxP = Math.max(maxP, p);
            count[p]++;
            sizes[p] += p;
        }
        
        for (int i = 1; i <= 100000; i++) {
            count[i] += count[i-1];
            sizes[i] += sizes[i-1];
        }

        long best = Long.MAX_VALUE;
        for (int[] available : boxes) {
            Arrays.sort(available);
            if (available[available.length-1] < maxP) continue;
            long wasted = 0;
            long prevCount = 0;
            long prevSum = 0;
            for (int size : available) {
                long prefixCount = count[size];
                long prefixSum = sizes[size];
                long boxesUsed = prefixCount - prevCount;
                long sizesSum = prefixSum - prevSum;
                wasted += boxesUsed * size - sizesSum;
                
                prevCount = prefixCount;
                prevSum = prefixSum;
            }
            best = Math.min(best, wasted);
        }
        return best == Long.MAX_VALUE ? -1 : (int) (best % MOD);
    }
}

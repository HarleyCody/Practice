__________________________________________________________________________Best Solution____________________________________________________________________________
class Solution {
    // record freq and traverse by freq, only add diff when its larger than 1
    // record num freq, and increase one num shift num - 1 at i to i + 1; 
    public int minIncrementForUnique(int[] A) {
        int[] count = new int[40000];
        int max = 0;
        for (int a : A) {
            count[a] ++;
            max = Math.max(max, a);
        }
        int res = 0;
        for (int i = 0; i < max; i++) {
            if (count[i] <= 1) {
                continue;
            }
            int diff = count[i] - 1;
            res += diff; // move these duplicate numbers to next num + 1
            count[i + 1] += diff;
            // count[i] = 1;
        }
        // in the end count[max] will have all the duplicates
        // make each duplicate unique, so we need to add 1+2+3+4...
        int diff = count[max] - 1;
        res += diff * (diff + 1) / 2;
        return res;
    }
}
____________________________________________________________________________My Solution____________________________________________________________________________
class Solution {
    // sort and increase smaller or equal value
    public int minIncrementForUnique(int[] A) {
        int incre = 0, len = A.length;
        if(len == 0){
            return 0;
        }
        
        Arrays.sort(A);
        
        for(int i = 1; i < len; ++i){
            if(A[i] == A[i - 1]){
                ++incre;
                ++A[i];
            }else if(A[i] < A[i - 1]){
                incre += A[i - 1] + 1 - A[i];
                A[i] = A[i - 1] + 1;
            }
        }
        return incre;
    }
}

class Solution {
    // maintain the sliding window of size K by flag flipped
    // flipped = 1 within range K its flipped or flipped odd times
    // flipped = 0 within range K its not flipped or flipped even times
    // odd times == 1, evem times == 0 by useing xor(^=) 
    public int minKBitFlips(int[] A, int K) {
        int n = A.length, flipped = 0, res = 0;
        int[] isFlipped = new int[n];
        for (int i = 0; i < A.length; ++i) {
            if (i >= K){
                // increase one times
                flipped ^= isFlipped[i - K];
            }
            // when cur is 1, if its flipped odd times, need one more flip
            // when cur is 0, if its flipped even times, need one more flip
            if (flipped == A[i]) {
                if (i + K > A.length)
                    return -1;
                isFlipped[i] = 1;
                // increase one times
                flipped ^= 1;
                res++;
            }
        }
        return res;
    }
}

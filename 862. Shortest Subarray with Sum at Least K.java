__________________________________________________________________________Best Solution__________________________________________________________________________________
class Solution {
    // dq is store prefix sum in asceding order 
    /*
    Q: Why keep the deque increase?
    A: If B[i] <= B[d.back()] and moreover we already know that i > d.back(), it means that compared with d.back(),
    B[i] can help us make the subarray length shorter and sum bigger. So no need to keep d.back() in our deque.

    More detailed on this, we always add at the LAST position
    B[d.back] <- B[i] <- ... <- B[future id]
    B[future id] - B[d.back()] >= k && B[d.back()] >= B[i]
    B[future id] - B[i] >= k too

    so no need to keep B[d.back()]
    */
    public int shortestSubarray(int[] A, int K) {
        int N = A.length, res = N + 1;
        int[] B = new int[N + 1];
        for (int i = 0; i < N; i++) B[i + 1] = B[i] + A[i];
        Deque<Integer> d = new ArrayDeque<>();
        for (int i = 0; i < N + 1; i++) {
            while (d.size() > 0 && B[i] - B[d.getFirst()] >=  K)
                res = Math.min(res, i - d.pollFirst());
            while (d.size() > 0 && B[i] <= B[d.getLast()])
                d.pollLast();
            d.addLast(i);
        }
        return res <= N ? res : -1;
    }
}
________________________________________________________________________________TLE__________________________________________________________________________________________
// find subArray sum >= K and narrow down the range
class Solution {
    public int shortestSubarray(int[] A, int K) {
        int len = A.length;
        int ans = 500001, sum = 0, l = 0;
        for(int r = 0; r < len; ++r){
            if(sum <= 0){
                sum = A[r];
                l = r;
                if(sum >= K){
                    return 1;
                }
            }else{
                sum += A[r];
                if(sum < K ){
                    continue;
                }
                
                int nl = l;
                int nSum = sum;
                while(nl <= r){
                    if(nSum >= K){
                        l = nl;
                        sum = nSum;
                    }
                    nSum -= A[nl++];
                }
                int curLen = r - l + 1;
                ans = Math.min(curLen, ans);
            }
        }
        return ans == 500001 ? -1 : ans;
    }
}

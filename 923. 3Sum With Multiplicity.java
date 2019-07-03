__________________________________________________________Best Solution________________________________________________________
class Solution {
    public int threeSumMulti(int[] A, int target) {
        long[] c = new long[101];
        // c[index] is sorted and index is value in A[].
        for (int a : A) c[a]++;
        long res = 0;
        for (int i = 0; i <= 100; i++)
            // find from i 
            for (int j = i; j <= 100; j++) {
                int k = target - i - j;
                // cannot be larger than 100 cas it is made by 3 numbers and largest target is 300
                if (k > 100 || k < 0) continue;
                // three same number
                if (i == j && j == k)
                    res += c[i] * (c[i] - 1) * (c[i] - 2) / 6;
                // two of them is same
                else if (i == j && j != k)
                    res += c[i] * (c[i] - 1) / 2 * c[k];
                // i != j && j != k || i != j j == k;
                else if (j < k)// indicate they are not same with any of them.
                    res += c[i] * c[j] * c[k];
            }
        return (int)(res % (1e9 + 7));
    }
}
__________________________________________________________My Solution__________________________________________________________
class Solution {
    int MOD = (int)1e9 + 7;
    public int threeSumMulti(int[] A, int target) {
        int len = A.length;
        // record times of A[i];
        HashMap<Integer, Long> recorder = new HashMap();
        for(int i = 0; i < len; ++i){
            long times = recorder.getOrDefault(A[i], new Long(0));
            ++times;
            recorder.put(A[i], times);
        }
        long ans = 0;
        int i = 0;
        Arrays.sort(A);
        
        while(i < len - 2 && A[i] <= target){
            int j = i + 1, k = len - 1;
            // use two pointers to find correct combination.
            while(j < k){
                int sum = A[i] + A[j] + A[k];
                if(sum == target){
                    HashMap<Integer, Integer> frq = new HashMap();
                    frq.put(A[i], frq.getOrDefault(A[i],0) + 1);
                    frq.put(A[j], frq.getOrDefault(A[j],0) + 1);
                    frq.put(A[k], frq.getOrDefault(A[k],0) + 1);
                    long times = 1;
                    // caculate times of each number by Combinatorics
                    for(Integer key: frq.keySet()){
                        int numbers = frq.get(key);
                        long total = recorder.get(key);
                        times *= calFactorial(total, numbers) / calFactorial(numbers, numbers);
                    }
                    frq.clear();
                    ans += times;
                    // skip to next different pair
                    while(j < k && A[k] == A[--k]);
                    while(j < k && A[j] == A[++j]);
                    
                }else if(sum > target){
                    // skip to next different pair
                    while(j < k && A[k] == A[--k]);
                    
                }else if(sum < target){
                    // skip to next different pair
                    while(j < k && A[j] == A[++j]);
                }
            }
            // skip to next different start. I changes here
            while(i < len - 2 && A[i] == A[++i]);
        }
        return (int) (ans % MOD);
    }
    // calculate Combinatorics
    private long calFactorial(long i, int times){
        long ans = 1;
        while(times-- > 0){
            ans *= i;
            --i;
        }
        return ans;
    }
}

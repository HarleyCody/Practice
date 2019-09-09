class Solution {
    // Record times of time. Permutation
    public int numPairsDivisibleBy60(int[] time) {
        int ans = 0;
        // record times of time % 60, reduce recode length. 
        int[] times = new int[60];
        for(int i = 0; i < time.length; i++){
            times[time[i] % 60]++;
        }
        // based on first half to find pair in second half.
        for(int i = 0; i <= 30; i++){
            // permuate with it per se.
            if(i == 0 || i == 30){
                if(times[i] > 1 && times[i] < 3){
                    ans++;
                }else if(times[i] >= 3){
                    ans += times[i] * (times[i] - 1)/2;
                }
            // permutate with other record.
            }else if(times[i] != 0 && times[60 - i] != 0){
                ans += times[i] * times[60 - i];
            }
        }
        return ans;
    }
}

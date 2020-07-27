class Solution {
    // record range in array by its start point; recorder[i] : interval start at i ends at recorder[i];
    // extend the nextMaxPoint that in current range(left, right) can reach
    // if(currentRight < l) means need open a tab and update the current range to Max and updateMax
    // !! currentRange update comes before updateMax as it picks the max before meeting tab start l > currentRight
    public int minTaps(int n, int[] ranges) {
        int[] recorder = new int[n + 1];
        
        for(int i = 0; i < n + 1; ++i){
            int l = Math.max(0, i - ranges[i]);
            int r = i + ranges[i];
            recorder[l] = Math.max(recorder[l], r);
        }
    
        int right = recorder[0], nums = 1;
        int nextRight = 0;
        
        for(int l = 0; l < n; ++l){
            if(right < l){
                if(nextRight < l){
                    return -1;
                }
                right = nextRight;
                ++nums;
            }
            nextRight = Math.max(recorder[l], nextRight);
        }
        
        if(right < n && nextRight >= n){
            ++nums;
            right = nextRight;
        }
        return right >= n ? nums : -1;
    }
}

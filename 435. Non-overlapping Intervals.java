class Solution {
    // minimize the rane of every interval
    //only choose the node with minimal end in the collection of overlapping node starting with i;
    public int eraseOverlapIntervals(int[][] intervals) {
        int r = intervals.length;
        if(r < 2){
            return 0; 
        }
        int ans = 0;
        Integer prevS = null, prevE = null;
        Arrays.sort(intervals, (x, y) -> x[0] == y[0] ? x[1] - y[1] : x[0] - y[0]);
        for(int[] interval : intervals){
            // new range of interval 
            if(prevS == null || prevE <= interval[0]){
                prevS = interval[0];
                prevE = interval[1];
                continue;
            }
            // update end;
            if(interval[1] < prevE){
                prevE = interval[1];
            }
            ++ans;
        }
        return ans;
    }
}

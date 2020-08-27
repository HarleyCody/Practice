______________________________________________________________________________Best Solution________________________________________________________________________________
class Solution {
// insertion sort, sort by start
    public int[] findRightInterval(int[][] intervals) {
        int min = intervals[0][0];
        int max = intervals[0][1];
        for (int i = 0; i < intervals.length; i++) {
            if(intervals[i][0] < min) min = intervals[i][0];
            if(intervals[i][1] > max) max = intervals[i][1];
        }
        int[] bucket = new int[max - min + 1];
        Arrays.fill(bucket, -1);
        for (int i = 0; i < intervals.length; i++) {
            bucket[intervals[i][0] - min] = i;
        }
        for (int i = bucket.length - 2; i >= 0; i--) {
            if(bucket[i] == - 1) bucket[i] = bucket[i + 1];
        }
        int[] result = new int[intervals.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = bucket[intervals[i][1] - min];
        }
        return result;
    }
}
________________________________________________________________________________My Solution________________________________________________________________________________
class Solution {
//  treemap to record start and i; find one by one. nlogn
    public int[] findRightInterval(int[][] intervals) {
        TreeMap<Integer, Integer> recorder = new TreeMap();
        
        int len = intervals.length;
        for(int i = 0; i < len; ++i){
            if(recorder.containsKey(intervals[i][0])){
                continue;
            }
            recorder.put(intervals[i][0], i);
        }
        
        int[] ans = new int[len];
        for(int i = 0; i < len; ++i){
            int key = intervals[i][1];
            Integer hKey = recorder.ceilingKey(key);
            ans[i] = -1;
            if(hKey != null){
                ans[i] = recorder.get(hKey);
            }
        }
        
        return ans;
    }
}

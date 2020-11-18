_____________________________________________________________________ Best Solution(2020)(O1 space O(n))_____________________________________________________________________
// do not need to sort
// check every interval, if it overlaps with the interval behind it, merge it to the later interval;
// discard current one
// overlaps: s2 end > s1 start, s2 start <= s1 end;(intersect or s2 contains s1)
// key save updated interval to later one not the current one;
class Solution {
    public int[][] merge(int[][] intervals) {
        
    int len = intervals.length, count = len, idx = 0;
    for (int i = 0; i < len - 1; i++) {
      int[] s1 = intervals[i];
      for (int j = i + 1; j < len; j++) {
        int[] s2 = intervals[j];
            if (s1[0] <= s2[1] && s2[0] <= s1[1]) {
                  s2[0] = Math.min(s1[0], s2[0]);
                  s2[1] = Math.max(s1[1], s2[1]);
                  s1[0] = 1;
                  s1[1] = 0;
                  count--;
                  break;
            }
        }
    }
    int[][] res = new int[count][];
    for (int i = 0; i < len; i++) {
      if (intervals[i][1] < intervals[i][0]) {
        continue;
      }
      res[idx++] = intervals[i];
    }
    return res;
    }
}
______________________________________________________________________ My Second Solution(2020)(O1 space nlogn time)_____________________________________________________________________
// reuse the space of original arrays
// return Arrays.copyOfRange
// find min x and max y. (1, 4) (2, 3) even sort still need to find max(l[1], r[1])
class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (x, y) -> x[0] == y[0] ? x[1] - y[1] : x[0] - y[0]);
        int len = intervals.length;
        int l = 0, r = 0;
        while(l < len){
            while(r < len && intervals[r][0] <= intervals[l][1]){
                intervals[l][1] = Math.max(intervals[r++][1], intervals[l][1]);
            }
            if(r == len){
                break;
            }
            intervals[++l] = intervals[r];
        }
        
        return Arrays.copyOfRange(intervals, 0, l + 1);
    }
}
______________________________________________________________________ My First Solution(2019)(On space nlogn time)_____________________________________________________________________
class Solution {
    public int[][] merge(int[][] intervals) {
        if(intervals.length == 0) return new int[][]{};
        Arrays.sort(intervals,(a,b)->a[0]-b[0]);
        List<int[]> ans = new ArrayList<>();
        int i = 0;
        while(i < intervals.length){
            int j = i+1;
            while(j < intervals.length && intervals[i][1]>=intervals[j][0]){
                intervals[i][1] = Math.max(intervals[j][1],intervals[i][1]);//合并前后两项，放到前一项
                ++j;//j为下一个数组
            }
            ans.add(intervals[i]);//合并完成，不能合并了，保存进List
            i=j;//j的位置作为开头查看后面能否合并；
        }
        return ans.toArray(new int[0][]);//List 转为二维数组
    }
}

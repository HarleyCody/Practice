_______________________________________________________Fastest Solution________________________________________________________

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int len = intervals.length, l, r, i;
        // l is position where intervals end before newInterval, l is at intervals[l][0] > newInterval[0];
		for (l = 0; l < len && intervals[l][1] < newInterval[0]; l++);
        
        // r is position where intervals overlap with newInterval
		for (r = l; r < len && intervals[r][0] <= newInterval[1]; r++);
        // caculate size of answerArray and innitialize newIntervals that need to be combined
		int[][] newIntervals = new int[len + l - r + 1][];
        // put intervals end before newIntervals into answer array; and put combined intervals
		for (i = l; --i >= 0; newIntervals[i] = intervals[i]);
		newIntervals[l] = l == r ? // start pos and end pos are in the same interval
            newInterval: new int[]{Math.min(intervals[l][0], newInterval[0]), Math.max(intervals[r - 1][1], newInterval[1])};
        // put intervals start after end of newInterval to answer Array
		for (i = l; ++i < newIntervals.length; newIntervals[i] = intervals[r++]);
		return newIntervals;
    }
}

___________________________________________________________Straight Solution___________________________________________________
class Solution{
     public int[][] insert(int[][] intervals, int[] newInterval) {
         List<List<Integer>> connect = new LinkedList();
         List<Integer> med = new LinkedList();
         int start = 0;
         
         //add node end before newInterval;
         while(start < intervals.length && intervals[start][1] < newInterval[0]){
             med = new LinkedList();
             med.add(intervals[start][0]);
             med.add(intervals[start][1]);
             connect.add(med);
             ++start;
         }
         // try to extend overlapping interval,update newInterval
         while(start < intervals.length && intervals[start][0] <= newInterval[1]){
             newInterval = new int[]{Math.min(intervals[start][0], newInterval[0]), Math.max(intervals[start][1], newInterval[1])};
             ++start;
         }
         // add overlap
         med = new LinkedList();
         med.add(newInterval[0]);
         med.add(newInterval[1]);
         connect.add(med);
         //add rest intervals.
         while(start < intervals.length){
             med = new LinkedList();
             med.add(intervals[start][0]);
             med.add(intervals[start][1]);
             connect.add(med);
             ++start;
         }
         // transfer list to array
         int[][] ans = new int[connect.size()][];
         for(int i = 0; i < connect.size(); ++i){
             int[] temp = connect.get(i).stream().mapToInt(Integer::intValue).toArray();
             ans[i] = temp;
         }
         return ans;
     }
}
/*class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if(intervals.length == 0) {
            int[][] ans = {newInterval};
            return ans;
        }
        int i = 0;
        // startPos where start > newterval.Start
        while(i < intervals.length && intervals[i][0] <= newInterval[0]){
            ++i;
        }
        // startPos where newinterval. Start < intervals[0]
        --i;
        // NewInterval is rightmost interval.
        if(i + 1 == intervals.length && intervals[i][1] < newInterval[0]){
            int[][] ans = new int[intervals.length + 1][];
            for(int k = 0; k < ans.length; ++k){
                if( k < intervals.length){
                    ans[k] = Arrays.copyOf(intervals[k], 2);
                }
                else ans[k] = Arrays.copyOf(newInterval, 2);
            }
            return ans;
        }
        
        // NewInterval is leftmost interval 
        if(i == -1 && intervals[0][0] > newInterval[1]){
            int[][] ans = new int[intervals.length + 1][];
            for(int k = 0; k < ans.length; ++k){
                if( k == 0){
                    ans[k] = Arrays.copyOf(newInterval, 2);
                }
                else ans[k] = Arrays.copyOf(intervals[k - 1], 2);
            }
            return ans;
        }
        
        // in the middle of interval. 
        int j = i;
        
        // endPos where end > newInterval.end
        while(j < intervals.length && intervals[j][1] < newInterval[1]){
            ++j;
        }
        System.out.print("i,j is:" + i + j);
        if(i == j) return intervals;
        int k = 0, num = j - i - 1;
        // in the middle of intervals with no overlapping
        if(intervals[i][1] < newInterval[0] && intervals[j][0] > newInterval[1]){
            int[][] ans = new int[intervals.length + 1][];
            for(; k <= i; ++k){
                ans[k] = Arrays.copyOf(intervals[k], 2);
            }
            ans[++k] = Arrays.copyOf(newInterval, 2);
            for(; k < ans.length; ++k){
                ans[k] = Arrays.copyOf(intervals[k - 1], 2);
            }
            return ans;
        }
        
        if( j == intervals.length){
            int[][] ans = new int[intervals.length - num][];
            while(k < i){
                ans[k] = Arrays.copyOf(intervals[k++], 2);
            }
            ans[k] = Arrays.copyOf(new int[]{intervals[i][0],newInterval[1]}, 2);
            return ans;
        }
        
        // in the middle with left part overlapping
        if(intervals[i][1] >= newInterval[0] && intervals[j][0] > newInterval[1]){
            
            int[][] ans = new int[intervals.length - num][];
            while(k < i){
                ans[k] = Arrays.copyOf(intervals[k++], 2);
            }
            ans[k] = Arrays.copyOf(new int[]{intervals[i][0],newInterval[1]}, 2);
            for(; j < intervals.length; ++j){
                ans[++k] = Arrays.copyOf(intervals[j], 2);
            }
            return ans;
        }
        // in the middle with right part overlapping
        if(intervals[i][1] < newInterval[0] && intervals[j][0] <= newInterval[1]){
            int[][] ans = new int[intervals.length - num][];
            while(k <= i){
                ans[k] = Arrays.copyOf(intervals[k++], 2);
            }
            ans[k] = Arrays.copyOf(new int[]{newInterval[0], intervals[j++][1]}, 2);
            for(; j < intervals.length; ++j){
                ans[++k] = Arrays.copyOf(intervals[j], 2);
            }
            return ans;
        }
        // in the middle with both end overlapping
        if(intervals[i][1] >= newInterval[0] && intervals[j][0] <= newInterval[1]){
            int[][] ans = new int[intervals.length - num - 1][];
            while(k < i){
                ans[k] = Arrays.copyOf(intervals[k++], 2);
            }
            ans[k] = Arrays.copyOf(new int[]{intervals[i][0], intervals[j++][1]}, 2);
            for(; j < intervals.length; ++j){
                ans[++k] = Arrays.copyOf(intervals[j], 2);
            }
            return ans;
        }
        return intervals;
    }
}*/

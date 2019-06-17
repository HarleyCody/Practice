_______________________________________________________Fastest Solution________________________________________________________


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

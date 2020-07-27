_________________________________________________________________________________Best Solution________________________________________________________________________
class Solution {
    //As the input is already sorted by left x;
    //sweep line from left to right will be traverse buildings from left to right;
    //cur[0] >= pre[0];
    //pre is building with max height
    // its like sliding window,
    // cur will read building one by one also it possibly will increase size of pq for leaving point
    // pre only record last highest building,
    // imagine cur is right, pre is left between left to right it records buildings in descending sequence;
    // in while left goes to right to get all leaving point added.
    // cur[0] > pre[1] means right pointer should have overlap with left pointer, otherwise there will be a new start;
    // in while pq and continue assure valid next must be in the right of pre and lower than pre,
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> ret = new ArrayList<>();
        
        // sorted by height and leaving x
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[2] == b[2] ? a[1] - b[1] : b[2] - a[2];
            }
        });
        
        int[] pre = new int[]{-1, Integer.MAX_VALUE, 0};
        for (int[] cur : buildings) {
            // add pre[1], next[2] to answerlist, when cur and pre has gap
            // while input all leaving node
            while (!pq.isEmpty() && cur[0] > pre[1]) {
                int[] next = pq.poll();// out put point with largest height
                if (next[1] <= pre[1]){
                    continue;
                }
                // red.right and green.height when cur is purple && 12，0 is made by {5,12,12}{-1, IntegerMax, 0}
                ret.add(Arrays.asList(pre[1], next[2]));
                // expend pre to approach curBuilding
                pre = next;
            }
            // current building is highest
            if (cur[2] > pre[2]) {
                // update entry point by deleting pre, as pre is recorded in the answer list;
                if (cur[0] == pre[0]) {
                    //remove last recorded(previous) point
                    ret.remove(ret.size() - 1);
                }
                // add new entry point as largest hight on cur[0] has changed
                ret.add(Arrays.asList(cur[0], cur[2]));
                
                // curBuilding has been completedly recorded as it is contained in preBuilding
                if (cur[1] < pre[1]) {
                    // add pre to pq, as it is still not end
                    pq.add(pre);
                }
                //update pre to highest building
                pre = cur;
            }
            //current height <= previous height
            else if (cur[1] > pre[1]) {
                if (cur[2] == pre[2]) {
                    // extend right of pre if cur has same height
                    pre[1] = cur[1];
                } else {
                    // for generate leaving point later
                    pq.add(cur);
                }
            }
        }
        while (!pq.isEmpty()) {
            int[] next = pq.poll();
            if (next[1] <= pre[1]) continue;
            ret.add(Arrays.asList(pre[1], next[2]));
            pre = next;
        }
        // goes back to zero (last point) when pre height is not zero
        if (pre[2] > 0) {
            ret.add(Arrays.asList(pre[1], 0));
        }
        return ret;
    }
}

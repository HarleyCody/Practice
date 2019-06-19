class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> ret = new ArrayList<>();
        
        // sorted by height, if height is same, sorted by right point, record largest height with larger end and lowerheight
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[2] == b[2] ? a[1] - b[1] : b[2] - a[2];
            }
        });
        // first element, with lowest height
        int[] pre = new int[]{-1, Integer.MAX_VALUE, 0};
        for (int[] cur : buildings) {
            // add pre[1], next[2] to answerlist, when cur and pre is not overlap
            while (!pq.isEmpty() && cur[0] > pre[1]) {
                int[] next = pq.poll();// out put point with largest height
                if (next[1] <= pre[1]) continue;
                ret.add(Arrays.asList(pre[1], next[2]));// red.right and green.height when cur is purple && 12，0 is made by {5,12,12}{-1, IntegerMax, 0}
                // change pre to polled point
                pre = next;
            }
            // current height > previous height
            if (cur[2] > pre[2]) {
                // current and previous has same start
                if (cur[0] == pre[0]) {
                    //remove last recorded(previous) point
                    ret.remove(ret.size() - 1);
                }
                // add new point with larger height
                ret.add(Arrays.asList(cur[0], cur[2]));
                // try to record largest height, current right < previous right, 
                if (cur[1] < pre[1]) {
                    // add pre to pq, as it still not end
                    pq.add(pre);
                }
                //improve pre previous height
                pre = cur;
            }
            //current height <= previous height
            else if (cur[1] > pre[1]) {// current end is larger than pre end
                if (cur[2] == pre[2]) {
                    // extend right of pre if cur has same height
                    pre[1] = cur[1];
                } else {// current height < previous height
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

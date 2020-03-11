____________________________________________________________Best Solution________________________________________________________________
class Solution {
     // merge and flat two slots in one time line
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        PriorityQueue<int[]> pq = 
            new PriorityQueue<>((n1,n2) -> n1[0] - n2[0]);
        
        for(int[] s : slots1)
            if(s[1]-s[0]>=duration)
                pq.offer(s);
        
        for(int[] s: slots2)
            if(s[1]-s[0]>=duration)
                pq.offer(s);
        
        while(pq.size() > 1)
            // if overlap, slot must from two different people, as there is no overlap in one people.
            if(pq.poll()[1] >= pq.peek()[0] + duration)
                return Arrays.asList(pq.peek()[0],pq.peek()[0]+duration);
        
        return Arrays.asList();
    }
}
_____________________________________________________________My Solution_________________________________________________________________
class Solution {
    // choose slot from 1 and 2 and compute first intersection by two pointers
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        int len1 = slots1.length, len2 = slots2.length;
        // two pointers
        int idx1 = 0, idx2 = 0;
        Arrays.sort(slots1,(x, y) -> x[0] - y[0]);
        Arrays.sort(slots2,(x, y) -> x[0] - y[0]);
        
        while(idx1 < len1 && idx2 < len2){
            if(slots1[idx1][1] - slots1[idx1][0] < duration){
                ++idx1;
                continue;
            }
            if(slots2[idx2][1] - slots2[idx2][0] < duration){
                ++idx2;
                continue;
            }
            
            int start = Math.max(slots1[idx1][0], slots2[idx2][0]);
            int end = Math.min(slots1[idx1][1], slots2[idx2][1]);
            // intersection is larger answer found;
            if(end - start >= duration){
                return Arrays.asList(start, start + duration);
            }
            
            if(slots1[idx1][0] < slots2[idx2][0]){
                // slot1 should be larger
                ++idx1;
            }else{
                // slots2 cannot contains slot1(slot1[tail] - slot1[head] < duration is excluded in the beginning of loop)
                ++idx2;
            }
        }
        return new ArrayList();
    }
}

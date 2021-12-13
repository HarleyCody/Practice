//General answer: Memic whole process.
//At curDay put all events that can be joined at today
//Choose closest event to join
//Delete closed event go to tomorrow
class Solution {
    public int maxEvents(int[][] events) {
        Arrays.sort(events, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a, b) -> a- b);
        
        int maxDay = 0;
        for(int[] event : events){
            maxDay = Math.max(maxDay, event[1]);
        }
        
        int ans = 0;
        int idx = 0;
        int day = 0;
        while(day <= maxDay){
            while(idx < events.length && events[idx][0] <= day && day <= events[idx][1]){
                pq.offer(events[idx++][1]);
            }
            
            if(!pq.isEmpty()){
                ++ans;
                pq.poll();
            }
            
            ++day;
            while(!pq.isEmpty() && pq.peek() < day){
                pq.poll();
            }
        }
        
        return ans;
    }
}

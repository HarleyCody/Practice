//Best Solution: Record the first available day for event starts at event[0]
//Similar to union found it can be applied to record the date and update the first available day
/*
1. for each event, we only select the first day if hasn't attended before
2. sort the day according to end day: necessary
*/
class Solution {
    public int maxEvents(int[][] events) {
        int n = events.length;
        Arrays.sort(events, (a, b) -> a[1] - b[1]);
        int lastDate = events[n-1][1];
        
        int[] parent = new int[lastDate+2];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        
        int count = 0;
        for (int[] e : events) {
            int x = find(parent, e[0]);
            if (x <= e[1]) {
                count++;
                parent[x] = find(parent, x + 1);
            }
        }
        
        
        return count;
    }
    
    private int find(int[] parent, int i) {
        if (parent[i] != i) {
            parent[i] = find(parent, parent[i]);
        }
        return parent[i];
    }
}

//Improve: only sort by start and pq pop by end
//General answer: Memic whole process.
//At curDay put all events that can be joined at today
//Choose closest event to join
//Delete closed event go to tomorrow
class Solution {
    public int maxEvents(int[][] events) {
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
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

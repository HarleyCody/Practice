__________________________________________________________________Best Solution_________________________________________________________
// load and unload, check capacity in every stop with distance i;
class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        int[] unload = new int[1001];
        int[] load = new int[1001];
        
        for(int[] trip : trips){
            load[trip[1]] += trip[0];
            unload[trip[2]] += trip[0];
        }
        
        for(int i = 0; i < 1001; ++i){
            capacity -= load[i];
            capacity += unload[i];
            if(capacity < 0) return false;
        }
        return true;
    }
}
____________________________________________________________________Naive Solution_________________________________________________________
//get on get off in with arrived stop order
class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        Arrays.sort(trips, (x, y) -> x[1] - y[1]);
        
        PriorityQueue<Integer> stops = new PriorityQueue();
        HashMap<Integer, Integer> recorder = new HashMap();
        for(int[] trip : trips){
            //unload passenger
            while(!stops.isEmpty() && stops.peek() <= trip[1]){
                int unloadStop = stops.poll();
                int unloadPeople = recorder.getOrDefault(unloadStop, 0);
                capacity += unloadPeople;
                recorder.remove(unloadStop);
            }
            // load passenger
            capacity -= trip[0];
            if(capacity < 0){
                return false;
            }
            
            // able to load current passenger;
            stops.offer(trip[2]);
            int numOfDestination = recorder.getOrDefault(trip[2], 0);
            recorder.put(trip[2], numOfDestination + trip[0]);
        }
        return true;
    }
}

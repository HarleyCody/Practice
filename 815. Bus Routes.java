___________________________________________________Best Solution(BFS)____________________________________________________________
class Solution {
    // record minimal steps of reaching stop, if there is any stop is changed, update relative stop until there is no change.
    // return count[T] if its max
    public int numBusesToDestination(int[][] routes, int S, int T) {   
        int maxStop = 0;
        for (int[] route : routes) {
            for (int stop : route) {
                maxStop = Math.max(stop, maxStop);
            }
        }
        maxStop++;
        // record minminal steps reach stop i;
        int[] counts = new int[maxStop];
        Arrays.fill(counts, maxStop);
        // start need 0 step
        counts[S] = 0;
        // if there is any stop is updated, it will be true, so it keeps update every stop related to updated stop
        boolean changed = true;
        while (changed) {
            // if there is nothing changed, the count is up to date, return it directly.
            changed = false; // to improve performance. 
            for (int[] route : routes) {
                int min = maxStop - 1;
                for (int stop : route) {
                    min = Math.min(min, counts[stop]);
                }
                min++;
                for (int stop : route) {
                    if (counts[stop] > min) {
                        counts[stop] = min;
                        changed = true;
                    }
                }
            }
        }
        return counts[T] == maxStop ? -1 : counts[T];
    }
}
___________________________________________________My Solution(BFS)____________________________________________________________
class Solution {
    // extend stop bus by bus, so hashmap record all the bus I can take in curStop(key: stop Value:busList
    // taken record bus that I have taken. record stop will TLE;
    HashMap<Integer, List<Integer>> boards = new HashMap();
    boolean[] taken = new boolean[500];
    public int numBusesToDestination(int[][] routes, int S, int T) {
        if(S == T) return 0;
        // add all bus stop at curStop
        for(int i = 0; i < routes.length; ++i){
            for(int j = 0; j < routes[i].length; ++j){
                List<Integer> buses = boards.getOrDefault(routes[i][j], new ArrayList());
                buses.add(i);
                boards.put(routes[i][j], buses); 
            }
        }
        
        Queue<Integer> q = new LinkedList();
        q.add(S);
        // bfs
        int ans = 0, size = 0, curStop = 0;
        while(!q.isEmpty()){
            ++ans;
            size = q.size();
            for(int i = 0; i < size; ++i){
                curStop = q.poll();
                if(curStop == T) return ans;
                List<Integer> adj = boards.get(curStop);
                for(int bus : adj){
                    if(taken[bus]) continue;
                    taken[bus] = true;
                    for(int stop : routes[bus]){
                        // reduce depth of bfs; determine in next stop not cur stop;
                        if(stop == T) return ans;
                        q.add(stop);
                    }
                }
            }
        }
        return -1;
    }
}

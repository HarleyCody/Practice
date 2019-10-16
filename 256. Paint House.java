____________________________________________________________Best Solution__________________________________________________________________
class Solution {
    // only three colors, so cur += Min(other two color in last level(e,g. i - 1 row));
    public int minCost(int[][] costs) {
        if(costs.length == 0 || costs[0].length == 0) return 0;
        
        for(int i = 1; i < costs.length; i++){
            costs[i][0] += Math.min(costs[i - 1][1],costs[i - 1][2]);
            costs[i][1] += Math.min(costs[i - 1][0],costs[i - 1][2]);
            costs[i][2] += Math.min(costs[i - 1][0],costs[i - 1][1]);
        }
        return Math.min(costs[costs.length - 1][0], Math.min(costs[costs.length - 1][1],costs[costs.length - 1][2]));
    }
}
____________________________________________________________My Solution____________________________________________________________________
class Solution {
    // dp . pq stores minimal costs in last level;
    // if costs[i - 1][j] != pq.peek() cost[i][j] += pq.peek() if costs[i - 1][j] != pq.peek();
    // else poll() and pq.peek() = second minimal value; cost[i][j] += pq.peek(), put poll() value back.
    public int minCost(int[][] costs) {
        if(costs.length == 0 || costs[0].length == 0) return 0;
        PriorityQueue<Integer> mins = new PriorityQueue();
        
        for(int i = 0; i < costs[0].length; i++){
            mins.offer(costs[0][i]);
        }
        
        for(int i = 1; i < costs.length; i++){
            PriorityQueue<Integer> curMins = new PriorityQueue();
            for(int j = 0; j < costs[0].length; j++){
                if(costs[i - 1][j] != mins.peek()){
                    costs[i][j] += mins.peek();
                
                }
                else{
                    int temp = mins.poll();
                    costs[i][j] += mins.peek();
                    mins.offer(temp);
                }
                curMins.offer(costs[i][j]);
            }
            mins = curMins;
        }
        return mins.peek();
    }
}

____________________________________________________________________________Best Solution_____________________________________________________________________________________________
//dp
class Solution {
    // dp[i] record min step from 0 to reach i
    int[] dp = new int[10001];
    public int racecar(int t) {
        if (dp[t] > 0) return dp[t];
        int n = (int)(Math.log(t) / Math.log(2)) + 1;
        if (1 << n == t + 1) {
            dp[t] = n;
        } else {
            // ans for pass target go back + 1 for turning car
            dp[t] = racecar((1 << n) - 1 - t) + n + 1;
            for (int m = 0; m < n - 1; ++m) {
                // turn go to n and turn to 2^m before reach so it will cost steps + n - 1 + m + 2;
                // 2 for two turns, so 
                dp[t] = Math.min(dp[t], racecar(t - (1 << (n - 1)) + (1 << m)) + n + m + 1);
            }
        }
        return dp[t];
    }
}
// dp iterative way
class Solution {
    public int racecar(int target) {
        int[] dp = new int[target + 3];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0; dp[1] = 1; dp[2] = 4;

        for (int t = 3; t <= target; ++t) {
            int k = 32 - Integer.numberOfLeadingZeros(t);
            // sum of previous speed == current pos, sum of speed is a distance derives from keeping accelerating
            if (t == (1<<k) - 1) {
                dp[t] = k;
                continue;
            }
            // k - 1 is the move that is before the t;
            for (int j = 0; j < k-1; ++j)
                dp[t] = Math.min(dp[t], dp[t - (1<<(k-1)) + (1<<j)] + k-1 + j + 2);
            if ((1<<k) - 1 - t < t)
                dp[t] = Math.min(dp[t], dp[(1<<k) - 1 - t] + k + 1);
        }

        return dp[target];  
    }
}
____________________________________________________________________________Best BFS_____________________________________________________________________________________________
/*Prunning Idea: We should allow the car to be able to pass the target and then reverse the direction to go to the target. However, there should be an upper bound because there's no point to keep accelerating after passing the target.

To find the upper bound, lets' consider an extreme case where the car is always accelerating, the number of acceleration is m, the car with position (2^m) - 1 has not reached the target: (2^m)-1 < target. Since the target is an integer, we can rewrite it as: target = (2^m)+k, k >= 0... (1)

In the above extreme case, the car's speed 2^m is the maximum possible speed at the position (2^m)-1. Note that we should allow the car to be able to pass the target, which leads to the maximum next position is (2^m)-1 + 2^m... (2)

Combine (1) and (2) we get (2^m)-1+2^m = 2*(2^m)-1 < 2*(2^m)+2*k = 2*target, k >= 0. It says, the upper bound for any position with any possible speed is 2*target.
*/
class Solution { 
//prunning when doing bfs with idea above
    public int racecar(int target) { 
        Queue<int[]> q = new LinkedList();
        HashMap<Integer, HashSet<Integer>> visited = new HashMap();
        
        q.offer(new int[]{0, 1});
        
        int steps = 0;
        while(!q.isEmpty()){
            
            int size = q.size();
            for(int s = 0; s < size; ++s){
                int[] cur = q.poll();
                int np = cur[0] + cur[1];
                if(np == target || np == -target){
                    return steps + 1;
                }
                int ns = cur[1] * 2;
                if(0 < np && np < (target << 1) && (!visited.containsKey(np) || !visited.get(np).contains(ns))){
                    q.offer(new int[]{np, ns});
                    visited.putIfAbsent(np, new HashSet());
                    visited.get(np).add(ns);
                }
                np = cur[0];
                ns = cur[1] < 0 ? 1 : -1;
                if(0 < np && np < (target << 1) && (!visited.containsKey(np) || !visited.get(np).contains(ns))){
                    q.offer(new int[]{np, ns});
                    visited.putIfAbsent(np, new HashSet());
                    visited.get(np).add(ns);
                } 
            }
            ++steps;
        }
        return -1;
    }
}
____________________________________________________________________________My BFS(TLE)_____________________________________________________________________________________________
// limitted prunning
class Solution {
    public int racecar(int target) { 
        Queue<int[]> q = new LinkedList();
        HashMap<Integer, HashSet<Integer>> visited = new HashMap();
        
        q.offer(new int[]{0, 1});
        
        int steps = 0;
        while(!q.isEmpty()){
            
            int size = q.size();
            for(int s = 0; s < size; ++s){
                int[] cur = q.poll();
                int np = cur[0] + cur[1];
                if(np == target){
                    return steps + 1;
                }
                int ns = cur[1] * 2;
                if(!visited.containsKey(np) || !visited.get(np).contains(ns)){
                    q.offer(new int[]{np, ns});
                    visited.putIfAbsent(np, new HashSet());
                    visited.get(np).add(ns);
                }
                np = cur[0];
                ns = cur[1] < 0 ? 1 : -1;
                if(!visited.containsKey(np) || !visited.get(np).contains(ns)){
                    q.offer(new int[]{np, ns});
                    visited.putIfAbsent(np, new HashSet());
                    visited.get(np).add(ns);
                } 
            }
            ++steps;
        }
        
        return -1;
    }
}

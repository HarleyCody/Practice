______________________________________________________________________________________Best Solution_____________________________________________________________________________
class Solution {
    //  Time : O(K * logN);
    // bfs + Greedy, only pick most vacation city to flight in next week
    public int maxVacationDays(int[][] flights, int[][] days) {
        int N = flights.length, K = days[0].length;
        Map<Integer, List<Integer>> srcToDst = new HashMap<>();
        for (int i = 0; i < N; i++) {
            List<Integer> dst = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                if (flights[i][j] == 1 || i == j) 
                    dst.add(j);
            }
            srcToDst.put(i, dst);
        }
        
        PriorityQueue<City> maxHeap = new PriorityQueue<>();
        maxHeap.offer(new City(0,0,0));
        
        for (int i = 0; i < K; i++) {
            PriorityQueue<City> newHeap = new PriorityQueue<>();
            // only pick top N city with max days to travel
            // otherwise MLE
            while (newHeap.size() < N && !maxHeap.isEmpty()) {
                City curCity = maxHeap.poll();
                List<Integer> nexts = srcToDst.get(curCity.id);
                for (int nextCity : nexts) {
                    int newDays = days[nextCity][curCity.week] + curCity.day;
                    newHeap.offer(new City(nextCity, newDays, curCity.week + 1));
                }
            }
            maxHeap = newHeap;
        }
        return maxHeap.poll().day;      // at last week, the maximum vacations we can get
    }
}
class City implements Comparable<City>{
    int id, day, week;
    public City(int id, int day, int week) {
        this.id = id;
        this.day = day;
        this.week = week;
    }
    public int compareTo(City that) {
        return that.day - this.day;
    }
}
________________________________________________________________________________________1D DP Solution_____________________________________________________________________________
// compress to 1D as status in week i is only related to status in weeek i - 1 (ie, previous week), two array to record status in last and cur week
class Solution {
    public int maxVacationDays(int[][] flights, int[][] days) {
        int tltC = flights.length, tltW = days[0].length;
        
        int[] dp = new int[tltC];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;
        
        
        for(int w = 0; w < tltW; ++w){
            int[] tmp = new int[tltC];
            Arrays.fill(tmp, Integer.MIN_VALUE);
            for(int i = 0; i < tltC; ++i){
                for(int j = 0; j < tltC; ++j){
                    if(flights[j][i] != 1 && i != j){
                        continue;
                    }
                    tmp[i] = Math.max(tmp[i], dp[j] + days[i][w]);
                } 
            }
            dp = tmp;
        }
        int max = 0;
        for(int d : dp){
            max = Math.max(max, d);
        }
        return max;
    }
}
________________________________________________________________________________________2D DP Solution_____________________________________________________________________________
//2d during week i at city j
class Solution {
    public int maxVacationDays(int[][] flights, int[][] days) {
        int tltC = flights.length, tltW = days[0].length;
        
        int[][] dp = new int[tltW + 1][tltC];
        Arrays.fill(dp[0], Integer.MIN_VALUE);
        
        dp[0][0] = 0;
        int max = 0;
        for(int w = 1; w <= tltW; ++w){
            Arrays.fill(dp[w], Integer.MIN_VALUE);
            for(int i = 0; i < tltC; ++i){
                for(int j = 0; j < tltC; ++j){
                    if(flights[j][i] != 1 && i != j){
                        continue;
                    }
                    if(w > 0){
                        dp[w][i] = Math.max(dp[w][i], dp[w - 1][j] + days[i][w - 1]);
                    } 
                }
            }
        }
        for(int d : dp[tltW]){
            max = Math.max(max, d);
        }
        return max;
    }
}
________________________________________________________________________________________My Solution_____________________________________________________________________________
class Solution {
    // dfs + mem
    // status is flight at city i int week j
    int tltC, tltW;
    int[][] mem;
    
    public int maxVacationDays(int[][] flights, int[][] days) {
        tltC = flights.length;
        tltW = days[0].length;
        mem = new int[tltC][tltW];
        
        int ans = 0;
        
        return start(flights, days, 0, -1);
    }
    
    private int start(int[][] flights, int[][] days, int curCity, int curWeek){
        //System.out.println("At " + curCity + " in " + curWeek);
        if(curWeek == tltW){
            //System.out.println("vavation is over");
            return 0;
        }
        if(curWeek != -1 && mem[curCity][curWeek] != 0){
            return mem[curCity][curWeek];
        }
        
        int ans = curWeek == -1 ? 0 : days[curCity][curWeek];
        int next = 0;
        for(int i = 0; i < tltC; ++i){
            if(flights[curCity][i] != 1 && curCity != i){
                continue;
            }
            next = Math.max(next, start(flights, days, i, curWeek + 1));
        }
        if(curWeek >= 0){
            mem[curCity][curWeek] = ans + next;
        }
        return ans + next;
    }
}

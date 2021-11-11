//Best Solution: color with increasing order. Change the color for max ID garden, break the chain one by one
public class Solution{
    public int[] gardenNoAdj(int n, int[][] paths) {
        int[] result = new int[n];
        Arrays.fill(result, 1);
        boolean stop = false;
        while(!stop) {
            stop = true;
            for(int[] edge: paths) {
                int u = Math.min(edge[0], edge[1]);
                int v = Math.max(edge[0], edge[1]);
                if (result[u - 1] == result[v - 1]) {
                    result[v - 1] = result[v - 1] == 4 ? 1 : result[v - 1] + 1;
                    stop = false;
                }
            }
        }
        return result;
    }
}

//Best Solution: Idea is same as mine. Just use bit mask to get the color instead of boolean[] array
class Solution {
    public int[] gardenNoAdj(int n, int[][] paths) {
        int[][] route = new int[n][3];
        int[] ans = new int[n];
        
        for(int[] path : paths){
            int from = path[0] - 1;
            int to = path[1] - 1;
            if(route[from][0] == 0){
                route[from][0] = to;
            }else if(route[from][1] == 0){
                route[from][1] = to;
            }else{
                route[from][2] = to;
            }
            
            if(route[to][0] == 0){
                route[to][0] = from;
            }else if(route[to][1] == 0){
                route[to][1] = from;
            }else{
                route[to][2] = from;
            }
        }
        for (int i = 0; i < n; i++) {
            int mask = 0;
            for (int neighbor : route[i]) { 
                mask = mask | (1 << ans[neighbor]);
            }
            for (int c = 1; c < 5 && ans[i] == 0; c++) {
                if ((mask & (1 << c)) == 0) {
                    ans[i] = c;
                }
            }
        }
        
        return ans;
    }
}
//My Solution: Choose the first available color to plant
//Check its neibor one by one O(3n)
//Use array
class Solution {
    int[] ans;
    int[][] route;
    public int[] gardenNoAdj(int n, int[][] paths) {
        ans = new int[n];
        route = new int[n][3];
        for(int[] path : paths){
            int from = path[0] - 1;
            int to = path[1] - 1;
            if(route[from][0] == 0){
                route[from][0] = to;
            }else if(route[from][1] == 0){
                route[from][1] = to;
            }else{
                route[from][2] = to;
            }
            
            if(route[to][0] == 0){
                route[to][0] = from;
            }else if(route[to][1] == 0){
                route[to][1] = from;
            }else{
                route[to][2] = from;
            }
        }
        for(int i = 0; i < n; ++i){
            if(ans[i] == 0){
                ans[i] = 1;
                plant(i);
            }
        }
        return ans;
    }
    
    private void plant(int id){
        boolean[] occupied = new boolean[5];
        for(int i : route[id]){
            int neiColor = ans[i];
            occupied[neiColor] = true;
        }
        int c = 1;
        while(occupied[c]){
            ++c;
        }
        ans[id] = c;
    }
}

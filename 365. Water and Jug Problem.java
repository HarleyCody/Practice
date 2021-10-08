//Best Solution: gcd means the minimal water that can be keep, 
//so repeat the process as many as possible to see if the water can be collected 
class Solution {
public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        if(jug1Capacity + jug2Capacity < targetCapacity)
            return false;
        if(targetCapacity%gcd(jug1Capacity,jug2Capacity) == 0)
            return true;
        return false;
    }
    
    public int gcd(int a,int b){
        if(a==0){
            return b;
        }
        return gcd(b%a,a);
    }
}
//Better Solution: As the operation only allows pour or add, so every time it could be +x +y -x -y the final state will be (tar, 0) or (0, tar)
//So assume every time move the remaining water to one bottle and fill one of the bottle with x or y or remove x or y
class Solution {
    public boolean canMeasureWater(int x, int y, int z) {
        if (x + y < z) return false;
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        
        while (queue.size() > 0) {
            int cur = queue.poll();
            if (cur == z) return true;
            if (cur + x <= x + y && visited.add(cur + x)) queue.offer(cur + x);
            if (cur - x >= 0 && visited.add(cur - x)) queue.offer(cur - x);
            if (cur + y <= x + y && visited.add(cur + y)) queue.offer(cur + y);
            if (cur - y >= 0 && visited.add(cur - y)) queue.offer(cur - y);
        }
        return false;
    }
}
//My Solution: BFS 3 operation for 2 jugs, in totall 6 operation
//Visited recorde the hash of two buckets
class Solution {
    public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {  
        Set<Long> visited = new HashSet<Long>();
        Queue<int[]> q = new LinkedList<int[]>();
        
        int[] curStatus = new int[2];
        int[] nextStatus;
        int delta = 0;
        long idx = 0;
        long hash = jug2Capacity + 1;
        visited.add(0L);
        q.offer(curStatus);
        while(!q.isEmpty()){
            curStatus = q.poll();

            if(curStatus[0] == targetCapacity || curStatus[1] == targetCapacity || curStatus[0] + curStatus[1] == targetCapacity) return true;
            
            nextStatus = new int[2];
            nextStatus[0] = curStatus[0];
            idx = nextStatus[0] * hash;
            if(visited.add(idx)) q.offer(nextStatus); 
               
            nextStatus = new int[2];
            nextStatus[1] = curStatus[1];
            idx = nextStatus[1];
            if(visited.add(idx)) q.offer(nextStatus); 
            
            nextStatus = new int[2];
            nextStatus[0] = jug1Capacity;
            nextStatus[1] = curStatus[1];
            idx = hash * nextStatus[0] + nextStatus[1];
            if(visited.add(idx)) q.offer(nextStatus);
            
            nextStatus = new int[2];
            nextStatus[0] = curStatus[0];
            nextStatus[1] = jug2Capacity;
            idx = hash * nextStatus[0] + nextStatus[1];
            if(visited.add(idx)) q.offer(nextStatus);
            
            nextStatus = new int[2];
            delta = Math.min(jug1Capacity - curStatus[0], curStatus[1]);
            nextStatus[0] = curStatus[0] + delta;
            nextStatus[1] = curStatus[1] - delta;
            idx = hash * nextStatus[0] + nextStatus[1];
            if(visited.add(idx)) q.offer(nextStatus);
            
            nextStatus = new int[2];
            delta = Math.min(jug2Capacity - curStatus[1], curStatus[0]);
            nextStatus[0] = curStatus[0] - delta;
            nextStatus[1] = curStatus[1] + delta;
            idx = hash * nextStatus[0] + nextStatus[1];
            if(visited.add(idx)) q.offer(nextStatus);
        }
        return false;
    }
}

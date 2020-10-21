_________________________________________________________________________________My Solution(Best)______________________________________________________________________________
// same as stack but using pointer to record size instead of sta
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        int idx = 0;
        int len = asteroids.length;
        for(int i = 0; i < len; ++i){
            while(asteroids[i] < 0 && idx != 0 && asteroids[idx - 1] > 0 && 
                  asteroids[idx - 1] < -asteroids[i]){
                asteroids[--idx] = asteroids[i];
            }
            if(idx == 0 || asteroids[idx - 1] * asteroids[i] > 0 ||
              asteroids[idx - 1] < 0 && asteroids[i] > 0){
                asteroids[idx++] = asteroids[i];
            }else if(asteroids[idx - 1] == -asteroids[i]){
                --idx;
            }
        }
        
        int[] ans = new int[idx];
        for(int i = 0; i < idx; ++i){
            ans[i] = asteroids[i];
        }
        return ans;
    }
}

____________________________________________________________________________My Concise Solution____________________________________________________________________________________
// use stack to record asteroids that will not collide.
// Collide happen when cur < 0 prev > 0; pop stack according to rules in question
// if equal, do not push new asteroid to stack
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> sta = new Stack();
        
        for(int a : asteroids){
            while(!sta.isEmpty() && sta.peek() > 0 && a < 0 && sta.peek() < -a){
                sta.pop();
            }
            if(sta.isEmpty() || sta.peek() * a > 0 || sta.peek() < 0 && a > 0){
                sta.push(a);
            }else if(Math.abs(sta.peek()) == Math.abs(a)){
                sta.pop();
            }
        }
        int len = sta.size();
        int[] ans = new int[len];
        
        for(int i = len - 1; 0 <= i; --i){
            ans[i] = sta.pop();
        }
        
        return ans;
    }
}
____________________________________________________________________________My Solution Naive____________________________________________________________________________________
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        int len = asteroids.length;
        if(len == 0) return asteroids;
        Deque<Integer> recorder = new LinkedList();
        recorder.offer(asteroids[0]);
        
        for(int i = 1; i < len; ++i){
            // get previous value;
            int p = recorder.isEmpty() ? 0 : recorder.pollLast();
            int predir = p >= 0 ? (p == 0 ? 0 : 1) : -1;
            int curdir = asteroids[i] > 0 ? 1 : -1;
            
            while(curdir == -1 && predir == 1){// eliminate collision
                // get result after collision
                int result = Math.abs(p) >= Math.abs(asteroids[i]) ? 
                    (Math.abs(p) == Math.abs(asteroids[i]) ? 0 : p) : asteroids[i];
                // update current asteroids
                asteroids[i] = result;
                // no asteroid
                // recorder is Empty(previous should not be put, as it is same as result), do not change the direction to avoid putting previous value into recorder.
                // then curdir is last dir.
                if(result == 0 || recorder.isEmpty()) break;
                // update curdir
                curdir = result >= 0 ? (result == 0 ? 0 : 1) : -1;
                // check last previous value and update previous dirction. to check in while loop
                p = recorder.pollLast();
                predir = p > 0 ? 1 : -1;
            }
            // no asteroid cotinue to check next one;
            if(asteroids[i] == 0) continue;
            // break out of while by no collision, put in previous value retrived in last time
            if(predir == curdir || (predir == -1 && curdir == 1)){
                recorder.offer(p);
            }
            // put updated asteroids[i]
            recorder.offer(asteroids[i]);
        }
        int size  = recorder.size();
        int[] ans = new int[size];
        for(int i = 0; i < size; ++i){
            ans[i] = recorder.poll();
        }
        return ans;
    }
}

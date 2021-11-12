//Best Solution: collect at every step. step = target[cur] - target[cur - 1]
class Solution {
    public int minNumberOperations(int[] target) {
        
        int ans = target[0];
        
        for(int i = 1 ; i < target.length ; i++) {
            ans += Math.max(target[i] - target[i-1],0);
        }
        
        return ans;
        
    }
}
//My Solution: O(n) Climb to peak and with it is about to go down, collect step before going down then update min
class Solution {
    public int minNumberOperations(int[] target) {
        int len = target.length;
        if(len == 0) return 0;
        int ans = target[0];
        int min = target[0];
        for(int i = 1; i < len; ++i){
            if(target[i] < target[i - 1]){
                ans += target[i - 1] - min;
                min = target[i];
            }
        }
        ans += target[len - 1] - min;
        return ans;
    }
}
//My Solution: Monotone stack only record in ascending order only ans += new steps when meet higher number
class Solution {
    public int minNumberOperations(int[] target) {
        Stack<Integer> sta = new Stack();
        int ans = 0;
        sta.push(0);
        for(int i : target){
            if(i > sta.peek()){
                ans += i - sta.peek();
                sta.push(i);
                continue;
            }
            while(sta.peek() >= i){
                sta.pop();
            }
            sta.push(i);
        }
        
        return ans;
    }
}

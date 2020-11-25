__________________________________________________________________________________Best Solution________________________________________________________________________
// do not need hashset to record repeat, if the last is not even or 5, it is bound to be able to find such number divisble by K
class Solution {
    public int smallestRepunitDivByK(int K) {
        int last = K % 10;
        if(last % 2 == 0 || last == 5){
            return -1;
        }
        
        int cur = 1, reminder = -1;
        int ans = 0;
        while(cur < K){
            cur = cur * 10 + 1;
            ++ans;
        }
        
        while(reminder != 0){
            reminder = cur % K;
            ++ans;
            cur = reminder * 10 + 1;
        }
        return ans;
    }
}
____________________________________________________________________________________My Solution________________________________________________________________________
class Solution {
    public int smallestRepunitDivByK(int K) {
        int last = K % 10;
        if(last % 2 == 0 || last == 5){
            return -1;
        }
        
        int cur = 1, reminder = 0;
        int ans = 0;
        while(cur < K){
            cur = cur * 10 + 1;
            ++ans;
        }
        
        HashSet<Integer> repeat = new HashSet();
        while(repeat.add(reminder)){
            reminder = cur % K;
            ++ans;
            cur = reminder * 10 + 1;
        }
        return reminder == 0? ans : -1;
    }
}

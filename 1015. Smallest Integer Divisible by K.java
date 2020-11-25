__________________________________________________________________________________Best Solution________________________________________________________________________
// do not need hashset to record repeat, if the last is not even or 5, it is bound to be able to find such number divisble by K
// https://www.youtube.com/watch?v=AxVUCzee-XI 7:19
// Assume K is not divisible by such onesNumber
// There will only be K - 1 reminders at most
// As we check endlessly, there will be some two numbers has same reminder after divided by K we call them n1 and n2
// f(n1) - f(n2) is divisble by K for sure as they have same reminder
// and f(n1) - f(n2) will be divisible by 10 (eg, 1111, 11 -> 1100)
// conclusively if K is times of 10(ie, divsible by 2 or 5), it will be able to find such numbers that f(n1) - f(n2) divisible by 10 so it will not be able to find onesNumber
// 2 and 5 is only prime factor number in 10
class Solution {
    public int smallestRepunitDivByK(int K) {
        if(K % 2 == 0 || K % 5 == 0){
            return -1;
        }
        
        int cur = 1, reminder = -1, ans = 0;
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

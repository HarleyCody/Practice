//My Solution: Swap can start with 0 and with 1, if diff is greater than 1 than no solution
//num1 > num0 then only return swap1 / 2;
//num0 > num1 then only return swap0 / 2;
//num1 == num0 then return min(swap1, swap0) / 2;
class Solution {
    public int minSwaps(String s) {
        char[] chs = s.toCharArray();
        int num1 = 0;
        int num0 = 0;
        for(char c : chs){
            if(c == '0'){
                ++num0;
            }else{
                ++num1;
            }
        }
        if(Math.abs(num1 - num0) > 1) return -1;
        int cur0 = 0;
        int cur1 = 1;
        int swap0 = 0;
        int swap1 = 0;
        for(char c : chs){
            if(c - '0' != cur0){
                ++swap0;
            }
            if(c - '0' != cur1){
                ++swap1;
            }
            cur0 = 1 - cur0;
            cur1 = 1 - cur1;
        }
        int swap = Math.min(swap0, swap1);
        if(num1 > num0){
            return swap1 >> 1;
        }else if(num0 > num1){
            return swap0 >> 1;
        }
        return Math.min(swap1, swap0) >> 1;
    }
}

________________________________________________________________________________Best Solution________________________________________________________________________
//O(1) Math
//https://leetcode.com/problems/add-digits/solution/
class Solution {
    public int addDigits(int num) {
        if (num == 0) return 0;
        if (num % 9 == 0) return 9;
        return num % 9;
    }
}
_________________________________________________________________________________My Solution________________________________________________________________________
// iteration O(logN) N is number of digits
class Solution{
    public int addDigits(int num) {
        int next = 0;
        while(num >= 10){
            next *= 10;
            next += num % 10;
            num /= 10;
            if(num < 10){
                next += num;
                num = next;
                next = 0;
            }
        }
        return num;
    }
}

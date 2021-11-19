//My Solution: based on the explaination for test case, start from 9 then 22, 34, 45, 55, 64, 72, 79, 85, 90, 94, 97, 99, and 100.
//Diff between then is 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13. (ans is 14 times)
//So start from 0 when curFloor is larger than target, that is the min steps
class Solution {
    public int twoEggDrop(int n) {
        int ans = 0;
        int curFloor = 0;
        int diff = 1;
        while(curFloor < n){
            curFloor += diff;
            ++diff;
            ++ans;
        }
        return ans;
    }
}

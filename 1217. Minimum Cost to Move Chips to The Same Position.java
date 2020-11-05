___________________________________________________________________________Best Solution_____________________________________________________________________________
// odd can go to odd pos without cost, even can do the same
// cost happens when odd to to even or even go to odd
// So it is determined by number of odd pos and even pos
class Solution {
    public int minCostToMoveChips(int[] position) {
        int even_cnt = 0;
        int odd_cnt = 0;
        for (int i : position) {
            if (i % 2 == 0) {
                even_cnt++;
            } else {
                odd_cnt++;
            }
        }
        return Math.min(odd_cnt, even_cnt);
    }
}
_____________________________________________________________________________My Solution_____________________________________________________________________________
// try every pos(brute froce)
class Solution {
    public int minCostToMoveChips(int[] position) {
        int ans = Integer.MAX_VALUE;
        
        for(int p : position){
            ans = Math.min(moveTo(p, position), ans);
        }
        
        return ans;
    }
    
    private int moveTo(int tar, int[] pos){
        int ans = 0;
        for(int p : pos){
            int diff = Math.abs(tar - p);
            ans += diff % 2;
        }
        
        return ans;
    }
}

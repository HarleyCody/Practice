_______________________________________________________Best Solution(Dp + bitmask)______________________________________________________
https://leetcode.com/problems/maximum-students-taking-exam/discuss/503686/A-simple-tutorial-on-this-bitmasking-problem

class Solution {
    public int maxStudents(char[][] s) {
        int m = s.length, n = s[0].length;
        int[] mask = new int[m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // start with zero, move to jth in ith row and record state. If valid(1) else 0;
                mask[i] =(mask[i] << 1) + (s[i][j] == '.' ? 1 : 0);
            }
        }
        
        int state = (1 << n);
        int[][] dp = new int[m][state];
        
        int ans = 0;

        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
            for (int j = 0; j < state; j++) {
                // j has seat and there is students in the left
                if( (j & mask[i]) == j && ((j >> 1) & j) == 0 ) {
                    if (i == 0) {
                        // first state of a row
                        dp[i][j] = Integer.bitCount(j);
                    } else {
                        // dervies from above
                        for (int k = 0; k < state; k++) {
                            // previous state is valid
                            if ((dp[i - 1][k] != -1)
                                && (((k >> 1) & j) == 0) 
                                && (k & (j >> 1)) == 0) {
                                
                                dp[i][j] = Math.max(dp[i][j], dp[i-1][k] + Integer.bitCount(j));
                            }
                        }
                    }
                    // choose the max
                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }
        return ans;
    }
}
_____________________________________________________My Solution(DFS + HashSet) TLE______________________________________________________
class Solution {
    int[][] memo;
    int ans = 0, row, col;
    int[][] dirs = {{-1, -1}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 1}};
    public int maxStudents(char[][] seats) {
        row = seats.length;
        if(row == 0) return 0;
        col = seats[0].length;
        
        HashSet<Integer> avaSeats = new HashSet();
        for(int i = 0; i < row; ++i){
            for(int j = 0; j < col; ++j){
                if(seats[i][j] == '.'){
                    avaSeats.add(i * col + j);
                }
            }
        }
        
        int size = avaSeats.size();
        if(size == 0) return 0;
        memo = new int[size][row * col];
        findSeats(avaSeats, 0);
        return ans;
    }
    private void findSeats(HashSet<Integer> seats, int cur){
        if(seats.size() == 0){
            //System.out.println("ans is " + cur);
            ans = Math.max(ans, cur);
            return;
        }
        for(Integer s : seats){
            int x = s / col;
            int y = s % col;
            HashSet<Integer> nextSeats = new HashSet(seats);
            nextSeats.remove(s);
            for(int i = 0; i < 6; ++i){
                int nx = x + dirs[i][0];
                int ny = y + dirs[i][1];
                if(nx < 0 || ny < 0 || nx == row || ny == col)continue;
                //System.out.println("chose " + x + " " + y + "remove " + nx + " " + ny);
                nextSeats.remove(nx * col + ny);
            }
            findSeats(nextSeats, cur + 1);
        }
        return;
    }
}

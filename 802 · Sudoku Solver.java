//My Solution: DFS check with boolean array
public class Solution {
    boolean[][] rows;
    boolean[][] cols;
    boolean[][] cell;
    public void solveSudoku(int[][] board) {
        rows = new boolean[9][10];
        cols = new boolean[9][10];
        cell = new boolean[9][10];
        List<int[]> blank = new ArrayList<int[]>();
        for(int r = 0; r < 9; ++r){
            for(int c = 0; c < 9; ++c){
                if(board[r][c] == 0){
                    blank.add(new int[]{r, c});
                    continue;
                }
                rows[r][board[r][c]] = true;
                cols[c][board[r][c]] = true;
                cell[r / 3 * 3 + c / 3][board[r][c]] = true;
            }
        }

        dfs(board, blank, 0);
    }
    private boolean dfs(int[][] board, List<int[]> blank, int bIdx){
        if(bIdx == blank.size()) return true;
        int[] pos = blank.get(bIdx);
        int r = pos[0];
        int c = pos[1];
        for(int i = 1; i < 10; ++i){
            if(rows[r][i] || cols[c][i] || cell[r / 3 * 3 + c / 3][i]) continue;
            board[r][c] = i;
            rows[r][i] = true;
            cols[c][i] = true;
            cell[r / 3 * 3 + c / 3][i] = true;
            if(dfs(board, blank, bIdx + 1)) return true;
            board[r][c] = 0;
            rows[r][i] = false;
            cols[c][i] = false;
            cell[r / 3 * 3 + c / 3][i] = false;
        }
        return false;
    }
}

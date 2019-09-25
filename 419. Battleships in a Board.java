_______________________________________________________My Solution(fills follow up)____________________________________________
class Solution {
    // find a boat, one pass with O(1) space 
    // if its [i - 1][j] is not boat or its[i][j - 1] is not boat, ans++
    int ans = 0;
    public int countBattleships(char[][] board) {

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                // left top cornor
                if(board[i][j] == 'X'){
                    if(0 == i && 0 == j){
                        ans++;
                    }
                    else{
                        // top most || left most || middle 
                        if((i == 0 && board[i][j - 1] != 'X') || 
                           (j == 0 && board[i - 1][j] != 'X') ||
                           (i != 0 && j != 0 && board[i][j - 1] != 'X' && board[i - 1][j] != 'X')){
                            ans++;
                        }
                    }
                }
            }
        }
        return ans;
    }
______________________________________________________My Solution______________________________________________________________
class Solution {
    // find a boat, sink it. terminate by water around(if it's '.' cannot sink, just return)
    int ans = 0;
    public int countBattleships(char[][] board) {

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == 'X'){
                    ans++;
                    dfs(board, i , j);
                }
            }
        }
        return ans;
    }
    private void dfs(char[][] board, int x, int y){
        if(0 > x || x >= board.length || y < 0 || y >= board[0].length) return;
        if(board[x][y] == '.') return;
        board[x][y] = '.';
        dfs(board, x, y + 1);
        dfs(board, x + 1, y);   
    }
}

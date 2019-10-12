class Solution {
    // extend exit, all 'O' node connect to exit can be 'O' others should be 'X'
    // dfs start at element 'O' in boarder column or row(0, board.length, board[0].length);
    // dfs dirs
    int[][] dirs =  new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    // chang according to old board;
    char[][] newBoard ;
    public void solve(char[][] board) {
        if(board.length == 0 || board[0].length == 0) return;
        // intialize newBoard
        newBoard = new char[board.length][board[0].length];
        for(int i = 0; i < board.length; i++){
            Arrays.fill(newBoard[i],'X');
        }
        // dfs from '0' at column 0 and column board[0].length - 1
        for(int i = 0; i < board.length; i++){
            if(board[i][0] == 'O'){
                infect(board, i , 0);
            }
            if(board[i][board[0].length - 1] == 'O'){
                infect(board, i, board[0].length - 1);
            }
        }
        // dfs from '0' in row 0 and row board.length - 1
        for(int i = 0; i < board[0].length; i++){
            if(board[0][i] == 'O'){
                infect(board, 0, i);
            }
            if(board[board.length - 1][i] == 'O'){
                infect(board, board.length - 1, i);
            }
        }
        // copy newBoarder to old.
        for(int i = 0; i < board.length; i++){
            System.arraycopy(newBoard[i], 0, board[i], 0, board[i].length);
        }
    }
    // dfs record exit, every '0' node connect to boarder can be exit.
    private void infect(char[][] board, int x, int y){
        if(board[x][y] == 'X') return;
        newBoard[x][y] = 'O' ;
        for(int i = 0; i < 4; i++){
            int newX = x + dirs[i][0];
            int newY = y + dirs[i][1];
            // newBoard[newX][newY] != 0 in case of stackoverflow.
            if(newX < board.length && 0 <= newX && newY < board[0].length && newY >=0
               && newBoard[newX][newY] != 'O' && board[newX][newY] != 'X'){
                infect(board, newX, newY);
            }
        }
    }
}

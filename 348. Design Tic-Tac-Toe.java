___________________________________________________________Better Solution_______________________________________________________________
class TicTacToe {
    int[] rows;
    int[] column;
    int dia, cdia;
    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        rows = new int [n];
        column = new int[n];
        dia = 0;
        cdia = 0;
    }
    
    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    public int move(int row, int col, int player) {
        // use four counters to record chess, player1 +1, player2 -1. only abs of counters == n then the winner is player. 
        int move = player == 1 ? 1 : -1;
        rows[row] += move;
        column[col] += move;
        
        if(row == col) dia += move;
        int n = rows.length;
        if(row + col == n - 1) cdia += move;
        
        return (Math.abs(rows[row]) == n || Math.abs(column[col]) == n 
                || Math.abs(dia) == n || Math.abs(cdia) == n) ? 
            player : 0;
    }
}
___________________________________________________________My Solution____________________________________________________________________
class TicTacToe {
    int [][] table;
    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        table = new int [n][n];
    }
    
    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    public int move(int row, int col, int player) {
        table[row][col] = player;
        if(checkwin(row,col,player, table.length))
            return player;
        return 0;
    }
    public boolean checkwin(int row, int col, int player, int n){
        int i = 0, j = 0;
        int r = 0, c = 0, diagonal = 0, cdiagonal = 0;
        for(i = 0; i < n; ++i){
            if(table[i][col] == player) ++r;
            if(table[row][i] == player) ++c;
            if(r == n || c == n) return true;
        }
        
        if(row == col){
            for(i = 0, j = 0; i < n; ++i, ++j){
                if(table[i][j] == player) ++diagonal;
            }
            if(diagonal == n ) return true;
        }
        
        if(row + col == n - 1){
            for(i = 0, j = n - 1; i < n; ++i, --j){
                if(table[i][j] == player) ++cdiagonal;
            }
            if(cdiagonal == n) return true;
        }
        return false;
    }
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */

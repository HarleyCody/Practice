class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board[click[0]][click[1]] == 'M') board[click[0]][click[1]] = 'X';
        else
            updateBoard(board, click[0], click[1]);
        return board;
    }
    
    private void updateBoard(char[][]board, int row, int col){
        // only update valid empty grid
        if(row < 0 || row == board.length  ||
           col < 0 || col == board[0].length || board[row][col] != 'E') return;
        
        int num = countMine(row, col, board);
        // surround by mine, update to num, stop;
        if(num > 0){
            board[row][col] = (char)(num + 48);
        }
        // Empty grid, reveal to 'B', recursive search neighbor(8 gird)
        else{
            board[row][col] = 'B';
            for(int i = -1; i < 2; ++i){
                for(int j = -1; j < 2; ++j){
                    if(i == 0 && j == 0) continue;
                    updateBoard(board, row + i, col + j);
                }
            }
        }
    }
    private int countMine(int row, int col, char[][]board){     
        int num = 0;
        for(int i = -1; i < 2; ++i){
            for(int j = -1; j < 2; ++j){
                if((i == 0 && j == 0) 
                   || row + i < 0 || row + i >= board.length 
                   || col + j < 0 || col + j >= board[0].length ) continue;
                if(board[row + i][col + j] == 'M' || board[row + i][col + j] == 'X'){
                    ++num;
                } 
            }
        }
        return num;
    }
}

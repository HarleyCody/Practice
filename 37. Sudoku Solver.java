______________________________________________________________________My Solution__________________________________________________________________________________________
// improve hashset to boolean[9][9]
class Solution {
    boolean[][] rows = new boolean[9][9];
    boolean[][] cols = new boolean[9][9];
    boolean[][] boxs = new boolean[9][9];

    public void solveSudoku(char[][] board) {
        
        for(int r = 0; r < 9; ++r){
            for(int c = 0; c < 9; ++c){
                if(board[r][c] == '.'){
                    continue;
                }
                int ca = board[r][c] - '1';
                rows[r][ca] = true;
                cols[c][ca] = true;
                int bX = r / 3, bY = c / 3;
                int bId = bX * 3 + bY;
                boxs[bId][ca] = true;
            }
        }
        
        fill(board, 0, 0);
    }
    
    private boolean fill(char[][] board, int r, int c){
        int[] nPos = getNextPos(r, c);
        if(board[r][c] != '.'){
            if(nPos[0] == 9){
                return true;
            }else{
                return fill(board, nPos[0], nPos[1]);
            }
        }
        
        int bX = r / 3, bY = c / 3;
        int b = bX * 3 + bY;
        for(int ca = 0; ca < 9; ++ca){
            if(rows[r][ca] || cols[c][ca] || boxs[b][ca]){
                continue;
            }
            
            rows[r][ca] = true;
            cols[c][ca] = true;
            boxs[b][ca] = true;
            board[r][c] = (char)(ca + '1');
            if(nPos[0] == 9 || fill(board, nPos[0], nPos[1])){
                return true;
            }
            rows[r][ca] = false;
            cols[c][ca] = false;
            boxs[b][ca] = false;
        }
        board[r][c] = '.';
        
        return false;
    }
    
    private int[] getNextPos(int r, int c){
        ++c;
        r += c / 9;
        c %= 9;
        
        return new int[]{r, c};
    }
}
______________________________________________________________________My Solution__________________________________________________________________________________________
// backtrack, use hashset to record number in row col and box
// hashset can be improved as boolean[][] as length is fixed as 9
class Solution {
    HashSet<Character>[] rows = new HashSet[9];
    HashSet<Character>[] cols = new HashSet[9];
    HashSet<Character>[] boxs = new HashSet[9];

    public void solveSudoku(char[][] board) {
        
        for(int i = 0; i < 9; ++i){
            rows[i] = new HashSet();
            cols[i] = new HashSet();
            boxs[i] = new HashSet();
        }
        for(int r = 0; r < 9; ++r){
            for(int c = 0; c < 9; ++c){
                if(board[r][c] == '.'){
                    continue;
                }
                
                rows[r].add(board[r][c]);
                cols[c].add(board[r][c]);
                
                int bX = r / 3, bY = c / 3;
                int bId = bX * 3 + bY;
                boxs[bId].add(board[r][c]);
            }
        }
        
        fill(board, 0, 0);
    }
    
    private boolean fill(char[][] board, int r, int c){
        int[] nPos = getNextPos(r, c);
        if(board[r][c] != '.'){
            if(nPos[0] == 9){
                return true;
            }else{
                return fill(board, nPos[0], nPos[1]);
            }
        }
        
        int bX = r / 3, bY = c / 3;
        int b = bX * 3 + bY;
        for(char ca = '1'; ca <= '9'; ++ca){
            if(rows[r].contains(ca) || cols[c].contains(ca) || boxs[b].contains(ca)){
                continue;
            }
            rows[r].add(ca);
            cols[c].add(ca);
            boxs[b].add(ca);
            board[r][c] = ca;
            if(nPos[0] == 9 || fill(board, nPos[0], nPos[1])){
                return true;
            }
            rows[r].remove(ca);
            cols[c].remove(ca);
            boxs[b].remove(ca);
        }
        board[r][c] = '.';
        
        return false;
    }
    
    private int[] getNextPos(int r, int c){
        ++c;
        r += c / 9;
        c %= 9;
        
        return new int[]{r, c};
    }
}

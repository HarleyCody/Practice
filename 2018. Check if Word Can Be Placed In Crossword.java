//Best Solution: find iteratively, from left to right and top to bot
// Check right to left and bot to top by reverse word
class Solution {
    public boolean placeWordInCrossword(char[][] board, String word) {
        return placeWord(board, word) || placeWord(board, new StringBuilder(word).reverse().toString());    
    }
    
    private boolean placeWord(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        char[] wordChars = word.toCharArray();
        int l = wordChars.length;
        // horizontal search
        if (l <= n) {
            for (int r = 0; r < m; ++r) {
                int i = 0, k = 0;
                boolean broken = false;
                while (i < n) {
                    if (broken) {
                        k = 0;
                        while (i < n && board[r][i] != '#') ++i;
                        ++i;
                        broken = false;
                    }else if (board[r][i] == wordChars[k] || board[r][i] == ' ') {
                        ++k;
                        ++i;
                        if (k == l) {
                            if (i == n || board[r][i] == '#') return true;
                            broken = true;
                        } 
                    } else {
                        broken = true;
                    }
                }
            }
        }
        // vertical search
        if (l <= m) {
            for (int c = 0; c < n; ++c) {
                int i = 0, k = 0;
                boolean broken = false;
                while (i < m) {
                    if (broken) {
                        k = 0;
                        while (i < m && board[i][c] != '#') ++i;
                        ++i;
                        broken = false;
                    }else if(board[i][c] == wordChars[k] || board[i][c] == ' ') {
                        ++k;
                        ++i;
                        if (k == l) {
                            if (i == m || board[i][c] == '#') return true;
                            broken = true;
                        } 
                    } else {
                        broken = true;
                    }
                }
            }
        }
        return false;
    }
}
//My Solution: Start from empty and check iteratively
class Solution {
    int R;
    int C;
    char[] chs;
    char[][] board;
    public boolean placeWordInCrossword(char[][] board, String word) {
        R = board.length;
        C = board[0].length;
        chs = word.toCharArray();
        this.board = board;
        
        int[] dirs = new int[]{-1, 0, 1, 0, -1};
        for(int r = 0; r < R; ++r){
            for(int c = 0; c < C; ++c){
                if(board[r][c] == '#')continue;
                for(int i = 0; i < 4; ++i){
                    int px = r - dirs[i];
                    int py = c - dirs[i + 1];
                    if(0 <= px && px < R && 0 <= py && py < C && board[px][py] != '#'){
                        continue;
                    }
                    if(find(r, c, dirs[i], dirs[i + 1], 0)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private boolean find(int r, int c, int dx, int dy, int idx){
        while(idx < chs.length){
            if(r < 0 || r == R || c < 0 || c == C || (board[r][c] != ' ' && board[r][c] != chs[idx])) return false;
            r += dx;
            c += dy;
            ++idx;
        }
        return r < 0 || r == R || c < 0 || c == C || board[r][c] == '#';
    }
}
//My Solution: Start from every empty and dfs
class Solution {
    int R;
    int C;
    
    String word;
    char[][] board;
    public boolean placeWordInCrossword(char[][] board, String word) {
        R = board.length;
        C = board[0].length;
        this.word = word;
        this.board = board;
        
        int[] dirs = new int[]{-1, 0, 1, 0, -1};
        for(int r = 0; r < R; ++r){
            for(int c = 0; c < C; ++c){
                if(board[r][c] == '#')continue;
                for(int i = 0; i < 4; ++i){
                    int px = r - dirs[i];
                    int py = c - dirs[i + 1];
                    if(0 <= px && px < R && 0 <= py && py < C && board[px][py] != '#'){
                        continue;
                    }
                    if(find(r, c, dirs[i], dirs[i + 1], 0)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private boolean find(int r, int c, int dx, int dy, int idx){
        int nx = r + dx;
        int ny = c + dy;
        if(idx == word.length()){
            return r < 0 || r == R || c < 0 || c == C || board[r][c] == '#';
        }
        if(r < 0 || r == R || c < 0 || c == C || (board[r][c] != ' ' && board[r][c] != word.charAt(idx))) return false;
        return find(nx, ny, dx, dy, idx + 1);
    }
}

//Best Solution: filled row by row use the same coordinate transfer, collect stone and the other point use to paint. meet at obstacle
class Solution {
    public char[][] rotateTheBox(char[][] box) {
        int N = box.length;
        int M = box[0].length;
        char[][] boxT = new char[M][N];
        
        for(int i=N-1; i>=0; i--) {
            fillBox(box[i], boxT, N, M, i);
        }
        return boxT;
    }
    
    private void fillBox(char[] row, char[][] box, int N, int M, int idx) {
        int i = M - 1;
        int j = M - 1;
        int stones = 0;
        while(i>=0) {
            stones = 0;
            while(i >= 0 && row[i] != '*') {
                if(row[i] == '#') stones++;
                i--;
            }
            while(j > i) {
                if(stones > 0) {
                    box[j][N - idx - 1] = '#';
                    stones--;
                }
                else box[j][N - idx - 1] = '.';
                j--;
            }
            if(i>=0) {
                box[j][N-idx-1] = '*';
                j--;
                i--;
            }
        }
    }
}
//My Solution: record number of rocks in the same row and transfer it into col * row
//Coordiante transfer, ogR = row - 1 - nCol, ogCol = newRow;
//update number of rocks while tranfering from bot to up and left to right; stop update while(number of rock == 0);
class Solution {
    public char[][] rotateTheBox(char[][] box) {
        int row = box.length;
        int col = box[0].length;
        int[][] num = new int[row][col];
        for(int r = 0; r < row; ++r){
            int numOfStone = 0;
            for(int c = 0; c < col; ++c){
                if(box[r][c] == '.')continue;
                if(box[r][c] == '#'){
                    ++numOfStone;
                }else{
                    if(c > 0 && numOfStone > 0){
                        num[r][c - 1] = numOfStone;
                    }
                    num[r][c] = -1;
                    numOfStone = 0;
                }
            }
            if(numOfStone > 0){
                num[r][col - 1] = numOfStone;
            }
        }
        
        char[][] ans = new char[col][row];
        for(int r = col - 1; 0 <= r; --r){
            for(int c = 0; c < row; ++c){
                int ogR = row - c - 1;
                int ogC = r;
                if(num[ogR][ogC] == 0){
                    ans[r][c] = '.';
                }else if(num[ogR][ogC] == -1){
                    ans[r][c] = '*';
                }else{
                    ans[r][c] = '#';
                    if(r > 0 && num[ogR][ogC] > 1)num[ogR][ogC - 1] = num[ogR][ogC] - 1;
                }
            }
        }
        
        return ans;
    }
}

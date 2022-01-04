//My Solution: record the freq for each col and row and check b that has row == 1 and col == 1
class Solution {
    public int findLonelyPixel(char[][] picture) {
        int row = picture.length;
        int col = picture[0].length;
        
        int[] rowCount = new int[row];
        int[] colCount = new int[col];
        int ans = 0;
        for(int r = 0; r < row; ++r){
            for(int c = 0; c < col; ++c){
                if(picture[r][c] == 'B'){
                    ++rowCount[r];
                    ++colCount[c];
                }
            }
        }
        
        for(int r = 0; r < row; ++r){
            for(int c = 0; c < col; ++c){
                if(picture[r][c] == 'B'){
                    if(rowCount[r] == 1 && colCount[c] == 1){
                        ++ans;
                    }
                }
            }
        }
        
        return ans;
    }
}

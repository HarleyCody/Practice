class Solution {
    public boolean validTicTacToe(String[] board) {
        int countX = 0, sum = 0, d1 = 0, d2 = 0;
        int[] col = new int[3];
        int[] row = new int[3];
        for(int i = 0 ; i < 3; ++i){
            char[] chs = board[i].toCharArray();
            for(int j = 0; j < 3; ++j){
                if(chs[j] == 'X'){
                    ++countX;
                    ++sum;
                    ++col[j];
                    ++row[i];
                    if(j == i)++d1;
                    if(i + j == 2)++d2;
                }else if(chs[j] == 'O'){
                    ++sum;
                    --col[j];
                    --row[i];
                    if(j == i)--d1;
                    if(i + j == 2)--d2;
                }
            }
        }
        int countO = sum - countX;
        if(countO > countX || countO < countX - 1)return false;
        if(sum % 2 == 1){
            if(d1 == -3 || d2 == -3 || col[0] == -3 || col[1] == -3 || col[2] == -3 || row[0] == -3 || row[1] == -3 || row[2] == -3){
                return false;
            }
        }else{
            if(d1 == 3 || d2 == 3 || col[0] == 3 || col[1] == 3 || col[2] == 3 || row[0] == 3 || row[1] == 3 || row[2] == 3){
                return false;
            }
        }
        return true;
    }
}

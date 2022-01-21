//Best Solution: flip all the 0 in the first row
//               check all other rows has same value

class Solution {
    public boolean removeOnes(int[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;

        for(int c = 0; c < cols; c++)
        {
            if(grid[0][c] == 0)
            {
                //flip
                for(int r=0; r<rows; r++)
                {
                    grid[r][c] = grid[r][c] == 0? 1 : 0;
                }                
            }

        }


        for(int r=0; r<rows; r++)
        {
            int val = grid[r][0];
            for(int c=1; c<cols; c++)
            {
                if(grid[r][c] != val)
                {
                    return false;
                }
            }
        }

        return true;
    }
}

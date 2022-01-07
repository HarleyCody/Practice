//Best Solution:
/*
The key to this problem is understanding the following: all 1s must be directly or indirectly flipped an odd number of times and all zeros - an even. Conclusions:

Flipping a cell (and indirectly its neighbours) 1 time is equivalent to flipping them 3 / 5 / 7 ... times, and so does not flipping is equivalent to flipping 2 / 4 / 6 ... times. So it's enough to only check flipping vs. not flipping per cell.
The order of the flip does not matter.
Overall, we have rows * columns (aka N * M) cells, each has 2 options to be directly flipped or not, so O(2^(NM)) options.
The solution is streight forward - iterate all the cells recursively (which means, memory up to O(MN), calc for flip/not and return the minimal if result is not -1.
In order to avoid checking the entire matrix is all zeros each time, I keep a 1s counter and increase/decrease it when flipping by the change made by the flip (O(1))
*/
class Solution {
    public int minFlips(int[][] mat) {
        int count = 0;
        for (int r=0; r<mat.length; r++) {
            for (int c=0; c<mat[0].length; c++) {
                count += mat[r][c];
            }
        }
        return minFlips(mat, count, 0);
    }
    
    private int minFlips(int[][] mat, int count, int i) {        
        if (count == 0) return 0;
        if (i == mat.length*mat[0].length) return -1;

        int noFlipRes = minFlips(mat, count, i+1);

        int r = i/mat[0].length;
        int c = i%mat[0].length;
        count = flipAround(mat, r, c, count);
        int flipRes = minFlips(mat, count, i+1);
        count = flipAround(mat, r, c, count);
                
        if (flipRes == -1) {
            if (noFlipRes == -1) return -1;
            return noFlipRes;
        }
        flipRes++; // count the flip
        if (noFlipRes == -1) return flipRes;
        return Math.min(noFlipRes, flipRes);
    }
    
    private int flipAround(int[][] mat, int r, int c, int count) {
        count += flip(mat, r, c);
        if (r>0) count += flip(mat, r-1, c);
        if (c>0) count += flip(mat, r, c-1);
        if (r<mat.length-1) count += flip(mat, r+1, c);
        if (c<mat[0].length-1) count += flip(mat, r, c+1);
        return count;
    }
    
    private int flip(int[][] mat, int r, int c)  {
        mat[r][c] = (mat[r][c]+1)%2;
        if (mat[r][c] == 1) return 1; // less 0s
        return -1; // more 0s
    }
    
}
//My Solution: BFS with bitMask, target bitMask is 00000 ie 0, flip (r, c), (r - col, c) , (r + col, c), (r, c - 1), (r, c + 1); 
class Solution {
    public int minFlips(int[][] mat) {
        int row = mat.length;
        int col = mat[0].length;

        int start = 0;
        for(int r = row - 1; 0 <= r; --r){
            for(int c = col - 1; 0 <= c; --c){
                start |= mat[r][c];
                start <<= 1;
            }
        }
        start >>= 1;
        if(start == 0) return 0;
        Queue<Integer> q = new LinkedList();
        Set<Integer> visited = new HashSet();
        q.offer(start);
        int steps = 0;
        while(!q.isEmpty()){
            int size = q.size();
            while(size-- > 0){
                int bitMask = q.poll();
                for(int r = 0; r < row; ++r){
                    for(int c = 0; c < col; ++c){
                        int nBitMask = flip(bitMask, r, c, col, row);
                        if(nBitMask == 0){
                            return steps + 1;
                        }
                        if(!visited.add(nBitMask)) continue;
                        q.offer(nBitMask);
                    }
                }
            }
            ++steps;
        }
        return -1;
    }
    
    private int flip(int ogBitMask, int r, int c, int col, int row){
        int bitMask = ogBitMask;
        int curIdx = r * col + c;
        bitMask ^= 1 << curIdx;
        
        if(r > 0){
            bitMask ^= 1 << (curIdx - col);
        }
        if(r < row - 1){
            bitMask ^= 1 << (curIdx + col);
        }
        
        if(c < col - 1){
            bitMask ^= 1 << (curIdx + 1);
        }
        if(c > 0){
            bitMask ^= 1 << (curIdx - 1);
        }
        
        return bitMask;
    }
    
}

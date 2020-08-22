________________________________________________________________________Best Solution________________________________________________________________________
class Solution {
    // find rect and find point
    // area should be treated as normal distributed
    // collect all points and choose one and caluclate it from rectangle
    // target - base means the point that is chosed at top left point of rect, calcualte its idx in the base rect, which is rect[0] and rect[1];
    private final int[] sums;
    private final int[][] rects;
    private final Random rand = new Random();
    
    // for rects = [[1, 1, 2, 4], [3, 2, 5, 4], [2, 5, 5, 6]]
    // sum is [8, 17, 25]
    
    public Solution(int[][] rects){
        this.rects = rects;
        sums = new int[rects.length];
        int sum = 0, index;
        for (int i = 0; i < rects.length; i++)
        {
            int[] rect = rects[i];
            sum += (rect[2] - rect[0] + 1) * (rect[3] - rect[1] + 1);
            sums[i] = sum;
        }
    }
    
    public int[] pick(){
        int lo = 0, hi = sums.length - 1;
        int target = rand.nextInt(sums[sums.length - 1]);
        
        while (lo < hi){
            int mid = lo + (hi - lo) / 2;
            
            if (target < sums[mid]) hi = mid;
            else lo = mid + 1;
        }
        
        int[] rect = rects[lo];
        int width = rect[2] - rect[0] + 1;
        int height = rect[3] - rect[1] + 1;
        int base = sums[lo] - width * height;
        return new int[]{rect[0] + (target - base) % width, rect[1] + (target - base) / width};
    }
}

____________________________________________________________________________________Best Solution________________________________________________________________________
https://leetcode.com/problems/rectangle-area-ii/discuss/137914/C%2B%2BPython-Discretization-and-O(NlogN)
____________________________________________________________________________________Best Solution________________________________________________________________________
// O(n^2)
// find overlap between every pair of rectangles
class Solution {
    long M= (long)1e9+7;
    public int rectangleArea(int[][] rectangles) {
        long res= 0;
        // add all areas
        for (int[] r: rectangles) res+=areaOf(r[0], r[1], r[2], r[3]);
        // extract overlap area;
        for (int i=0; i<rectangles.length; i++){
            long overlap= overlap(rectangles, rectangles[i], i+1);
            res=(res-overlap+M)%M;
        }
        return (int)res;
    }
    public long areaOf(int x1, int y1, int x2, int y2){
        return (long)(x2-x1)*(y2-y1);
    }
    public long overlap(int[][] rectangles, int[] a, int idx){
        if (idx==rectangles.length) return 0;
        int[] b= rectangles[idx++];
        int left= Math.max(a[0], b[0]), right= Math.min(a[2], b[2]), down= Math.max(a[1], b[1]), up= Math.min(a[3], b[3]);
        // no overlap between a and idx, check overlap with next;
        if (left>=right || up<=down) return overlap(rectangles, a, idx);
        
        // two rec overlaps, res is overlap between a and b
        long res=areaOf(left, down, right, up);
        
        // continue collect overlap area with current rect - overlap and rest recs;
        // new int build a max rect from a to detect overlap with rest of recs
        // b is at right if a, pick max area of a without overlap area to continue calculate
        if (a[0]<b[0]) res=(res+overlap(rectangles, new int[]{a[0], a[1], b[0], a[3]}, idx))%M;
        // b is at left of a
        if (b[2]<a[2]) res=(res+overlap(rectangles, new int[]{b[2], a[1], a[2], a[3]}, idx))%M;
        // b is at top of a
        if (a[1]<b[1]) res=(res+overlap(rectangles, new int[]{left, a[1], right, b[1]}, idx))%M;
        // b is at bottom of a
        if (b[3]<a[3]) res=(res+overlap(rectangles, new int[]{left, b[3], right, a[3]}, idx))%M;
        return res;
    }
}

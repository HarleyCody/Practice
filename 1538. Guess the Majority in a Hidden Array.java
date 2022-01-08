/**
 * // This is the ArrayReader's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface ArrayReader {
 *   public:
 *     // Compares 4 different elements in the array
 *     // return 4 if the values of the 4 elements are the same (0 or 1).
 *     // return 2 if three elements have a value equal to 0 and one element has value equal to 1 or vice versa.
 *     // return 0 : if two element have a value equal to 0 and two elements have a value equal to 1.
 *     public int query(int a, int b, int c, int d);
 *
 *     // Returns the length of the array
 *     public int length();
 * };
 */
//Best Solution: Find out the first two pointers by comparing five query composed by first five number. 
//               then count the freq x and y return accordingly
class Solution {
    public int guessMajority(ArrayReader reader) {
        int x0 = 0, x1 = 0, yi = 0, cx = 0, cy = 0;
        int st = 5;
        int n = reader.length();
        
        if (reader.query(0, 1, 2, 3) == 4) {
            x0 = 0;
            x1 = 1;
            cx = 4; 
            cy = 0;
            st = 4;
        } else {
            x1 = 4;
            cx++;
            int[] val = new int[5];
            val[0] = reader.query(1, 2, 3, 4);
            val[1] = reader.query(0, 2, 3, 4);
            val[2] = reader.query(0, 1, 3, 4);
            val[3] = reader.query(0, 1, 2, 4);
            val[4] = reader.query(0, 1, 2, 3);
            for (int i = 0; i < 4; ++i) {
                if (val[i] == val[4]) {
                    x0 = i;
                    cx++;
                } else {
                    yi = i;
                    cy++;
                }
            }
        }
        
        while (st < n - 1) {
            switch (reader.query(x0, x1, st, st + 1)) {
                case 4:
                    cx += 2;
                    break;
                case 0:
                    cy += 2;
                    yi = st;
                    break;
                case 2:
                    cx++;
                    cy++;
                    break;
            }
            st += 2;
        }
        
        if (st == n - 1 && Math.abs(cx - cy) < 2) {
            int val = 0;
            if (yi < x0) val = reader.query(yi, x0, x1, st);
            else if (yi < x1) val = reader.query(x0, yi, x1, st);
            else val = reader.query(x0, x1, yi, st);
            if (val == 0) {
                cy++;
            } else {
                cx++;
            }
        }
        if (cx == cy) return -1;
        return (cx > cy) ? x0 : yi;
    }
}

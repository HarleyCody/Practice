_______________________________________________________________________________Best Solution_____________________________________________________________________________________
// Calcualte corrdinates directly by gcd.
// p times of horizontal reflect
// q times of vertical reflect
class Solution {

    public int mirrorReflection(int p, int q) {
        int g = gcd(p, q);
        p /= g; p %= 2;
        q /= g; q %= 2;

        if (p == 1 && q == 1) return 1;
        return p == 1 ? 0 : 2;
    }

    public int gcd(int a, int b) {
        if (a == 0) return b;
        return gcd(b % a, a);
    }
}
________________________________________________________________________________My Solution_____________________________________________________________________________________
// simulate ray. if out of p in top just extend mirrors, hight will increase 2p;
/*

2-1
| |
 -0
| | p
2-1
| | p
 -0
*/ 
class Solution {
    public int mirrorReflection(int p, int q) {
        int[] p0 = {p, 0};
        int[] p1 = {p, p};
        int[] p2 = {0, p};
        int x = 0, y = 0;
        while(true){
            if(x == p0[0] && y == p0[1]){
                return 0;
            }
            if(x == p1[0] && y == p1[1]){
                return 1;
            }
            if(x == p2[0] && y == p2[1]){
                return 2;
            }
            
            x = p - x;
            y += q;
            
            if(y > p1[1]){
                p0[1] += 2 * p;
                p1[1] += 2 * p;
                p2[1] += 2 * p;
            }
        }
    }
}

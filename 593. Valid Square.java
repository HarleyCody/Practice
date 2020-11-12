______________________________________________________________________________Best Solution___________________________________________________________________________
// brute force check
class Solution {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int d12 = dist(p1,p2);
        int d13 = dist(p1,p3);
        int d14 = dist(p1,p4);
        int d23 = dist(p2,p3);
        int d24 = dist(p2,p4);
        int d34 = dist(p3,p4);
        if(d12==d13)
            return validate(d12, d24, d34, d13, d14, d23);
        if(2*d12==d13)
            return validate(d12, d23, d34, d14, d13, d24);
        if(d12==2*d13)
            return validate(d13, d23, d24, d14, d12, d34);
        return false;
    }
    
    private boolean validate(int a1, int a2, int a3, int a4, int d1, int d2) {
        if(a1==0)
            return false;
        return a1==a2
            && a1==a3
            && a1==a4
            && d1==d2
            && d1==2*a1;
    }
    
    private int dist(int[] a, int[] b) {
        int dx = a[0]-b[0];
        int dy = a[1]-b[1];
        return dx*dx+dy*dy;
    }
}
_______________________________________________________________________________My Solution___________________________________________________________________________
// side can conduct points so only need to assure the relations between sides are right
class Solution {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[][] points = new int[4][2];
        points[0] = p1;
        points[1] = p2;
        points[2] = p3;
        points[3] = p4;
        
        Arrays.sort(points, (x, y) -> x[0] == y[0] ? y[1] - x[1] : x[0] - y[0]);
        int b1 = getDis(points[0], points[1]);
        int b2 = getDis(points[2], points[3]);
        int d1 = getDis(points[0], points[3]);
        int d2 = getDis(points[1], points[2]);
        return d1 == d2 && b1 == b2 && b1 + b1 == d1 && b1 != 0;
    }
    
    private int getDis(int[] p1, int[] p2){
        int x = p1[0] - p2[0];
        int y = p1[1] - p2[1];
        
        return x * x + y * y;
    }
}
_______________________________________________________________________________My Solution___________________________________________________________________________
class Solution {
    //sqaure, side and dia can confirm a square;
    //side all same, dia all same, so only two positive Integer
    //side * side + side * side = dia * dia;
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        HashSet<Integer> lines = new HashSet();
        lines.add(length(p1, p2));
        lines.add(length(p1, p3));
        lines.add(length(p1, p4));
        lines.add(length(p2, p3));
        lines.add(length(p2, p4));
        lines.add(length(p3, p4));
        
        
        if(lines.size() != 2 || lines.contains(0)){
            return false;
        }
        int side = 0, dia = 0;
        for(int i : lines){
            side = Math.min(i, side);
            dia = Math.min(i, dia);
            if(side != 0 && dia != 0){
                break;
            }
        }
        
        return 2 * side == dia;
    }
    
    private int length(int[] p1, int p2[]){
        int x = Math.abs(p1[0] - p2[0]);
        int y = Math.abs(p1[1] - p2[1]);
        return Math.abs(x * x + y * y);
    }
}

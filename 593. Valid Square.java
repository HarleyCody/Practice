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

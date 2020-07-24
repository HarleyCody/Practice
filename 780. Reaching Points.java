_____________________________________________________________________________Best Solution_____________________________________________________________________________
// modulo from end, otherwise stack overflow
class Solution {
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        while (sx < tx && sy < ty)
            if (tx < ty) ty %= tx;
            else tx %= ty;
        // can reach to the final point
        return sx == tx && sy <= ty && (ty - sy) % sx == 0 ||
               sy == ty && sx <= tx && (tx - sx) % sy == 0;
    }
}
____________________________________________________________________________ My Solution____________________________________________________________________________
// Stack overflow
class Solution {
    private final int a = (int)1e9 + 1;
    HashMap<Long, Boolean> mem = new HashMap();
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        return search(sx, sy, tx, ty);
    }
    
    private boolean search (int sx, int sy, int tx, int ty){
        if(sx > tx || sy > ty){
            return false;
        }
        if(sx == tx && sy == ty){
            return true;
        }
        Long hash = (long)sx * a + sy; 
        if(mem.containsKey(hash)){
            return mem.get(hash);
        }
        
        boolean rlt = search(sx + sy, sy, tx, ty) || search(sx, sx + sy, tx, ty);
        
        mem.put(hash, rlt);
        
        return rlt;
    }
}

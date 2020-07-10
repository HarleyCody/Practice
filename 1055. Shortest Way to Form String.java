________________________________________________________________________My Solution_______________________________________________________________________________________
class Solution {
    //Gready + dfs
    public int shortestWay(String source, String target) {
        char[] src = source.toCharArray();
        char[] tar = target.toCharArray();
        
        return shortestWay(src, tar, 0);
    }
    
    private int shortestWay(char[] src, char[] tar, int t){
        if(t > tar.length){
            return 0;
        }
        int nt = t;
        for(int s = 0; nt < tar.length && s < src.length; ++s){
            if(src[s] == tar[nt]){
                ++nt;
            }
        }
        if(nt == tar.length){
            return 1;
        }
        if(t == nt){
            return -1;
        }
        int next = shortestWay(src, tar, nt);
        
        return next == -1 ? -1 : 1 + next;
    }
}

class Solution {
    // matching xx, yy scenario first with cost 1;
    // matching xy, yx scenario in secnd with cost 2;
    
    // x / 2: pairs of xx, yy; cost 1
    // y / 2: pairs of yy, xx; cost 1
    // x % 2 pairs of xy, yx; cost 2
    public int minimumSwap(String s1, String s2) {
        int len = s1.length();
        
        int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
        
        for(int i = 0; i < len; ++i){
            if(s1.charAt(i) != s2.charAt(i)){
                if(s1.charAt(i) == 'x'){
                    ++x1;
                    ++y2;
                }else{
                    ++y1;
                    ++x2;
                }
            }
        }
        if((x1 + x2) % 2 == 1 || (y1 + y2) % 2 == 1)return -1;
        int num = x1 / 2 + x1 % 2 * 2 + y1 / 2;
        return num;
    }
}

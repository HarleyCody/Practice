___________________________________________________________________My Solution_________________________________________________________
class Solution {
    //from back to head, collect # to skip char
    //not equal or one of them has used up but the other one still have char remains => false;
    public boolean backspaceCompare(String S, String T) {
        char[] sChars = S.toCharArray();
        char[] tChars = T.toCharArray();
        
        int s = sChars.length - 1, t = tChars.length - 1;
        int sd = 0, td = 0;
        while(s >= 0 || t >= 0){ 
            while(s >= 0 && (sd > 0 || sChars[s] == '#')){
                if(sChars[s] == '#'){
                    ++sd;
                }else{
                    --sd;
                }
                --s;
            }
            while(t >= 0 && (td > 0 || tChars[t] == '#')){
                if(tChars[t] == '#'){
                    ++td;
                }else{
                    --td;
                }
                --t;
            }
            if(t >= 0 && s >= 0 && sChars[s] != tChars[t] ||
               (s < 0 && t >= 0 || t < 0 && s >= 0)){
                return false;
            }
            --s;
            --t;
        }
        return true;
    }
}

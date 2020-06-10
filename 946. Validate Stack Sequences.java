________________________________________________________My Solution_____________________________________________________________
class Solution {
    // number before i should be in reversed order
    // record idx of number by array
    // when curIdx > nextIdx => 
    //    next num is before cur num, it should be the prev of cur num otherwise false;
    // update prev for pushed array
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int[] indice = new int[1000];
        int len = pushed.length;
        
        for(int i = 0; i < len; ++i){
            indice[pushed[i]] = i;
        }
        
        for(int i = 0; i < len - 1; ++i){
            int curIdx = indice[popped[i]];
            int nextIdx = indice[popped[i + 1]];
            int prv = curIdx == 0 ? -1 : indice[pushed[curIdx - 1]];
            if(curIdx > nextIdx && nextIdx != prv){
                return false;        
            }
            if(prv == -1){
                continue;
            }
            
            int p = pushed[curIdx];
            for(int j = curIdx; j < len; ++j){
                if(pushed[j] != p){
                    break;
                }
                pushed[j] = pushed[prv];
            }
        }
        return true;
    }
}

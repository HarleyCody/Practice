_______________________________________________________Better Solution_________________________________________________________
/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */
public class Solution extends Relation {
    public int findCelebrity(int n) {
        int celebrity = 0;
        
        //find out candidate does not know anyone behind him
        for(int i = 1; i < n ; ++i) {
            if(knows(celebrity, i)) {
                celebrity = i;
            }
        }
        
        // check if candidate knows any one before him and everybody knows him.
        for(int i = 0; i < n; ++i){
            if(i != celebrity){
                if((i < celebrity && knows(celebrity, i)) || !knows(i, celebrity)){
                    return -1;
                }
            }
        }
        return celebrity;
        
    }
}
_______________________________________________________My Solution_____________________________________________________________
/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class Solution extends Relation {
    public int findCelebrity(int n) {
        // record outdegree;
        int[] know = new int[n];
        // record indegree;
        int[] isKnown = new int[n];
        for(int i = 0; i < n; ++i){
            for(int j = 0; j < n; ++j){
                if(i == j) continue;
                if(knows(i,j)){// i knows j
                    ++isKnown[j];
                    ++know[i];
                }
            }
        }
        for(int i = 0; i < n; ++i){
            if(know[i] > 0) continue;
            if(isKnown[i] = n - 1)return i; 
        }
        return -1;
    }
}

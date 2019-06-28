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

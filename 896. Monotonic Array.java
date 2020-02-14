// two flag to state the status of array
class Solution {
    public boolean isMonotonic(int[] A) {
        if(A.length == 0)return true;
        boolean isIncrease = false, isDecrease = false;
        int prev = A[0];
        for(int i : A){
            if(i == prev) continue;
            if(i > prev){
                if(isDecrease)return false;
                isIncrease = true;
            }else{
                if(isIncrease)return false;
                isDecrease = true;
            }
            prev = i;
        }
        return true;
    }
}

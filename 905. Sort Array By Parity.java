___________________________________________________________________Best Solution___________________________________________________________
class Solution {
    //two pointer
    // p = A[l] find a even from tail put to A[l]
    // find a odd from head put to A[r];
    // r always at even, l always at odd
    // in the second iteration, two pointers will move itself, no need for moving after swap p with r
    public int[] sortArrayByParity(int[] A) {
        int l = 0, r = A.length - 1;
        
        while(l < r){
            int p = A[l];
            while(l < r && A[r] % 2 == 1){
                --r;
            }
            A[l] = A[r];
            while(l < r && A[l] % 2 == 0){
                ++l;
            }
            A[r] = p;
        }
        return A;
    }
}

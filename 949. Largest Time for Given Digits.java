________________________________________________________________________My Solution________________________________________________________________________
//Construct case in case, start from 0 , 1 or 2, pick valid max and swap
class Solution {
    public String largestTimeFromDigits(int[] A) {
        Arrays.sort(A);
        
        if(A[0] > 2 || A[0] == 2 && (A[1] >= 4 || A[2] > 5) || A[1] > 5){
            return "";
        }
        
        if(A[0] == 2){
            int tmp = 0;
            for(int i = 3; 1 < i; --i){
                if(A[i] < 4 && A[i] > A[1]){
                    swap(A, 1, i);
                    break;
                }
            }
        }else if(A[0] == 1){
            for(int i = 3; 0 < i; --i){
                if(A[i] < 3){
                    swap(A, 0, i);
                    break;
                }
            }
            int max = A[0] == 2 ? 4 : 10;
            for(int i = 3; 1 < i; --i){
                if(A[i] < max && A[i] > A[1]){
                    swap(A, 1, i);
                }
            }      
        }else{
            for(int i = 3; 0 < i; --i){
                if(A[i] < 3){
                    swap(A, 0, i);
                    break;
                }
            }
            int max = A[0] == 2 ? 4 : 10;
            for(int i = 3; 1 < i; --i){
                if(A[i] < max && A[i] > A[1]){
                    swap(A, 1, i);
                }
            } 
        }
        if(A[3] > A[2] && A[3] < 6 || A[2] > 5){
            swap(A, 2, 3);
            if(A[2] > 5){
                swap(A, 0, 1);
                swap(A, 1, 2);
            }
        }
        StringBuilder ans = new StringBuilder();
        for(int i = 0; i < 4; ++i){
            ans.append(A[i]);
            if(i == 1){
                ans.append(':');
            }
        }
        return ans.toString();
    }
    
    private void swap(int[] A, int l, int r){
        int tmp = A[l];
        A[l] = A[r];
        A[r] = tmp;
    }
}

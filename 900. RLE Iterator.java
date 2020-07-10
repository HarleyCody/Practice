___________________________________________________________________________My Solution 1 while loop___________________________________________________________________________
// same as solution 2 but use while loop to update idx
class RLEIterator {
    int[] recorder;
    int idx, len;
    public RLEIterator(int[] A) {
        recorder = A;
        idx = 0;
        len = A.length;
    }
    
    public int next(int n) {
        while(idx < len && n != 0){
            if(recorder[idx] > n){
                recorder[idx] -= n;
                n = 0;
                return recorder[idx + 1];
            }else{
                n -= recorder[idx];
                recorder[idx] = 0;
                if(n == 0){
                    return recorder[idx + 1];
                }else{
                    idx += 2;
                }
            }
        }
        return -1;
    }
}
___________________________________________________________________________My Solution 2 recursion___________________________________________________________________________
class RLEIterator {
    // curIdx > len return -1;
    // recorder[idx] - n or n - recorder[idx]
    // return until n == 0;
    int[] recorder;
    int idx, len;
    public RLEIterator(int[] A) {
        recorder = A;
        idx = 0;
        len = A.length;
    }
    
    public int next(int n) {
        if(idx >= len){
            return -1;
        }
        if(n == 0 || recorder[idx] > n){
            recorder[idx] -= n;
            return recorder[idx + 1];
        }
        n -= recorder[idx];
        if(n == 0){
            recorder[idx] = 0;
            return recorder[idx + 1];
        }else{
            idx += 2;
            return next(n);
        }
    }
}

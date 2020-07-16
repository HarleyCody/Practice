
_______________________________________________________________________________________Best Solution______________________________________________________________________
class Solution {
    //construct from map one by one backtracking, only construct with candidate digits
    HashMap<Integer, Integer> map = new HashMap();
    int ans = 0, end;
    int[]can = new int[]{0, 1, -1, -1, -1, -1, 9, -1, 8, 6};
    public int confusingNumberII(int N) {
        end = N;
        helper(0);
        return ans;
    }
    
    private void helper(long n){
        if(isValid(n)){
            ++ans;
        }
        
        for(int i : can){
            if(i == -1){
                continue;
            }
            long next = n * 10 + i;
            if(next > end || next == 0){
                continue;
            }
            helper(next);
        }
    }
    
    private boolean isValid(long n){
        long rlt = 0;
        long og = n;
        while(n > 0){
            int d = (int)n % 10;
            rlt = rlt * 10 + can[d];
            n /= 10;
        }
        
        return rlt != og;
    }
}

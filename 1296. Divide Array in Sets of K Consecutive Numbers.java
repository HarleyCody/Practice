class Solution {
    //only record num of consecutive nums and lastNum in consecutive array
    //update num one by one to array
    //also record range(ie, s and e) of num that can be updated
    int[] lastNum, num;
    int s = 0, e = 0, K;
    public boolean isPossibleDivide(int[] nums, int k) {
        int len = nums.length;
        if(len % k != 0){
            return false;
        }
        lastNum = new int[len / k];
        num = new int[len / k];
        K = k;
        
        Arrays.sort(nums);
        
        int idx = 0;
        
        while(idx < len){
            int n = nums[idx];
            int f = 0;
            
            while(idx < len && n == nums[idx]){
                ++f;
                ++idx;
            }
            if(!update(n , f)){
                return false;
            }
        }
        for(int n : num){
            if(n != 0 && n < k){
                return false;
            }
        }
        return true;
    }
    
    private boolean update(int n, int f){
        for(int i = e - 1; s <= i; --i){
            if(f <= 0 && num[i] < K){
                return false;
            }else if(num[i] == K || lastNum[i] != n - 1){
                s = i + 1;
                break;
            }else{
                lastNum[i] = n;
                ++num[i];
                --f;
            }
        }
        
        while(0 < f && e < num.length){
            lastNum[e] = n;
            ++num[e++];
            --f;
        }
        
        return true;
    }
}

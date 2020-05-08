class Solution {  
    // decide l, m find r
    // find r by memoization
    public boolean splitArray(int[] nums) {
        int len = nums.length;
        if(len < 7){
            return false;
        }
        // recorder[i] List of r where mid ~ r ==  r ~ len
        List<Integer>[] recorder = new ArrayList[len];
        int[] sums = new int[len];
        sums[0] = nums[0];
        
        for(int i = 1; i < len; ++i){
            sums[i] = nums[i] + sums[i - 1];
        }
        
        int tar;
        for(int l = 1; l < len - 3; ++l){
            tar = sums[l - 1];
            for(int m = l + 2; m < len - 2; ++m){
                if(sums[l - 1] == sums[m - 1] - sums[l]){
                    if(recorder[m] != null){
                        for(int r : recorder[m]){
                            if(sums[len - 1] - sums[r] == tar){
                                return true;
                            } 
                        }
                    }else{
                        recorder[m] = new ArrayList();
                        for(int r = m + 2; r < len - 1; ++r){
                            if(sums[len - 1] - sums[r] == sums[r - 1] - sums[m]){
                                if(sums[len - 1] - sums[r] == tar){
                                    return true;
                                }
                                recorder[m].add(r);
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}

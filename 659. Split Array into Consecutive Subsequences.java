class Solution {
    // record last num, and num of current arry
    // if freq of new number >= array we got, append array
    // add number to array one by one;
    int[] lastNum = new int[5000], num = new int[5000];
    int s = 0, e = 0;
    public boolean isPossible(int[] nums) {
        int len = nums.length, idx = 0;
        while(idx < len){
            int n = nums[idx];
            int freq = 0;
            while(idx < len && n == nums[idx]){
                ++freq;
                ++idx;
            }
            if(!update(n , freq)){
                return false;
            }
        }
        for(int i = s; i < e; ++i){
            if(num[i] < 3){
                return false;
            }
        }
        return true;
    }
    
    private boolean update(int n, int f){
        for(int i = e - 1; s <= i; --i){
            if(f != 0 && lastNum[i] == n - 1){
                lastNum[i] = n;
                ++num[i];
                --f;
            }else if(num[i] < 3){
                return false;
            }else{
                s = i;
            }
        }
        while(f != 0){
            --f;
            lastNum[e] = n;
            ++num[e++]; 
        }
        return true;
    }
}

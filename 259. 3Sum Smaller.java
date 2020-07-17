_______________________________________________________________________________Best Solution________________________________________________________________________
class Solution {
    // 1, sort 2, twoSumSmaller 3 two pointer collect rlt of twoSumSmaller
    public int threeSumSmaller(int[] nums, int target) {
        int len = nums.length;
        int ans = 0;
        Arrays.sort(nums);
        for(int i = 0; i < len; ++i){
            ans += twoSumSmaller(nums, target - nums[i], i + 1);
        }
        return ans;
    }
    
    private int twoSumSmaller(int[] nums, int tar, int start){
        int l = start;
        int r = nums.length - 1;
        int ans = 0;
        while(l < r){
            if(nums[l] + nums[r] >= tar){
                --r;
            }else{
                ans += r - l;
                ++l;
            }
        }
        return ans;
    }
}
________________________________________________________________________________My Solution________________________________________________________________________
class Solution {
    // hash the idx and record visited nodes
    public int threeSumSmaller(int[] nums, int target) {
        int ans = 0, len = nums.length;
        HashSet<Integer> visited = new HashSet();
        for(int mid = 1 ; mid < len; ++mid){
            visited = new HashSet();
            for(int l = 0; l < mid; ++l){
                for(int r = len - 1; r > mid; --r){
                    if(nums[mid] + nums[l] + nums[r] < target){
                        int hash = l * len + r;
                        if(visited.add(hash)){
                            ++ans;
                        }
                    }
                }
            }
        }
        return ans;
    }
}

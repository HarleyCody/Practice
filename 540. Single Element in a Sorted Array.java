___________________________________________________________My Solution O(logN)_____________________________________________________________
class Solution {
    // BST narrow down the range only one element is single
    // before single num[even] == num[even + 1]
    // after single num[odd] == num[odd - 1]
    public int singleNonDuplicate(int[] nums) {
        int l = 0, r = nums.length - 1;
        while(l < r){
            int m = (l + r) / 2;
            if(m % 2 == 0){
                if(nums[m + 1] == nums[m]){
                    l = m + 1;
                }else{
                    r = m;
                }
            }else{
                if(nums[m - 1] == nums[m]){
                    l = m + 1;
                }else{
                    r = m;
                }
            }
        }
        return nums[r];
    }
}

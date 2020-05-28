_______________________________________________________________Best Solution_______________________________________________________________
class Solution {
    // set to null in case of no such ans
    // l set when its null or num > l
    // m set when m == null or num < l (ie != num)
    // r set when m != null num < m 
    public int thirdMax(int[] nums) {
        Integer l = null, m = null, r = null;
        boolean found = false;
        for(int i = 0; i < nums.length; ++i){
            if(l == null || nums[i] > l){
                r = m;
                m = l;
                l = nums[i];
            }else if(nums[i] < l && (m == null || nums[i] > m)){
                r = m;
                m = nums[i];
            }else if(m != null && nums[i] < m && (r == null || nums[i] > r)){
                r = nums[i];
            }
        }
        return r == null ? l : r;
    }
}

__________________________________________________Qualified Solution but slower________________________________________________
class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> ans = new ArrayList();
        
        for(int i = 0; i < nums.length; ++i){
        // check number at index - 1; if > 0, unchecked( unique number), turn it to negative.
            int checkIdx = Math.abs(nums[i]) - 1;
            if(nums[checkIdx] < 0){
                ans.add(Math.abs(checkIdx + 1));
            }
            nums[checkIdx] = - nums[checkIdx];
        }
        return ans;
    }
}
________________________________________________________My Solution____________________________________________________________
class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> ans = new ArrayList();
        // record frequence of numbers.
        int[] recorder = new int[nums.length];
        for(int i = 0; i < nums.length; ++i){
            ++recorder[nums[i] - 1];
            if(recorder[nums[i] - 1] > 1){
                ans.add(nums[i]);
            }
        }
        return ans;
    }
}

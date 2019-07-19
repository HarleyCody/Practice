class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        // recorder ans
        List<Integer> ans = new ArrayList();
        if(nums.length == 0) return ans;
        // recorder existence of nums[i], exist == 1, exist == 0;
        int[] recorder = new int[nums.length];
        // recorder existence
        for(int i = 0; i < nums.length; ++i){
            recorder[nums[i] - 1] = 1;
        }
        // add to ans if abscent
        for(int i = 0; i < recorder.length; ++i){
            if(recorder[i] == 0){
                ans.add(i + 1);
            }
        }
        return ans;
    }
}

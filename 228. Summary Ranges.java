class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> ans = new ArrayList();
        if(nums.length == 0) return ans;
        // expected record value of next expected element
        int start = 0, end = 0, expected = nums[0];
        while(end < nums.length){
            end++;
            expected++;
            // when its last element or not eaquals to expected, add it to list
            if(end == nums.length || nums[end] != expected){
                // single element
                if(start == end - 1){
                    ans.add(String.valueOf(nums[start]));
                // multi elements
                }else{
                    StringBuilder str = new StringBuilder(String.valueOf(nums[start]));
                    str.append("->");
                    str.append(String.valueOf(nums[end - 1]));
                    ans.add(str.toString());
                }
                // update start and expected;
                if(end < nums.length){
                    expected = nums[end];
                    start = end;
                }
            }
        }
        return ans;
    }
}

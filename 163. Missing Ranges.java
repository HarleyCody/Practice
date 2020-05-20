________________________________________________________________My Solution______________________________________________________________
class Solution {
    // traverse from left to right
    // long settles int overflow
    // append speeds up
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> ans = new ArrayList();
        StringBuilder sb = new StringBuilder();
        
        if(nums.length == 0){
            if(lower == upper){
                sb.append(lower);
            }else{
                sb.append(lower).append("->").append(upper);
            }
            if(sb.length() != 0){
                ans.add(sb.toString());
            }
            return ans;
        }
          
        
        if((long)lower + 1 < nums[0]){
            sb.append(lower).append("->").append(nums[0] - 1);        
        }else if((long)lower < nums[0]){
            sb.append(lower);
        }
        if(sb.length() != 0){
            System.out.println(sb.toString());
            ans.add(sb.toString());
        }
        
        int len = nums.length;
        for(int i = 0; i < len - 1; ++i){
            sb = new StringBuilder();
            if((long)nums[i] + 2 < nums[i + 1]){
                sb.append(nums[i] + 1).append("->").append(nums[i + 1] - 1);
            }else if((long)nums[i] + 1 < nums[i + 1]){
                sb.append(nums[i] + 1);   
            }
            if(sb.length() != 0){
                ans.add(sb.toString());
            }
        }
        
        sb = new StringBuilder();
        if(nums[len - 1] < upper - 1){
            sb.append(nums[len - 1] + 1).append("->").append(upper);
        }else if(nums[len - 1] < upper){
            sb.append(nums[len - 1] + 1);
        }
        if(sb.length() != 0){
            ans.add(sb.toString());
        }
        return ans;
    }
}

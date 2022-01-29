//My Solution: Override comparator by len and char and sort
class Solution {
    public String kthLargestNumber(String[] nums, int k) {
        Arrays.sort(nums, new Comparator<String>(){
           public int compare(String num1, String num2){
               int len = num1.length();
	            if(num1.length() != num2.length()){
	                return num2.length() - len;
                }
                for(int i = 0; i < len; ++i){
                    char c1 = num1.charAt(i);
                    char c2 = num2.charAt(i);
                    if(c1 > c2){
                        return -1;
                    }
                    if(c1 < c2){
                        return 1;
                    }
                }
               return 0;
           }
        });
        
        return nums[k - 1];
    }
}

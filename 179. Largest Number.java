class Solution {
    public String largestNumber(int[] nums) {
        String[] recorder = new String[nums.length];
        for(int i = 0; i < nums.length; i++){
            recorder[i] = String.valueOf(nums[i]);
        }
        // Comparing string if they have same length;
        // Otherwise, concatenate them with different order(1st + 2nd) (2nd + 1st)
        // Comparing these two new concatenated String
        // if return >= 0 str2 will follow str1
        // else str1 will follow str2.
        
        // 2nd param is older element, 1st element is new element
        // order is str2, str1 in comparator, if return >=0 order keeps unchanged
        // else swap str2 and str1.
        Arrays.sort(recorder, new Comparator<String>(){
            public int compare(String str1, String str2){

                if(str1.length() == str2.length()){
                    return str2.compareTo(str1);
                }
                StringBuilder in = new StringBuilder(str1);
                in.append(str2);
                StringBuilder re = new StringBuilder(str2);
                re.append(str1);
                int i = 0;
                while(i < in.length() && in.charAt(i) == re.charAt(i)){
                    i++;
                }
                return i == in.length() ? 0 : re.charAt(i) - in.charAt(i);
            }
        });
        StringBuilder ans = new StringBuilder();
        for(String str : recorder){
            ans.append(str);
        }
        return ans.toString().charAt(0) == '0' ? "0" : ans.toString();
    }
}

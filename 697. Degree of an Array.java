________________________________________________________________Best Solution______________________________________________________________
class Solution {
    public int findShortestSubArray(int[] nums) {
        // reduced recorder array size
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int n:nums){
            max = Math.max(max,n);
            min = Math.min(min,n);
        }
        int len = max - min + 1;
        
        // occurrence
        int[] occurArray = new int[len];
        // first index
        int[] firstArray = new int[len];
        // last index
        int[] lastArray = new int[len];
        int degree = 0;
        for(int i=0, index; i < nums.length; i++){
            index = nums[i] - min;
            // occurance + 1
            if(occurArray[index]++ == 0){
                firstArray[index] = lastArray[index] = i;
            }else{
                lastArray[index] = i;
            }
            if(occurArray[index] <= degree) continue;
            degree = occurArray[index];
        }
        // get ans by searching all element with maxDegree
        int resLen = nums.length;
        for(int i=0; i<len; i++){
            if(degree == occurArray[i]){
                resLen = Math.min(resLen,lastArray[i] - firstArray[i] + 1);
            }
        }
        return resLen;
    }
}

__________________________________________________________________My Solution______________________________________________________________
class Solution {
    // record start of elements,
    // record frequncy of elements(degree)
    // i - start[i] + 1 == length[i];
    // update minLength[degree];
    public int findShortestSubArray(int[] nums) {
        if(nums.length == 0) return 0;
        
        HashMap<Integer, Integer> recorder = new HashMap();
        HashMap<Integer, Integer> start = new HashMap();
        
        int[] ans = new int[nums.length + 1];
        Arrays.fill(ans, nums.length);
        int maxDgr = 0, degree = 0, length = 0;
        for(int i = 0; i < nums.length; i++){
            if(!start.containsKey(nums[i])){
                start.put(nums[i], i);
            }
            degree = recorder.getOrDefault(nums[i], 0) + 1;
            maxDgr = Math.max(degree, maxDgr);
            
            recorder.put(nums[i], degree);
            if(degree == maxDgr){
                length = i - start.get(nums[i]) + 1;
                ans[degree] = Math.min(length, ans[degree]);
            }
        }
        
        return ans[maxDgr];
    }
}

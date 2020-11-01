_____________________________________________________________________________Best Solution________________________________________________________________________________
// do not search from index - 1 every time, search from start instead
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int len = nums.length, index = 1, start = -1, end = 0, max = nums[0];
        while(index < len) {
            if(nums[index] >= nums[index - 1]) {
                max = nums[index];
            } else {
                if(start == -1 || nums[start] > nums[index]) {
                    if(start==-1) {
                        start=index-1;
                    }
                    while(start>=0 && nums[start] > nums[index]) {
                        --start;
                    }
                    ++start;
                }
                nums[index] = max;
                end = index;
            }
            ++index;
        }
        return start==-1 ? 0 : end-start+1;
    }
}
______________________________________________________________________________My Solution________________________________________________________________________________
// sort num and compare
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int len = nums.length;
        int[] sortedNum =  nums.clone();
        Arrays.sort(sortedNum);
        
        int l = 0, r = len - 1;
        while(l < r && nums[l] == sortedNum[l]){
            ++l;
        }
        while(l < r && nums[r] == sortedNum[r]){
            --r;
        }
        
        return r == l? 0 : r - l + 1;
    }
}
________________________________________________________________________________My Solution________________________________________________________________________________
// find wrong number and find its position, r: current pos, l = correct pos;
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int len = nums.length;
        
        int[] min = new int[len];
        min[0] = nums[0];
        int l = len - 1, r = len - 1;
        for(int i = 1; i < len; ++i){
            int prev = i - 1;
            while(0 <= prev && nums[i] < min[prev]){
                r = i;
                --prev;
                l = Math.min(prev, l);
            }
            min[i] = Math.max(nums[i], min[i - 1]);
        }
        return r - l;
    }
}

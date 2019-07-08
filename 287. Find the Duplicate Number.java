_________________________________________________________Best Solution_________________________________________________________
class Solution {
    public int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[nums[0]];
        // how long the path is between 0 to entry point
        while(slow != fast){
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        
        fast = 0;
        // enrtry point
        while(slow != fast){
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
__________________________________________________________My Solution__________________________________________________________
class Solution {
    public int findDuplicate(int[] nums) {
    // set record previous numbers
        HashSet<Integer> recorder = new HashSet();
        for(int i = 0; i < nums.length; ++i){
            if(!recorder.contains(nums[i])){
                recorder.add(nums[i]);
            }
            else return nums[i];
        }
        return 0;
    }
}

_____________________________________________________________Best Solution_________________________________________________________________
class Solution {
    //~ : not(opposite);
    // ^ :XOR, x ^ x = 0; x^0 = x
    /* nums[i] = 2;
    first:  once = 1 0 (eg. 2) twice = 0 0
    second: once = 0 0 twice = 1 0
    third: once = 0 0 twice = 0 0
    during the third time 2 has been elimiated, so only once will record ans;
    */
    // this method can also find element occurs forth with others occurs more than once less than four times( 1 < other occur < 4)
    public int singleNumber(int[] nums) {
        int once = 0;
        int twice = 0;
        //int third = 0; find element occurs once with others occurs four times
        for (int i = 0;i < nums.length; i++) {
            once = ~twice & (once ^ nums[i]);
            twice = ~once & (twice ^ nums[i]);
            // third = ~twice & (third ^ nums[i]);
        }
        return once;
    }
}
______________________________________________________________My Solution__________________________________________________________________
class Solution {
    // sort with ascending
    // if cur != later, cur is ans;
    // else find a new start;
    // if new start == nums.length - 1; if new start != prev, new start is ans
    // else cannot find.
    public int singleNumber(int[] nums) {
        if(nums.length == 1) return nums[0];
        Arrays.sort(nums);
        int i = 0;
        while(i < nums.length){
            if(nums[i] != nums[i + 1]) return nums[i];
            int j = i + 1;
            while(j < nums.length && nums[j] == nums[i]){
                j++;
            }
            if(j == nums.length - 1 && nums[j] > nums[j - 1]) {
                return nums[j];
            }
            i = j;
        }
        return 0;
    }
}

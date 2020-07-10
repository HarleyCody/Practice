_____________________________________________________________________________My Solution_____________________________________________________________________________
class Solution {
    //detect digits by digits, find minMax
    //from back to head to assure closest
    //digits record orginal number of time;
    //nums record number of answer, nums changed from digits, so digits should remain same in every foundIn()
    int[] digits = new int[4];
    int[] nums = new int[4];
    int min = 9;
    public String nextClosestTime(String time) {
        int d = 0;
        for(int i = 0; i < 5; ++i, ++d){
            if(i == 2){
                ++i;
            }
            digits[d] = time.charAt(i) - '0';
            min = Math.min(min, digits[d]);
        }
        
        nums = digits.clone();
        
        StringBuilder ans = new StringBuilder();
        
        for(int i = 3; 0 <= i; --i){
            if(foundIn(i)){
                ans.append(nums[0]).append(nums[1]).append(":").
                    append(nums[2]).append(nums[3]);
                return ans.toString();
            }
        }
        
        ans.append(min).append(min).append(":").append(min).append(min);
        return ans.toString();
    }
    private boolean foundIn(int d){
        boolean found = false;
        int og = digits[d];
        int range = 10;
        if(d == 2){
            range = 6;
        }else if(d == 1){
            range = digits[0] < 2 ? 10 : 5;
        }else if(d == 0){
            range = 3;
        }
        for(int i = 3; 0 <= i ; --i){
            if(i != d && og < digits[i] && digits[i] < range){
                found = true;
                nums[d] = nums[d] == og ? digits[i] : Math.min(digits[i], nums[d]);
            }
        }
        if(!found){
            nums[d] = min;
        }
        return found;
    }
}

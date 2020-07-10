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
___________________________________________________________________________My Naive Code___________________________________________________________________
class Solution {
    int[] digits = new int[4];
    int[] nums = new int[4];
    int min = 9;
    public String nextClosestTime(String time) {
        digits[0] = time.charAt(0) - '0';
        digits[1] = time.charAt(1) - '0';
        digits[2] = time.charAt(3) - '0';
        digits[3] = time.charAt(4) - '0';
        
        nums = digits.clone();
        min = Math.min(Math.min(Math.min(digits[0], digits[1]), digits[2]), digits[3]);

        StringBuilder ans = new StringBuilder();
        ans.append(min).append(min).append(":").append(min).append(min);
        if(foundInD3() || foundInD2() || foundInD1() || foundInD0()){
            ans = new StringBuilder();
            ans.append(nums[0]).append(nums[1]).append(":").append(nums[2]).append(nums[3]);
        }
        
        return ans.toString();
    }
    private boolean foundInD3(){
        boolean found = false;
        int og = digits[3];
        for(int i = 3; 0 <= i ; --i){
            if(i == 3)continue;
            if(og < digits[i]){
                found = true;
                nums[3] = nums[3] == og ? digits[i] : Math.min(digits[i], nums[3]);
            }
        }
        if(!found){
            nums[3] = min;
        }

        return found;
    }
    private boolean foundInD2(){
        boolean found = false;
        int og = digits[2];
        for(int i = 3; 0 <= i ; --i){
            if(i == 2)continue;
            if(og < digits[i] && digits[i] < 6){
                found = true;
                nums[2] = nums[2] == og ? digits[i] :  Math.min(digits[i], nums[2]);
            }
        }
        if(!found){
            nums[2] = min;
        }

        return found;
    }
    private boolean foundInD1(){
        boolean found = false;
        int og = digits[1];
        int range = digits[0] < 2 ? 10 : 5;
        for(int i = 3; 0 <= i ; --i){
            if(i == 1)continue;
            if(og < digits[i] && digits[i] < range){
                found = true;
                nums[1] = nums[1] == og ? digits[i] : Math.min(digits[i], nums[1]);
            }
        }
        if(!found){
            nums[1] = min;
        }

        return found;
    }
    private boolean foundInD0(){
        boolean found = false;
        int og = digits[0];
        for(int i = 3; 0 <= i ; --i){
            if(i == 0)continue;
            if(og < digits[i] && digits[i] < 3){
                found = true;
                nums[0] = nums[0] == og ? digits[i] : Math.min(digits[i], nums[0]);
            }
        }
        if(!found){
            nums[0] = min;
        }

        return found;
    }
}

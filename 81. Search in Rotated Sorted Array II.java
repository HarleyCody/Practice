__________________________________________________________________My Solution___________________________________________________________
// find the rotated point previous O(n) the rest O(logN);
// time complexity (O(m1) + O(logm2))
class Solution {
    public boolean search(int[] nums, int target) {
        int len = nums.length;
        if(len == 0){
            return false;
        }
        
        int bp = 0;
        while(bp < len - 1 && nums[bp] <= nums[bp + 1]){
            if(nums[bp] == target){
                return true;
            }
            ++bp;
        }
        if(nums[bp] == target){
            return true;
        }
        
        return find(nums, target, bp + 1, len - 1);
    }
    
    private boolean find(int[] nums, int target, int l, int r){
        int m = 0;
        while(l <= r){
            m = (l + r) / 2;
            if(nums[m] == target){
                return true;
            }
            
            if(nums[m] > target){
                r = m - 1;
            }else{
                l = m + 1;
            }
        }
        
        return false;
    }
}
__________________________________________________________________My Solution___________________________________________________________
class Solution {  
    // find rotate point and binary search
    public boolean search(int[] nums, int target) {
        int len = nums.length, p = 0;
        if(len == 0){
            return false;
        }
        while(p < len - 1){
            if(nums[p] > nums[p + 1]){
                break;
            }
            ++p;
        }
        
        if(nums[p] < target){
            return false;
        }
        return search(nums, 0, p, target) || search(nums, p + 1, len - 1, target);
    }
    
    private boolean search(int[] nums, int s, int e, int tar){
        if(e < s){
            return false;
        }
        int m = (s + e) / 2;
        if(nums[m] == tar){
            return true;
        }else if(nums[m] < tar){
            return search (nums, m + 1, e, tar);
        }else{
            return search(nums, s, m - 1, tar);
        }
    }

}

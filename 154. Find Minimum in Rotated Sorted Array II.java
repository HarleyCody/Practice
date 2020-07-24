_______________________________________________________________________________Best Solution_________________________________________________________________________________
// worst N
class Solution {
    public int findMin(int[] nums) {
        int low = 0, high = nums.length - 1;

        while (low < high) {
            int pivot = low + (high - low) / 2;
            if (nums[pivot] < nums[high])
                high = pivot;
            else if (nums[pivot] > nums[high])
                low = pivot + 1;
            else
                high -= 1;
        }
        return nums[low];
    }
}
_________________________________________________________________________________My Solution_________________________________________________________________________________
// worst (logn)^2
class Solution {
    //binary search if both end are same same each half part
    public int findMin(int[] nums) {
        int l = 0, r = nums.length -1;
        return search(nums, 0, nums.length - 1);
    }
    
    private int search(int[] nums, int l, int r){
        if(l == r){
            return nums[r];
        }
        int m = (l + r) / 2;
        int rlt = 0;
        if(nums[r] == nums[l]){
            rlt = Math.min(search(nums, l, m), search(nums, m + 1, r));
        }else if(nums[l] > nums[r]){
            if(nums[m] < nums[l]){
                rlt = search(nums, l, m);
            }else{
                rlt = search(nums, m + 1, r);
            }
        }else{
            if(nums[m] < nums[l]){
                rlt = search(nums, l, m);
            }else{
                rlt = nums[l];
            }
        }
        return rlt;
    }
}

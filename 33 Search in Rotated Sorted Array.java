_______________________________________________________________Best Solution_____________________________________________________________
class Solution {
    // creteria for binary search
    // one of two halves of divided nums is sorted
    // if num is in sorted range, keep searching in range;
    // otherwise searching the other part(Unsorted part);
    public boolean search(int[] nums, int target) {
        if (nums.length == 0) return false;
        
        return search(nums, target, 0, nums.length - 1);
    }
    
    public boolean search(int[] nums, int target, int low, int high) {
        System.out.println("searching in range " + low + " " + high);
        int mid = (low + high) / 2;
        
        if (nums[mid] == target) {
            return true;
        }
        
        if (low > high) {
            return false;
        }
        
        if (nums[low] <= nums[mid]) { // The left half of the array is sorted
            if (nums[low] <= target && nums[mid] >= target) { // The element exists in the left half
                return search(nums, target, low, mid - 1);    
            } else { // The element exists in the right half
                return search(nums, target, mid + 1, high);
            }
        }
        
		// The right half of the array is sorted
        if (nums[mid] <= target && nums[high] >= target) { // The element exists in the right half
            return search(nums, target, mid + 1, high);
        } else { // The element exists in the left half
            return search(nums, target, low, mid - 1);
        }
    }
}
_________________________________________________________________O(logN)Naive binary search Solution___________________________________________
class Solution {
    public int search(int[] nums, int target) {
        if(nums.length == 0) return -1;
        if(nums.length == 1) return nums[0] == target ? 0 : -1;
        int mid = (nums.length - 1)/2;
        return Math.max(binarySearch(nums, 0, mid, target), binarySearch(nums, mid + 1, nums.length - 1, target));
    }
    private int binarySearch(int[] nums, int start, int end, int target){
        if(nums[start] == target) return start;
        if(start >= end) return -1;
        int mid = (start + end) / 2;
        return Math.max(binarySearch(nums, start, mid, target), binarySearch(nums, mid + 1, end, target));
    }
}
_____________________________________________________O(n)________________________________________________________________________
class Solution {
    public int search(int[] n, int target) {
        int i=0;
        while(i!=n.length){
            if(target==n[i])return i; //找到
            else ++i;//没找到继续下一个
        }
        return -1;//遍历完了都没找到
    }
}

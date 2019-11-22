_________________________________________________________________O(logN)valid Solution___________________________________________
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

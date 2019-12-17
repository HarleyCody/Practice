_________________________________________________Best Solution__________________________________________________________________
class Solution {
// recorder two min and three max by variables O(n)
    public int maximumProduct(int[] nums) {
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max3 = max2;
                max2 = num;
            } else if (num > max3) {
                max3 = num;
            }
            if (num < min1) {
                min2 = min1;
                min1 = num;
            } else if (num < min2) {
                min2 = num;
            }
        }
        return max1 * Math.max( max2 * max3, min1 * min2);
    }
}
_________________________________________________My Solution__________________________________________________________________
class Solution {
     // may contains two negatives or all positive. so only campare product of two min value and 2nd 3rd largest value
     // choose max to time 1st largest value O(nlogn)
    public int maximumProduct(int[] nums) {
        int n = nums.length;
        if(nums.length < 3) return 0;
        Arrays.sort(nums);
        return Math.max(nums[0] * nums[1], nums[n - 2] * nums[n - 3]) * nums[n - 1];
    }
}

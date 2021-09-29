//Best Solution: Due to the range is fixed and range of number is relative small
//Use insertion sort instead of binary sort, insertion sort record the frequence of number like a freqMap but arr gives order
class Solution {
    public int minPairSum(int[] nums) {
        int[] arr = new int[100001];
        int max = 0;
        int min = 100001;
        for(int i : nums) {
            arr[i]++;
            max = Math.max(max, i);
            min = Math.min(min, i);
        }
        int start = min; 
        int end = max;
        max = 0;
        while(start < end) {
            if(arr[start] == 0) start++; 
            else if(arr[end] == 0) end--;
            else {
                max = Math.max(start + end, max);
                int temp = Math.min(arr[start], arr[end]);
                arr[start] -= temp;
                arr[end] -= temp;
            }
        }
        
        if(arr[start] > 1) {
            max = Math.max(max, 2 * start);
        }else if(arr[end] > 1) {
            max = Math.max(max, 2 * end);
        }
        return max;
    }
}
//My Soution: Sort and compare one pair with two pointers
class Solution {
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int l = 0, r = nums.length - 1;
        int ans = Integer.MIN_VALUE;
        
        while(l < r){
            ans = Math.max(ans, nums[l++] + nums[r--]);
        }
        
        return ans;
    }
}

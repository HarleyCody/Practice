_______________________________________________________Best Solution Arrays.sort(5m)_________________________________________________________
class Solution {
    public int[] sortArray(int[] nums) {
        int[] arr = new int[100001];
        // store frequence of num base on its value; smaller element goes to head;
        for(int i = 0; i < nums.length; i++){
            arr[nums[i] + 50000]++;
        }
        int index = 0;
        // put num to nums based on recorder.
        for(int i = 0; i < arr.length; i++){
            while(arr[i] > 0){
                // i - 50000 value of num.
                nums[index++] = i - 50000;
                arr[i]--;
            }
        }
        return nums;
    }
}
_________________________________________________________My Solution QuickSort(8m)____________________________________________________________
class Solution {
    public int[] sortArray(int[] nums) {
        if(nums.length == 0) return nums;
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }
    private void quickSort(int[] nums, int start, int end){
        if(start >= end) return;
        int pivot = nums[start];
        int i = start, j = end;
        while(i < j){
            while(i <= end && nums[i] < pivot)i++;
            while(start <= j && nums[j] > pivot)j--;
            if(i < j){
                // duplicate element, put in front of pivot together,(swap j with i + 1)
                if(nums[i] == nums[j])i++;
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        nums[i] = pivot;
        quickSort(nums, start, i - 1);
        quickSort(nums, i + 1, end);
    }
}
_______________________________________________________My Solution Arrays.sort(5m)_________________________________________________________
class Solution{
    public int[] sortArray(int[] nums){
        Arrays.sort(nums);
        return nums;
    }
}

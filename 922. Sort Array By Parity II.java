//Best Solution: two pointers going from left to right, to find odd and even, swap to numbers
class Solution {
    public int[] sortArrayByParityII(int[] nums) {
        int n=nums.length;
        int even=0,odd=1;
        while(even<n && odd<n){
            while(even<n && (nums[even]&1)==0)
                even+=2;
            while(odd<n && (nums[odd]&1)!=0)
                odd+=2;
            if(even<n && odd<n)
                swap(odd,even,nums);
        }
       return nums;
    }
    public void swap(int i,int j,int[] nums){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }
}

//My Solution: two arrays
class Solution {
    public int[] sortArrayByParityII(int[] nums) {
        int count = nums.length - 1;
        int[] evenArray = new int[count];
        int[] oddArray = new int[count];
        int evenIdx = 0;
        int oddIdx = 0;
        for(int n : nums){
            if(n % 2 == 0){
                evenArray[evenIdx++] = n;
            }else{
                oddArray[oddIdx++] = n;
            }
        }
        
        evenIdx = 0;
        oddIdx = 0;
        int val = 0;
        for(int i = 0; i < nums.length; ++i){
            val = i % 2 == 0? evenArray[evenIdx++] : oddArray[oddIdx++];
            nums[i] = val;
        }
        return nums;
    }
}
//My Solution: four pointers
class Solution {
    public int[] sortArrayByParityII(int[] nums) {
        int len = nums.length;
        int leftEven = -1;
        int leftOdd = -1;
        int rightEven = -1;
        int rightOdd = -1;
        int idx = 0;
        while(idx < len && (leftEven < 0 || leftOdd < 0 || rightEven < 0 || rightOdd < 0)){
            if(nums[idx] % 2 == 0){
                if(leftEven < 0){
                    leftEven = idx;
                }else{
                    rightEven = idx;
                }
            }else{
                if(leftOdd < 0){
                    leftOdd = idx;
                }else{
                    rightOdd = idx;
                }
            }
            ++idx;
        }
        swap(nums, leftEven, 0);
        swap(nums, leftOdd, 1);
        if(1 == rightOdd) rightOdd = leftOdd;
        leftEven = 0;
        leftOdd = 1;
        
        if(rightOdd == -1) return nums;
        
        swap(nums, rightOdd, len - 1);
        swap(nums, rightEven, len - 2);
        rightOdd = len - 1;
        rightEven = len - 2;
        
        while(leftOdd < rightEven || leftEven < rightOdd){
            while(rightEven > leftOdd && nums[rightEven] % 2 == 0) rightEven -= 2;
            while(rightOdd > leftEven && nums[rightOdd] % 2 == 1) rightOdd -= 2;
            while(leftEven < rightOdd && nums[leftEven] % 2 == 0) leftEven += 2;
            while(leftOdd < rightEven && nums[leftOdd] % 2 == 1) leftOdd += 2;
            swap(nums, leftEven, rightOdd);
            swap(nums, leftOdd, rightEven);
        }
        return nums;
    }
    private void swap(int[] arr, int left, int right){
        if(left >= right) return;
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
}

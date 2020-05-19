___________________________________________________________________Best solution(O(n))_________________________________________________________
class Solution {
    // even min : swtich even with even + 1 when even > even + 1
    // odd max : swtich odd with odd + 1 when odd > odd + 1
    public void wiggleSort(int[] nums) {
        
        for (int i = 0; i < nums.length - 1; i++) {
            if (i % 2 == 0) {
                if (nums[i] > nums[i + 1]) {
                    swap(nums, i, i + 1);
                }
            } else {
                if (nums[i] < nums[i + 1]) {
                    swap(nums, i, i + 1);
                }   
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
__________________________________________________________Modify Quick Sort solution_____________________________________________________
class Solution {
    //naive solution, sort and swtich borader when i is odd
    public void wiggleSort(int[] nums) {
        int l = 0, r = nums.length - 1;
        quickSort(nums, l, r);
    }
    
    private void quickSort(int[] nums, int s, int e){
        if(s <= e){
            int p = partition(nums, s, e);
            quickSort(nums, s, p - 1);
            quickSort(nums, p + 1, e);
            
            if(p % 2 == 1 && p < nums.length - 1){
                int tmp = nums[p];
                nums[p] = nums[p + 1];
                nums[p + 1] = tmp;
            }
        }
    }
    private int partition(int[] nums, int s, int e){
        int p = nums[s];
        while(s < e){
            while(s < e && p <= nums[e]){
                --e;
            }
            nums[s] = nums[e];
            while(s < e && p >= nums[s]){
                ++s;
            }
            nums[e] = nums[s];
        }
        nums[s] = p;
        return s;
    }
}
_____________________________________________________________________naive solution_______________________________________________________
class Solution {
    //sort and swtich borader when i is odd
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        for(int i = 0; i < len - 1; ++i){
            if(i % 2 == 0){
                continue;
            }
            int tmp = nums[i + 1];
            nums[i + 1] = nums[i];
            nums[i] = tmp;
        }
    }
}

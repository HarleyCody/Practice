//Best Solution for C++: this can achieve O(n) time compexity and O(1) space;
//Find median by O(n)
//Index rewiring can give same number of different index
//let index in the left part point to large number and right part point to small number
void wiggleSort(vector<int>& nums) {
    int n = nums.size();
    
    // Find a median.
    auto midptr = nums.begin() + n / 2;
    nth_element(nums.begin(), midptr, nums.end());
    int mid = *midptr;
    
    // Index-rewiring.
    //[0,1,2,3,4,5] -> [1,3,5,0,2,4]
    #define A(i) nums[(1+2*(i)) % (n|1)]

    // 3-way-partition-to-wiggly in O(n) time with O(1) space.
    int i = 0, j = 0, k = n - 1;
    while (j <= k) {
        if (A(j) > mid)
            swap(A(i++), A(j++));
        else if (A(j) < mid)
            swap(A(j), A(k--));
        else
            j++;
    }
}
//Best Solution: Bucket sort and insert in descending order
//Descending order will not allow two consecutive number as small number are bound to be more than large number, so insert large number first can seperate two small number
class Solution {
    public void wiggleSort(int[] nums) {
        if (nums.length <= 1) return;
        
        int[] bucket = new int[5001];
        for (int num : nums) {
            bucket[num]++;
        }
        
        int len = nums.length;
        int index = 1;
        for (int i = bucket.length - 1; i >= 0; i--) {
            if (bucket[i] == 0) continue;
            while (bucket[i] != 0) {
                nums[index] = i;
                index += 2;
                if (index >= len) {
                    index = 0;
                }
                bucket[i]--;
            }
            
        }
        
    }
}

//My Soluton: Bucket sort, and arrange numbers to num in ascending order. 
//Check if there are two consecutive number same, break the same part by moving array to right; 
class Solution {
    public void wiggleSort(int[] nums) {
        int[] bucket = new int[5001];
        int left = 5000;
        int right = 0;
        for(int n : nums){
            ++bucket[n];
            left = Math.min(left, n);
            right = Math.max(right, n);
        }
        
        int idx = 0;
        int len = nums.length;
        for(int i = left; i <= right; ++i){
            while(bucket[i]-- > 0){
                nums[idx++] = i;
                ++idx;
                if(idx >= len){
                    idx = 1;
                }
            }
        }
        
        //incase a number occurs len / 2 times;
        idx = 1;
        while(idx < len){
            if(nums[idx] == nums[idx - 1]){
                break;
            }
            ++idx;
        }
        if(idx == len) return;
        // break the two consecutive same number 
        left = 0;
        while(idx < len){
            int tmp = nums[left];
            nums[left++] = nums[idx];
            nums[idx++] = tmp;
        }
    }
}

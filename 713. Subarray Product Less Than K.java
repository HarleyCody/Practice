__________________________________________________________Best Solution_________________________________________________________________
class Solution {
    int ct = 0;
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if(nums == null || nums.length == 0 || k <= 1){
            return 0;
        }
        int res = 1, ct = 0, j = 0;
        // Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            res *= nums[i];
            while(res >= k){
                res /= nums[j++];
            }
            // if element is > k res /= element, j++ point to next element, so i = j - 1;
            // then ct += 0
            // j is start point, i is end point, increment is i - j + 1; 
            // + 1 means elemen perse; 
            /*j - i means number of start of valid subarray, eg, 10,5,2,3(100). 
             eg. when i = 1 j = 3, then it can start from 5(1) and 2(2); number is j - i => 3 - 1 = 2; 
             */
            ct += i - j + 1;
        }           
        return ct;
    }
//1. no need to use map, need to divide by the left boundary
//2. j need to define outside the for loop, because as long as j fixed, no need to start from 0, because the product * {0,j} will definitely > k, same as res, can be/nums[j]
//3. no need to determine each element is>than k or not, the res/nums[j] will cover that
}
____________________________________________________________My Solution_________________________________________________________________
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        // two pointers find left boundary and right boundary
        int i = 0, j = 0, product = 1, ans = 0;
        while(i < nums.length || j < nums.length){
            // if stater is not valid find a valid start
            while(i < nums.length && nums[i] >= k){
                i = ++j;
                product = 1;
            }
            if(i == nums.length) return ans;
            // find end point j (10, 5, 2, 3)(100) it will stop at 3(index: 3) so j - 1 is last element but numbers are not updated 
            while(j < nums.length && product < k){
                // product might be largr than k after multiply.
                product *= nums[j++];
                // update ans only when product < k
                if(product < k){
                    // j - i: number of valid subarray after multiplies (j - 1)th element
                    ans += j - i;
                }
            }
            // reach the end
            if(product < k){
                return ans;
            }
            // narrow down left boundary
            while(i < nums.length && product >= k){
                product /= nums[i++];
                // left are found, add last element to ans, eg, 2(index: 2) in this case
                if(product < k){
                    ans += j - i;
                }
            }
        }
        return ans;
    }
}

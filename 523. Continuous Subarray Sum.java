________________________________________________________Best Solution_________________________________________________________
class Solution {
    /*
    Running sum from first element to index i : sum_i. If we mod k, it will be some format like : 
    sum_i = k * x + modk_1.
    
    Running sum from first element to index j : sum_j. If we mod k, it will be some format like : 
    sum_j = k * y + modk_2
    
    If they have the same mod, which is modk_1 == modk_2, subtracting these two running sum we get the
    difference sum_i - sum_j = (x - y) * k = constant * k, 
    and the difference is the number of elements between index i and j, and the value is a multiple of k.*/
    public boolean checkSubarraySum(int[] nums, int k) {
        int sum = 0;
        HashMap < Integer, Integer > map = new HashMap < > ();
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (k != 0)
                sum = sum % k;
            if (map.containsKey(sum)) {
                // at least two elements
                if (i - map.get(sum) > 1)
                    return true;
            } else
                map.put(sum, i);
        }
        return false;
        
    }
}
__________________________________________________________My Solution_________________________________________________________
class Solution {
    // brute force
    public boolean checkSubarraySum(int[] nums, int k) {
        for(int i = 0; i < nums.length; ++i){
            int sum = nums[i];
            for(int j = i + 1; j < nums.length; ++j){
                sum += nums[j];
                if(k == 0 && sum == 0 || k != 0 && sum % k == 0 ) return true;
            }
        }
        return false;
    }
}

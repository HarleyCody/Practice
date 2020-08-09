________________________________________________________________________Best Solution__________________________________________________________________________________________
/*

Algo:
1. Take minval and maxVal amoing first element in all given arrays. Also, capture whihc array holds that in var minK.
2. Have an array (say dp) of size k (given k list) to store the minIndex on all given arrays.
3. while(dp[minK] of any one array is done) then we are done coverig all given array
    Until then, 
        a. Get next value from array minK with index dp[minK]
        b. Compare that value with all K list and update their minIndex in dp accordingly.
        c. Also, capture next minVal, maxVal and minK
*/
// check array one by one and move the pointer(dp) to right until there is not right can be move to.
class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
        int k=nums.size();
        if(k == 1) {
            return new int[] {nums.get(0).get(0), nums.get(0).get(0)};
        }
        int[] res = new int[2]; 
        int[] dp = new int[k];
        
        int minK = 0;
        int minVal = nums.get(0).get(0);
        int maxVal = nums.get(0).get(0);
        
        //Find minVal, minIndex sort list and maxVal
        for(int i =0;i<k;i++) {
            int val = nums.get(i).get(0);
            
            if(val< minVal) {
                minVal=val;
                minK = i;
            }
            if(val>maxVal) {
                maxVal = val;
            }
        }
        res = new int[]{minVal, maxVal};
        
        //Do until we cover anyone list completely
        boolean done = false;
        while(true) {
            List<Integer> nextNums = nums.get(minK);
            dp[minK]++;
            // found
            if(dp[minK] == nextNums.size()) {
                break;
            }
            int next = nextNums.get(dp[minK]);
            minVal = next;
            
            //Check all k list for next minVal and maxVal
            for(int i = 0;i < k ;i++) {
                
                nextNums = nums.get(i);
                int currIdx = dp[i];
                int currSize = nextNums.size();
                while(currIdx < currSize && nextNums.get(currIdx) <= next) {
                    dp[i] = currIdx++;
                } 
                int val = nextNums.get(dp[i]);
                if(val < minVal) {
                    minVal = val;
                    minK = i;
                }
                if(val > maxVal) {
                    maxVal = val;
                }
            }
            if((maxVal - minVal) < (res[1] - res[0])){
                res = new int[]{minVal, maxVal};
            }
        }
        
        return res;
    }
}

//O(min(length of k list) * k)

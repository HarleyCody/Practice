//DFS: When two number at same do the next level recursion to get max
//     recorder sum for start at nums1 and nums2 and return max(sum1, sum2);

class Solution {
    public int maxSum(int[] nums1, int[] nums2) {
        return (int)(maxSumFrom(nums1, 0, nums2, 0) % 1000000007);
    }
    
    private long maxSumFrom(int[] nums1, int nums1Place, int[] nums2, int nums2Place) {
        long total1 = 0, total2 = 0;
        while (nums1Place < nums1.length || nums2Place < nums2.length) {
            if (nums1Place >= nums1.length) {
                total2 += nums2[nums2Place++];
            } else if (nums2Place >= nums2.length) {
                total1 += nums1[nums1Place++];
            } else {
                if (nums1[nums1Place] < nums2[nums2Place]) {
                    total1 += nums1[nums1Place++];
                } else if (nums1[nums1Place] > nums2[nums2Place]) {
                    total2 += nums2[nums2Place++];
                } else {
                    total1 += nums1[nums1Place++];
                    total2 += nums2[nums2Place++];
                    long nextTotal = maxSumFrom(nums1, nums1Place, nums2, nums2Place);
                    total1 += nextTotal;
                    total2 += nextTotal;
                    break;
                }
            }
        }
        return Math.max(total1, total2);
    }
}

//My Solution: Merge sort, curMax at nums1 = max(previousMaxAtNum1 + curVal, previousMaxAtNum2 + curVal);
                           curMax at nums2 = max(previousMaxAtNum2 + curVal, previousMaxAtNum1 + curVal); 
class Solution {
    public int maxSum(int[] nums1, int[] nums2) {
        int mod = (int)1e9 + 7;
        if(nums1.length < nums2.length){
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        int len1 = nums1.length;
        int len2 = nums2.length;
        HashMap<Integer, Integer> map1 = new HashMap();
        HashMap<Integer, Integer> map2 = new HashMap();
        
        for(int i = 0; i < len1; ++i){
            map1.put(nums1[i], i);
        }
        
        for(int i = 0; i < len2; ++i){
            map2.put(nums2[i], i);
        }
        
        long[][] dp = new long[2][len1];
        long prev = 0;
        int idx1 = 0;
        int idx2 = 0;
        while(idx1 < len1){
            if(idx2 < len2 && nums2[idx2] < nums1[idx1]){
                if(idx2 == 0){
                    dp[1][idx2] = nums2[idx2];
                }else{
                    dp[1][idx2] = dp[1][idx2 - 1] + nums2[idx2];
                }
                if(map1.containsKey(nums2[idx2])){
                    prev = dp[0][map1.get(nums2[idx2])];
                    dp[1][idx2] = Math.max(dp[1][idx2], prev);
                }
                ++idx2;
            }else{
                if(idx1 == 0){
                    dp[0][idx1] = nums1[idx1];
                    if(map2.containsKey(nums1[idx1])){
                        dp[0][idx1] = Math.max(dp[0][idx1], dp[1][map2.get(nums1[idx1])]);
                    }
                }else{
                    dp[0][idx1] = dp[0][idx1 - 1] + nums1[idx1];
                    if(map2.containsKey(nums1[idx1 - 1])){
                        prev = dp[1][map2.get(nums1[idx1 - 1])] + nums1[idx1];
                        dp[0][idx1] = Math.max(dp[0][idx1], prev);
                    }
                }
                ++idx1;
            }
        }
        if(idx2 == 0){
            dp[1][idx2] = nums2[idx2];
            if(map1.containsKey(nums2[idx2])){
                dp[1][idx2] = Math.max(dp[1][idx2], dp[0][map1.get(nums2[idx2])]);
            }
            ++idx2;
        }
        while(idx2 < len2){
            dp[1][idx2] = dp[1][idx2 - 1] + nums2[idx2];
            if(map1.containsKey(nums2[idx2 - 1])){
                prev = dp[0][map1.get(nums2[idx2 - 1])] + nums2[idx2];
                dp[1][idx2] = Math.max(dp[1][idx2], prev);
            }
            ++idx2;
        }
        return (int)(Math.max(dp[0][len1 - 1], dp[1][len2 - 1]) % mod);
    }
}

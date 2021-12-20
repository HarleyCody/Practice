//Best Solution: sort array for compute (compute in sorted array can be faster guessing there is find pointer in hashmap)
//Compute the sum of first two arrays and iterate through the second to check answer
class Solution {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		Arrays.sort(nums3);
		Arrays.sort(nums4);
	
        int count = 0;
		
		var map = new HashMap<Integer, Integer>();
		for (int m: nums1) {
			for (int n: nums2) {
				map.compute(m + n, (k, v) -> v == null ? 1: v + 1);
			}
		}
		
		for (int m: nums3) {
			for (int n: nums4) {
				count += map.getOrDefault(-(m + n),0);
			}
		}
		
		return count;
    }
}

//My Solution: recorde result in two hashmap and find result from map1 to map2
class Solution {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        HashMap<Integer, Integer> sum1 = new HashMap();
        HashMap<Integer, Integer> sum2 = new HashMap();
        int len = nums1.length;
        for(int i = 0; i < len; ++i){
            for(int j = 0; j < len; ++j){
                int val = nums1[i] + nums2[j];
                int freq = sum1.getOrDefault(val, 0);
                sum1.put(val, freq + 1);
                
                val = nums3[i] + nums4[j];
                freq = sum2.getOrDefault(val, 0);
                sum2.put(val, freq + 1);
            }
        }
        int ans = 0;
        for(int val1 : sum1.keySet()){
            int freq1 = sum1.get(val1);
            int freq2 = sum2.getOrDefault(-val1, 0);
            ans += freq1 * freq2;
        }
        
        return ans;
    }
}

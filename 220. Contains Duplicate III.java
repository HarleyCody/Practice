__________________________________________________________________________________Best Solution_____________________________________________________________________________
class Solution {
    // treat hashmap as sliding window, getIdx will return the position of nums in i;
    // only need to find cloest num and calculate diff
    public long getID(long num, long width) {
        return num < 0 ? num / width - 1 : num / width;
    }
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(t < 0) return false;
        HashMap<Long, Long> map = new HashMap<>();
        long width = (long)(t + 1);
        for(int i = 0; i < nums.length; i++) {
            long index = getID( nums[i], width);
            if(map.containsKey(index))
                return true;
            if(map.containsKey(index - 1) && Math.abs(nums[i] - map.get(index - 1)) <= t) {
                return true;
            }
            if(map.containsKey(index + 1) && Math.abs(nums[i] - map.get(index + 1)) <= t){
                return true;
            }
            map.put(index, (long) nums[i]);
            if(map.size() > k) {
                map.remove(getID(nums[i-k], width));
            }
        }
        return false;
    }
}
____________________________________________________________________________________My Solution_____________________________________________________________________________
// Idea check floor and ceiling to get min diff for every num 
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeMap<Integer, Integer> recorder = new TreeMap<Integer, Integer>();
        
        int len = nums.length;
        for(int i = 0; i < len; ++i){
            Integer l = recorder.ceilingKey(nums[i]);
            if(l != null && (long)l - nums[i] <= t){
                return true;
            }
            
            Integer r = recorder.floorKey(nums[i]);
            if(r != null && (long)nums[i] - r <= t){
                return true;
            }
            
            int f = recorder.getOrDefault(nums[i], 0);
            recorder.put(nums[i], f + 1);
            
            if(i >= k){
                f = recorder.get(nums[i - k]);
                if(f == 1){
                    recorder.remove(nums[i - k]);
                }else{
                    recorder.put(nums[i - k], f);
                }
            }
        }
        
        return false;
    }
}

__________________________________________________________________________My Solution_________________________________________________________________
class Solution {
    //record freq and add ans
    HashMap<Integer, Integer> recorder = new HashMap();
    HashSet<Integer> ansSet = new HashSet();
    public List<Integer> majorityElement(int[] nums) {
        int len = nums.length;
        for(int n : nums){
            int f = recorder.getOrDefault(n, 0);
            recorder.put(n, f + 1);
            if(f + 1 > len / 3){
                ansSet.add(n);
            }
        } 
        return new ArrayList(ansSet);
    }
}

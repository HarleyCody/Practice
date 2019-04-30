_________________________________________________________Better Solution__________________________________________________________________
class Solution{
public List<Integer> topKFrequent(int[] nums, int k) {

	    List<Integer>[] bucket = new List[nums.length + 1];
	    Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();

	    for (int n : nums) { // record the frequence of num;
		    frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1); // num is key frequency is value;
	    }

	    for (int key : frequencyMap.keySet()) { // record the num by the frequence in an array, so in the reverse order it is descending order
		    int frequency = frequencyMap.get(key); //get frequency of num;
		    if (bucket[frequency] == null) {// stored by frequency
			    bucket[frequency] = new ArrayList<>();
		    }
		    bucket[frequency].add(key); // num with same frequence will be record, key is unique, so there will be no overlap.
	    }

	    List<Integer> res = new ArrayList<>();

	    for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
		    if (bucket[pos] != null) {
			    res.addAll(bucket[pos]);
		    }
	    }
	    return res;
    }
}

_________________________________________________________Sort HashMap_______________________________________________________________________
class Solution {
    private class ValueComparator implements Comparator<Map.Entry<Integer, Integer>> {  
        public int compare(Map.Entry<Integer, Integer> mp1, Map.Entry<Integer, Integer> mp2){  
	            return mp2.getValue() - mp1.getValue();  
	        }
    }
    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer,Integer> com = new HashMap<Integer,Integer>();
        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i < nums.length; ++i){
            com.put(nums[i], com.getOrDefault(nums[i],0) + 1);
        }
        List<Map.Entry<Integer,Integer>> list=new ArrayList<>();
	list.addAll(com.entrySet());
	ValueComparator vc=new ValueComparator();
	Collections.sort(list,vc);
        int c = 0;
        Iterator<Map.Entry<Integer,Integer>> it = list.iterator();
        it.hasNext();
        while (++c <= k){
            ans.add(it.next().getKey());
        }
        return ans;
    }
}

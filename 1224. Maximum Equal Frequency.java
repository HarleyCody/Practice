class Solution {
    Map<Integer, Integer> count = new HashMap<>();
    Map<Integer, Integer> freq = new HashMap<>();
    
    public int maxEqualFreq(int[] nums) {
        int res = 1;
        for (int i = 0; i < nums.length; i++) {
            int c = count.getOrDefault(nums[i], 0);
            count.put(nums[i], c + 1);
            
            if (freq.containsKey(c)) {
                freq.put(c, freq.get(c) - 1);
                if (freq.get(c) == 0) {
                    freq.remove(c);
                }
            }
            int nc = count.get(nums[i]);
            freq.put(nc, freq.getOrDefault(nc, 0) + 1);
            // as it is longest prefix, must start from index 0; so i + 1 is length;
            if (valid(i)) res = i + 1;
        }
        return res;
    }
    private boolean valid(int index) {
        //如果只有一个数
        if (count.size() == 1) return true;
        //如果所有数都只出现了一次
        if (count.size() == index + 1) return true;
        // 
        if (freq.size() != 2) return false;
        
        List<Integer> freqK = new ArrayList<>(freq.keySet());
        Collections.sort(freqK);
        int f1 = freqK.get(0), f2 = freqK.get(1);
        //如果只有一个数出现了一次，其余的数都出现多次
        if (f1 == 1 && freq.get(f1) == 1) return true;
        //如果只有一个数出现次数是最多的，其余出现次数都相等并且出现的次数比较大的那个频率出现少一次
        if (freq.get(f2) == 1 && f1 + 1 == f2) return true;
        return false;
    }
}

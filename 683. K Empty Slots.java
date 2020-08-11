________________________________________________________________________________Best Solution________________________________________________________________________________
// low hight record boarder the lower idx and higher idx that first left, and bulb will turn on
// check range day by day so must be empty if find any position
// space compressed by % bSize as there might be some bulbs never light up.
class Solution {
    public int kEmptySlots(int[] bulbs, int k) {
        int n = bulbs.length;
        if (n == 0 || k >= n) return -1;
        
        int bSize = k + 1;
        int bNum = (n + bSize - 1) / bSize;
        
        int[] lows = new int[bNum];
        int[] highs = new int[bNum];
        Arrays.fill(lows, Integer.MAX_VALUE);
        Arrays.fill(highs, Integer.MIN_VALUE);
        
        for (int i = 0; i < n; i++) {
            int x = bulbs[i] - 1;
            int position = x / bSize;
            if (x < lows[position]) {
                lows[position] = x;
                if (position > 0 && x - highs[position - 1] - 1 == k) {
                    return i + 1;
                }
            }
            if (x > highs[position]) {
                highs[position] = x;
                if (position < bNum - 1 && lows[position + 1] - x - 1 == k) {
                    return i + 1;
                }
            }
        }
        
        return -1;
    }
}
________________________________________________________________________________2nd Best Solution________________________________________________________________________________
// find bulb turns on between left and right, turn on after left and right means its empty between left and right;
// when i == right means all bulbs between left and right are off otherwise, left and right will be updated larger and scan again 
class Solution {
    public int kEmptySlots(int[] flowers, int k) {
        int[] days =  new int[flowers.length];
        for(int i=0; i<flowers.length; i++)days[flowers[i] - 1] = i + 1;
        int left = 0, right = k + 1, res = Integer.MAX_VALUE;
        for(int i = 0; right < days.length; i++){
            if(days[i] < days[left] || days[i] <= days[right]){
                if(i == right)res = Math.min(res, Math.max(days[left], days[right]));   //we get a valid subarray
                left = i; 
                right = k + 1 + i;
            }
        }
        return (res == Integer.MAX_VALUE)?-1:res;
    }
}
________________________________________________________________________________My Solution________________________________________________________________________________
class Solution {
    //Tree set to count distance between two vals
    public int kEmptySlots(int[] bulbs, int K) {
        int len = bulbs.length;
        
        TreeSet<Integer> recorder = new TreeSet();
        
        for(int i = 0; i < len; ++i){
            int idx = bulbs[i];
            Integer prev = recorder.lower(idx);
            if(prev != null && idx - prev == K + 1){
                return i + 1;
            }
            
            Integer next = recorder.higher(idx);
            if(next != null && next - idx == K + 1){
                return i + 1;
            }
            recorder.add(idx);
        }
        return -1;
    }
}

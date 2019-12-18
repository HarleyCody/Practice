______________________________________My Solution(Sliding Window with Array 256)_______________________________________________
class Solution {
    int[] count = new int[256];
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if(k < 1) return 0;
        int left = 0, right = 0;
        char[] chs = s.toCharArray();
        int len = chs.length;
        if(len <= k) return len;
        
        int ans = 0, num = 0, size = 0;
        for(; right < len; ++right){
            // new char
            if(count[chs[right]] == 0){
                ++size;
            }
            ++count[chs[right]];
            
            while(size > k){
                // old char is deleted
                if(count[chs[left]] == 1){
                    --size;
                }
                --count[chs[left]];
                ++left;
            }
            ans = Math.max(ans, (right - left) + 1);
        }
        return ans;
    }
}
_________________________________________My Solution(Sliding Window with HashMap)_____________________________________________
class Solution {
    HashMap<Character, Integer> recorder = new HashMap();
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if(k < 1) return 0;
        int left = 0, right = 0;
        char[] chs = s.toCharArray();
        int len = chs.length;
        if(len <= k) return len;
        
        // record size to be faster
        int ans = 0, num = 0, size = 0;
        for(; right < len; ++right){
            num = recorder.getOrDefault(chs[right], 0);
            // new char size + 1
            if(num == 0){
                ++size;
            }
            recorder.put(chs[right], num + 1);
            
            while(size > k){
                num = recorder.get(chs[left]);
                // delete char, size - 1;
                if(num == 1){
                    --size;
                }
                recorder.put(chs[left], num - 1);
                left++;
            }
            ans = Math.max(ans, (right - left) + 1);
        }
        return ans;
    }
}

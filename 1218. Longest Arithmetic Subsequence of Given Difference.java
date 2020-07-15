_______________________________________________________________________________Best Solution_____________________________________________________________________________________
class Solution {
// use arr instead of hashmap
     public int longestSubsequence(int[] arr, int difference) {
        int len = arr.length;
        int res = 0;
        int[] count = new int[40000];
        for (int i = 0; i < len; i++) {
            arr[i] += 20000;
        }
        count[arr[len - 1]] = 1;
        for (int i = len - 2; i >= 0; i--) {
            int tempAns = count[arr[i] + difference] + 1;
            count[arr[i]] = tempAns;
            res = Math.max(res, tempAns);
        }
        return res;
    }
}

_______________________________________________________________________________My Solution_____________________________________________________________________________________
class Solution {
    // record max len of valid subsequence at arr[i];
    // curLen = prev + 1
    // prev = curVal - diff
    int ans = 0;
    HashMap<Integer, Integer> recorder = new HashMap();
    public int longestSubsequence(int[] arr, int difference) {
        int len = arr.length;
        recorder.put(arr[0], 1);
        for(int i = 0; i < len; ++i){
            int prev = arr[i] - difference;
            int prevNum = recorder.getOrDefault(prev, 0);
            ans = Math.max(prevNum + 1, ans);
            recorder.put(arr[i], prevNum + 1);
        }
        return ans;
    }
}

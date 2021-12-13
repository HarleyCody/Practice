//Best Solution: Recorder frequence by count, how many number have count i == dp[count]
//Remove unique from the most smallest count
class Solution {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        int n = arr.length, count = 1, unique = 1;
        int[] dp = new int[n + 1];
        Arrays.sort(arr);
        for(int i = 1; i < n; i++) {
            if(arr[i] == arr[i - 1]) {
                count++;
            } else {
                dp[count]++;
                count = 1;
                unique++;
            }
        }
        dp[count]++;
        for(int i = 1; i <= n; i++) {
            if(i * dp[i] <= k) {
                k -= i * dp[i];
                unique -= dp[i];
            } else {
                int tmp = k / i;
                unique -= tmp;
                break;
            }
        }
        return unique;
    }
}
//My Solution: recorder freq by array and removal from less frequent number
class Solution {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        int len = arr.length;
        if(len == k) return 0;
        int[] freqArr = new int[len];
        
        int idx = 0;
        int pos = 0;
        HashMap<Integer, Integer> posMap = new HashMap();
        for(int i : arr){
            if(posMap.containsKey(i)){
                pos = posMap.get(i);
            }else{
                pos = idx;
                posMap.put(i, idx++);
            }
            ++freqArr[pos];
        }
        
        Arrays.sort(freqArr);
        idx = len - posMap.size();
        while(idx < freqArr.length && k > 0){
            k -= freqArr[idx++];
        }
        
        return k == 0 ? len - idx : len - idx + 1; 
    }
}
//My Solution: Greedy, remove element as much as it can.
class Solution {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        int len = arr.length;
        if(len == k) return 0;
        
        HashMap<Integer, Integer> freqMap = new HashMap();
        for(int i : arr){
            int freq = freqMap.getOrDefault(i, 0);
            freqMap.put(i, freq + 1);
        }
        int[] freqArr = new int[freqMap.size()];
        int idx = 0;
        for(int key : freqMap.keySet()){
            freqArr[idx++] = freqMap.get(key);
        }
        
        Arrays.sort(freqArr);
        idx = 0;
        while(idx < freqArr.length && k > 0){
            k -= freqArr[idx++];
        }
        
        return k == 0 ? freqArr.length - idx : freqArr.length - idx + 1; 
    }
}

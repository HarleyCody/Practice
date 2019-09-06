______________________________________________________________My Solution_________________________________________________________________
class Solution {
    HashSet<String> recorder = new HashSet();
    HashMap<String, Integer> dp = new HashMap();
    int curMax = 0;
    public int longestStrChain(String[] words) {
        int ans = 0;
        for(String str : words){
            recorder.add(str);
        }
        for(String str : words){
            getLongestChain(str, 1);
            dp.put(str, curMax);
            ans = Math.max(ans, curMax);
            curMax = 1;
        }
        return ans;
    }
    private void getLongestChain(String word, int cur){
        for(int i = 0; i < word.length(); i++){
            StringBuilder newStr = new StringBuilder(word.substring(0, i));
            newStr = newStr.append(word.substring(i + 1));
            if(dp.containsKey(newStr.toString())){
                curMax = Math.max(cur + dp.get(newStr.toString()), curMax);
            }
            else if(recorder.contains(newStr.toString())){
                getLongestChain(newStr.toString(), cur + 1);
            }
        }
        curMax = Math.max(cur, curMax);
    }
}

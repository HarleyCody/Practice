//Best Solution: Try to link the chain by the word in specific length;
//Try to match by DFS and Memoization
//wordIndice[len][0] records the size of words with length
//wordIndice[len][i] records the index of word in words Array so it can get the words directly and retrive index to do the momization more efficiently
//mem[len][i] record the result for word at words[wordIndice[len][i]]
class Solution {
    static final int LONGEST_WORD_LEN = 16;
    
    public int longestStrChain(String[] words) {
        int maxFrequency = 0;
        int maxWordLen = 0;
        int[] lenCounts = new int[LONGEST_WORD_LEN + 1];
        for (String word : words) {
            maxFrequency = Math.max(maxFrequency, ++lenCounts[word.length()]);
            maxWordLen = Math.max(maxWordLen, word.length());
        }
            
        int[][] wordIndice = new int[maxWordLen + 1][maxFrequency + 1];

        for (int i = 0; i < words.length; i++) {
            int len = words[i].length();
            wordIndice[len][++wordIndice[len][0]] = i;
        }
        
        int[][] memo = new int[maxWordLen + 1][maxFrequency + 1];

        int ans = 1;
        for (int len = maxWordLen; len > ans; len--){
            for (int wordIdx = wordIndice[len][0]; wordIdx >= 1 && len > ans; --wordIdx){
                ans = Math.max(ans, findPath(len, wordIdx, wordIndice, memo, words));
            }
        }
            
        return ans;
    }

    private int findPath(int len, int wordIdx, int[][] wcs, int[][] memo, String[] words) {
        if(len == 1) return 1;
        if(memo[len][wordIdx] > 0) return memo[len][wordIdx];
        String curWord = words[wcs[len][wordIdx]];
        
        int ans = 0;
        for (int preIdx = wcs[len - 1][0]; preIdx >= 1 && ans + 1 < len; preIdx--) {
            if (isMatch(curWord, words[wcs[len - 1][preIdx]])){
                ans = Math.max(ans, findPath(len - 1, preIdx, wcs, memo, words));
            }
        }
            
        memo[len][wordIdx] = ans + 1;
        return ans + 1;
    }

    private boolean isMatch(String preWord, String curWord) {
        boolean misMatch = false;
        int preIdx = 0;
        int curIdx = 0;
        while(curIdx < curWord.length()){
            if(curWord.charAt(curIdx++) != preWord.charAt(preIdx++)){
                if(misMatch) return false;
                misMatch = true;
                --curIdx;
            }
        }
        
        return true;
    }
}
______________________________________________________________Best Solution_______________________________________________________________
/* categorize word with length
check word in adjascent list recurlsively to get max length*/
class Solution {
    int[] mem;
    List<String>[] collector = new ArrayList[16];
    HashMap<String, Integer> indice = new HashMap();
    public int longestStrChain(String[] words) {
        int len = words.length;
        mem = new int[len];
        for(String word : words){
            len = word.length();
            if(collector[len - 1] == null){
                collector[len - 1] = new ArrayList();
            }
            collector[len - 1].add(word);
        }
        
        len = words.length;
        int ans = 1;
        for(int i = 0; i < words.length; ++i){
            if(ans >= words[i].length()) continue;
            ans = Math.max(ans, find(words, i));
        }
        
        return ans;
    }
    
    private int find(String[] words, int idx){
        int len = words[idx].length();
        if(len == 1 || collector[len - 2] == null) return 1;
        if(mem[idx] > 0) return mem[idx];
        
        String preWord = words[idx];
        int ans = 0;
        for(int i = 0; i < words.length; ++i){
            if(words[i].length() != len - 1 || ans >= words[i].length()) continue;
            if(match(words[i], preWord)){
                ans = Math.max(ans, find(words, i));
            }
        }
        mem[idx] = ans + 1;
        return ans + 1;
    }
    
    private boolean match(String curWord, String preWord){
        boolean misMatch = false;
        int pIdx = 0;
        int cIdx = 0;
        while(cIdx < curWord.length()){
            if(curWord.charAt(cIdx++) != preWord.charAt(pIdx++)){
                if(misMatch) return false;
                misMatch = true;
                --cIdx;
            }
        }
        
        return true;
    }
}
______________________________________________________________My Solution_________________________________________________________________
class Solution {

    // candidates
    HashSet<String> recorder = new HashSet();
    //record max Chain for every starting word in words
    HashMap<String, Integer> dp = new HashMap();
    int curMax = 0;
    public int longestStrChain(String[] words) {
        int ans = 0;
        
        // initialize candidate
        for(String str : words){
            recorder.add(str);
        }
        // start from every word
        for(String str : words){
            getLongestChain(str, 1);
            // recorder max Chain start with word str;
            dp.put(str, curMax);
            // update ans
            ans = Math.max(ans, curMax);
            curMax = 1;
        }
        return ans;
    }
    // check substring of word recursivly
    private void getLongestChain(String word, int cur){
        // try every possible substring with omittming one letter
        for(int i = 0; i < word.length(); i++){
        
            // cut words with omitting one letter
            StringBuilder newStr = new StringBuilder(word.substring(0, i));
            newStr = newStr.append(word.substring(i + 1));
            // speed up, substring exist and max chain of it has been calcualted
            if(dp.containsKey(newStr.toString())){
                // get maxLength, it can comparing multiple times as different substring has different max chain length.
                curMax = Math.max(cur + dp.get(newStr.toString()), curMax);
            }
            // maxLength of current substring has not been calculate before, recursivly calculate it.
            else if(recorder.contains(newStr.toString())){
                getLongestChain(newStr.toString(), cur + 1);
            }
        }
        // update max chain length of current starting word.
        curMax = Math.max(cur, curMax);
    }
}

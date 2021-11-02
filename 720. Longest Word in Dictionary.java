//My Solution: DFS to find and dp to record
class Solution {
    int length = 0;
    String ans = "";
    List<String>[] collector = new List[1001];
    HashMap<String, Integer> dp = new HashMap();
    public String longestWord(String[] words) {
        for(String word : words){
            int len = word.length();
            if(collector[len] == null){
                collector[len] = new ArrayList();
            }
            collector[len].add(word);
        }
        collector[0] = new ArrayList();
        collector[0].add(ans);
        
        Arrays.sort(words, (a, b) -> a.length() == b.length() ? a.compareTo(b) : a.length() - b.length());
        for(int i = words.length - 1; 0 <= i; --i){
            if(words[i].length() < length) break;
            find(words[i]);
        }
        
        return ans;
    }
    
    private int find(String word){
        if(dp.containsKey(word)) return dp.get(word);
        int len = word.length();
        if(len == 0) return 0;
        if(collector[len - 1] == null) return -1;
        
        int ansLen = -1;
        for(String nWord : collector[len - 1]){
            if(!match(word, nWord)) continue;
            int nLength = find(nWord);
            if(nLength == -1) continue;
            ansLen = Math.max(ansLen, nLength + 1);
            if(length <= nLength + 1){
                ans = word;
                length = nLength + 1;
            }
        }
        dp.put(word, ansLen);
        return ansLen;
    }
    
    private boolean match(String og, String nWord){
        boolean misMatch = false;
        int oIdx = 0;
        int nIdx = 0;
        while(nIdx < nWord.length()){
            if(og.charAt(oIdx++) != nWord.charAt(nIdx++)){
                if(misMatch) return false;
                misMatch = true;
                --nIdx;
            }
        }
        
        return true;
    }
}

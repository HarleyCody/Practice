class WordDistance {
    // record index of string in words array
    HashMap<String, List<Integer>> recorder;
    public WordDistance(String[] words) {
        recorder = new HashMap();
        for(int i = 0; i < words.length; ++i){
            if(!recorder.containsKey(words[i]))
                recorder.put(words[i], new ArrayList());
            List<Integer> pos = recorder.get(words[i]);
            pos.add(i);
            recorder.put(words[i], pos);
        }
    }
    
    public int shortest(String word1, String word2) {
        int ans = Integer.MAX_VALUE;
        List<Integer> pos1 = recorder.get(word1);
        List<Integer> pos2 = recorder.get(word2);
        // abs(index of word1 - word2), find smallest one 
        for(Integer p1 : pos1){
            for(Integer p2 : pos2){
                ans = ans > Math.abs(p2 - p1)? Math.abs(p2 - p1) : ans; 
            }
        }
        return ans;
    }
}

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(words);
 * int param_1 = obj.shortest(word1,word2);
 */

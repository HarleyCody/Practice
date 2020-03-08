class Solution {
    // union pair and find in string array one by one;
    // cur, parent
    HashMap<String, String> parent = new HashMap();
    HashMap<String, Integer> rank = new HashMap();
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs) {
        int len1 = words1.length, len2 = words2.length;
        if(len1 != len2) return false;
        
        for(List<String> pair : pairs){
            String word1 = pair.get(0);
            String word2 = pair.get(1);
            // set root to itself, if it was not set before
            parent.putIfAbsent(word1, word1);
            parent.putIfAbsent(word2, word2);
            union(word1, word2);
        }
        
        for(int i = 0; i < len1; ++i){
            String word1 = words1[i];
            String word2 = words2[i];
            if(word1.equals(word2))continue;
            
            if(!find(word1).equals(find(word2))){
                return false;
            }
        }
        return true;
    }
    
    private void union(String word1, String word2){
        String parent1 = find(word1);
        String parent2 = find(word2);
        // new node, rank will be 0;
        int word1Rank = rank.getOrDefault(parent1, 0);
        int word2Rank = rank.getOrDefault(parent2, 0);
        // inset by rank, low rank insert to high rank by merging parent
        if(word1Rank > word2Rank){
            parent.put(parent2, parent1);
        }else if(word2Rank > word1Rank){
            parent.put(parent1, parent2);
        }else{
            parent.put(parent2, parent1);
            rank.put(parent1, word1Rank + 1);
        }
    }
    private String find(String word1){
        // incase of word is not in pair, so it will get itself as parent
        String p = parent.getOrDefault(word1, word1);
        while(!p.equals(word1)){
            word1 = p;
            p = parent.get(word1);
        }
        return p;
    }
}

class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> recorder = new HashMap();
        List<String> ans = new ArrayList();
        
        //record frequency of words
        for(String i : words){
            recorder.put(i, recorder.getOrDefault(i, 0) + 1);
        }
        
        //Override comparator, identifer is Map.Entry<TYPE,TYPE>
        Comparator<Map.Entry<String, Integer>> sortByInter = new Comparator<Map.Entry<String, Integer>>(){
            @Override
            public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2){
                
                // same value, sort by key in alphabetic order
                if(e2.getValue() == e1.getValue())return e1.getKey().compareTo(e2.getKey());
                // sort by value in descending order
                return e2.getValue() - e1.getValue();
            } 
        };
        
        // Collection sort should use list to sort entry;
        List<Map.Entry<String, Integer>> sorted = new ArrayList(recorder.entrySet());
        // Sort entry
        Collections.sort(sorted, sortByInter);
        //put k elements to ans List;
        for(Map.Entry<String, Integer> e : sorted){
            ans.add(e.getKey());
            if(ans.size() == k) break;
        }
        return ans;
    }
}

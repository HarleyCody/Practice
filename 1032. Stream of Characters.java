class StreamChecker {
    // build trie in reverse order
    // query trie in normal order
    // only query, at most, max length of trie. if not found stop query return false;
    
    class TrieNode{
        TrieNode[] child = new TrieNode[26];
        boolean isWord;
    }
    
    TrieNode root;
    int maxLen = 0;
    List<Character> queries;
    public StreamChecker(String[] words) {
        root = new TrieNode();
        queries = new ArrayList();
        
        for(String word : words){
            maxLen = Math.max(maxLen, word.length());
            buildTree(word);
        }
    }
    
    public boolean query(char letter) {
        queries.add(letter);
        
        TrieNode queryNode = root;
        int size = queries.size();
        int end = Math.min(size, maxLen);
        for(int i = 1; i <= end; ++i){
            int idx = queries.get(size - i) - 'a';
            if(queryNode.child[idx] == null) return false;
            queryNode = queryNode.child[idx];
            if(queryNode.isWord) return true;
        }
        return false;
    }
    
    private void buildTree(String word){
        TrieNode cur = root;
        char[] chs = word.toCharArray();
        int len = chs.length;
        for(int i = len - 1; 0 <= i; --i){
            int idx = chs[i] - 'a';
            if(cur.child[idx] == null){
                cur.child[idx] = new TrieNode();
            }
            cur = cur.child[idx];
        }
        cur.isWord = true;
    }
}

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words);
 * boolean param_1 = obj.query(letter);
 */

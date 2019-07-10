__________________________________________________________Best Solution__________________________________________________________________
class WordDictionary {
    // Trie record path to every word
    class TrieNode{
        TrieNode[] child = new TrieNode[26];
        boolean isWord = false;
    }
    TrieNode root;
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }
    // add word to Trie(expand Trie)
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode node = root;
        for(char c : word.toCharArray()){
            if(node.child[c - 'a'] == null){
                node.child[c - 'a'] = new TrieNode();
            }
            node = node.child[c - 'a'];
        }
        node.isWord = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return match(word.toCharArray(), 0, root);
    }
    // check if word exist
    private boolean match(char[] chs, int pos, TrieNode root){
        // base case
        if(pos == chs.length) return root.isWord;
        // acutal aphabetic here, check with known child node and check later part. If both of them is true, then return true
        if(chs[pos] != '.'){
            return (root.child[chs[pos] - 'a'] != null && match(chs, pos + 1, root.child[chs[pos] - 'a']));
        }
        // . can match with any alphabet, so for loop to match it with every possible child node, and check next one. If both of them is true, then return true
        else{
            for(int i = 0; i < root.child.length; ++i){
                if(root.child[i] != null && match(chs, pos + 1, root.child[i])){
                        return true;
                }
            }
        }
        return false;
    }
}
___________________________________________________________My Solution(Trie)_____________________________________________________________
/
class WordDictionary {
    // Trie record path to every word
    class TrieNode{
        TrieNode[] child = new TrieNode[26];
        boolean isWord;
    }
    TrieNode root;
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }
    // add word to Trie(expand Trie)
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode node = root;
        for(char c : word.toCharArray()){
            if(node.child[c - 'a'] == null)
                node.child[c - 'a'] = new TrieNode();
            node = node.child[c - 'a'];
        }
        node.isWord = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return search(word.toCharArray(), 0, root);
    }
    // check if word exist
    private boolean search(char[] chs, int pos, TrieNode root){
        // base case
        if(pos == chs.length) return root.isWord;
        // . can match with any alphabet, so for loop to match it with every possible child node, and check next one. If both of them is true, then return true
        if(chs[pos] == '.'){
            for(int i = 0; i < root.child.length; ++i){
                if(root.child[i] != null && search(chs, pos + 1, root.child[i])){
                    return true;
                }
            }
        }
        // acutal aphabetic here, check with known child node and check later part. If both of them is true, then return true
        else{
            if (root.child[chs[pos] - 'a'] != null && search(chs, pos + 1, root.child[chs[pos] - 'a'])){
                return true;
            }
        }
        return false;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */

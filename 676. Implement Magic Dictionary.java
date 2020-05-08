____________________________________________________________My(Best) Solution_____________________________________________________________
class MagicDictionary {
    // Trie to match word
    
    /* Find: dfs to find
        if isChanged before only matched node to continue;
        other continue to find while update isChange for every step of search;
        
         For match node is prev node should compare node.child[at chs[level] - 'a'] != null;
         ie: node.child[childIdx] != null && childIdx = chs[level] - 'a';
    */
    class TrieNode{
        TrieNode[] child = new TrieNode[26];
        boolean isWord;
    }
    /** Initialize your data structure here. */
    TrieNode root;
    public MagicDictionary() {
        root = new TrieNode();
    }
    
    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        for(String word : dict){
            buildTree(word);
        }
    }
    
    private void buildTree(String word){
        TrieNode cur = root;
        
        char[] chs = word.toCharArray();
        for(int i = 0; i < chs.length; ++i){
            int idx = chs[i] - 'a';
            if(cur.child[idx] == null){
                cur.child[idx] = new TrieNode();
            }
            cur = cur.child[idx];
        }
        cur.isWord = true;
    }
    
    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
        TrieNode cur = root;
        //System.out.println("Word " + word);
        return dfsSearch(cur, word.toCharArray(), 0, word.length(), false);
    }
    
    private boolean dfsSearch(TrieNode root, char[] chs, int level, int len, boolean isChanged){
        if(level == len){
            return root.isWord && isChanged;
        }
        boolean ans = false;
        
        TrieNode[] childs = root.child;
        for(int c = 0; c < 26; ++c){
            if(childs[c] == null){
                continue;
            }
            
            //System.out.println("Searchig " + chs[level] + " at " + (char)(c + 'a'));
            if(isChanged){
                if(c != chs[level] - 'a'){
                    continue;
                }
                ans |= dfsSearch(childs[c], chs, level + 1, len, true);
            }else{
                ans |= dfsSearch(childs[c], chs, level + 1, len, c != chs[level] - 'a');
            }
            if(ans == true){
                return true;
            }
        }
        return false;
    }
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dict);
 * boolean param_2 = obj.search(word);
 */

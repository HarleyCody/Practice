// Trie store products
// search through dfs;
class Solution {
    class Trie{
        Trie[] next = new Trie[26];
        boolean isWord;
        String word;
    }
    private List<String> med = new LinkedList();
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Trie root = new Trie();
        for(String str : products){
            add(str, root);
        }
        List<List<String>> ans = new LinkedList();
        for(int i = 0; i < searchWord.length(); ++i){
            med = new LinkedList();
            if(root != null && root.next[searchWord.charAt(i) - 'a'] != null){
                root = root.next[searchWord.charAt(i) - 'a'];
                search(root);
            }else{
                // if product ad root will search abcd for d if root is not null
                // root = null stops search anymore.
                root = null;
            }
            ans.add(med);
        }
        return ans;
    }
    private void add(String str, Trie root){
        char[] c = str.toCharArray();
        int cur = 0;
        while(cur < c.length){
            if(root.next[c[cur] - 'a'] == null){
                root.next[c[cur] - 'a'] = new Trie();
            }
            root = root.next[c[cur] - 'a'];
            ++cur;
        }
        root.isWord = true;
        root.word = str; 
    }
    
    private void search(Trie root){
        if(med.size() == 3) return;
        if(root.isWord) med.add(root.word);
        for(int i = 0; i < 26; ++i){
            if(root.next[i] != null) {
                search(root.next[i]);
            }
        }
    }
}

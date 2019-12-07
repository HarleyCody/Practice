________________________________________________________My Solution____________________________________________________________
class Solution {
    class Trie{
        Trie[] next = new Trie[26];
        boolean isWord = false;
    }
    
    private List<String> ansList = new ArrayList();
    
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Trie root = new Trie();
        for(String str : words){
            if(str.length() == 0) continue;
            buildTrie(root, str, 0);
        }
        for(String str : words){
            if(str.length() == 0) continue;
            if(findTrie(str, 0, root, 0)) ansList.add(str);
        }
        return ansList;
    }
    // search in the tree
    private boolean findTrie(String word, int start, Trie root, int level){
        Trie cur = root;
        int len = word.length();
        for(int i = start; i < len; ++i){
            char c = word.charAt(i);
            // word is not exist in tree;
            if(cur.next[c - 'a'] == null)return false;
            
            cur = cur.next[c - 'a'];
            if(cur.isWord){
                // 1. is the last char
                if(i == len - 1 && level >= 1) return true;
                // check more words
                if(findTrie(word, i + 1, root, level + 1)) return true;
            }
        }
        return false;
    }
    // build tree
    private void buildTrie(Trie r, String s, int level){
        char[] chars = s.toCharArray();
        for(char c : chars){
            if(r.next[c - 'a'] == null){
                r.next[c - 'a'] = new Trie();
            }
            r = r.next[c - 'a'];
        }
        r.isWord = true;
    }
}

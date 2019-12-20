_________________________________________________Best Solution(Trie)__________________________________________________________________
class Solution {
// ignore voews, set it to 0 child node
// search word, same first, then caseSensitive then change voewl
    public String[] spellchecker(String[] wordlist, String[] queries) {
        TrieNode root = new TrieNode();
        for(String word: wordlist) {
            build(root, word);
        }

        String[] res = new String[queries.length];
        for(int i = 0; i < queries.length; i++) {
            res[i] = search(root, queries[i]);
        }
        return res;
    }
    
    public String search(TrieNode root, String query) {
        for(char c: query.toLowerCase().toCharArray()) {
            if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                if(root.children[0] == null) {
                    return "";
                }
                root = root.children[0];
            } else {
                if(root.children[c-'a'] == null) {
                    return "";
                }
                root = root.children[c-'a'];
            }
        }
        String res = "";
        // tier make sure choose the first one;
        boolean tier = false;
        String lower = query.toLowerCase();
        for(int i = 0; i < root.words.size(); i++) {
            String word = root.words.get(i);
            if(word.equals(query)) {
                // same is top priority
                return word;
            } else if(!tier && word.toLowerCase().equals(lower)) {
                // only give the first one if chars are matched without case sensitive
                res = word;
                tier = true;
            } else if(res == "") {
                //change vowels here, continue to search next as might have exactly same word later 
                res = word;
            }
        }
        return res;
    }
    
    public void build(TrieNode root, String word) {
        for(char c: word.toLowerCase().toCharArray()){
            if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                if(root.children[0] == null) {
                    root.children[0] = new TrieNode();
                }
                root = root.children[0];
            } else {
                if(root.children[c-'a'] == null) {
                    root.children[c-'a'] = new TrieNode();
                }
                root = root.children[c-'a'];
            }
        }
        root.words.add(word);
    }
    
    private class TrieNode {
        TrieNode[] children;
        List<String> words;
        
        TrieNode () {
            children = new TrieNode[26];
            words = new ArrayList<>();
        }
    }
}

_________________________________________________Two HashMap__________________________________________________________________
// store lowercase with vowel, first idx in wordlist by recorder hashmap
// store lowercase without vowel, first idx in wordlist by devowel hashmap
class Solution {
    public String[] spellchecker(String[] wordlist, String[] queries) {
        HashSet<String> words = new HashSet(Arrays.asList(wordlist));
        HashMap<String, Integer> recorder = new HashMap();
        HashMap<String, Integer> devowel = new HashMap();
        for(int i = 0; i < wordlist.length; ++i){
            String lower = wordlist[i].toLowerCase();
            recorder.putIfAbsent(lower, i);
            String unvowel = lower.replaceAll("[a,e,i,o,u]", "#");
            devowel.putIfAbsent(unvowel,i);
        }
        for(int i = 0; i < queries.length; ++i){
            if(words.contains(queries[i])) continue;
            String lower = queries[i].toLowerCase();
            String unvowel = lower.replaceAll("[a,e,i,o,u]", "#");
            queries[i] = "";
            if(recorder.containsKey(lower)){
                queries[i] = wordlist[recorder.get(lower)];
            }else if(devowel.containsKey(unvowel)){
                queries[i] = wordlist[devowel.get(unvowel)];
            }
        }
        return queries;
    }
}

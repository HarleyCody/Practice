__________________________________________________Best Solution(Trie DFS from tail to head)____________________________________
// similar to dfs + memo, it build current string with all valid string of substring.
class Solution {
    class Trie{
        Trie[] children;
        boolean isEnd;
        public Trie(){
            children = new Trie[26];
        }
    }
    public List<String> wordBreak(String s, List<String> wordDict) {
        Trie root = new Trie();
        for(String word: wordDict){
            buildTree(word, root);
        }
        
        List<LinkedList<String>> list = find(s,0,new int[s.length()], root);
        List<String> result = new ArrayList<>();
        for(List<String> l: list){
            StringBuilder sb = new StringBuilder();
            
            for(String w: l){
                sb.append(w);
                sb.append(" ");
            }
            result.add(sb.toString().trim());
        }
        return result;
    }
    private void buildTree(String word, Trie root){
        Trie current = root;
        for(char ch: word.toCharArray()){
            int pos = ch - 'a';
            if(current.children[pos] == null){
                current.children[pos] = new Trie();
            }
            current = current.children[pos];
        }
        current.isEnd = true;
    }
    
    // add from subString, every LinkedList made up a tartget string.
    private List<LinkedList<String>> find(String s, int index, int dp[], Trie root){
        List<LinkedList<String>> list = new ArrayList<>();
        if(index == s.length()){
            LinkedList<String> l = new LinkedList<>();
            list.add(l);
            return list;
        }
        if(dp[index] == -1) return list;
        Trie curr = root;
        for(int i = index; i < s.length(); i++){
            int pos = s.charAt(i) - 'a';
            if(curr.children[pos] == null) break;
            curr = curr.children[pos];
            if(curr.isEnd){
                List<LinkedList<String>> nextList = find(s,i+1,dp, root);
                String currWord = s.substring(index,i+1);
                for(LinkedList<String> l: nextList){
                    l.addFirst(currWord);
                    list.add(l);
                }
            }
        }
        // terminate or not the last string must be empty in order to add from it.
        dp[index] = list.isEmpty() ? -1:1;
        return list;
        
   }
}
__________________________________________________Best Solution(DFS + memo)__________________________________________________
class Solution {
    private Map<String, List<String>> map;
    public List<String> wordBreak(String s, List<String> wordDict) {
        map = new HashMap<>();
        return bfs(s, wordDict);
    }
    
    // curString possbility == (curString - word) possibility
    private List<String> bfs(String s, List<String> wordDict) {
        if(map.containsKey(s)) return map.get(s);
        List<String> res = new ArrayList<>();
        if(s.length() == 0) {
            res.add("");
            return res;
        }
        for(String w: wordDict){
            if(s.startsWith(w)) {
                List<String> subList = bfs(s.substring(w.length()), wordDict);
                for(String sub: subList){
                    res.add(w + (sub == ""? "": " ") + sub);
                }
            }
        }
        // store the all the string of s.
        map.put(s, res);
        return res;
    }
}
__________________________________________________My Solution(Trie) TLE_______________________________________________________
class Solution {
    class Trie{
        Trie[] child = new Trie[26];
        boolean isWord;
    }
    List<String> ans = new ArrayList();
    public List<String> wordBreak(String s, List<String> wordDict) {
        Trie root = new Trie();
        for(String str : wordDict){
            buildTrie(str, root);
        }
        find(root, s, new StringBuilder());
        return ans;
    }
    private void buildTrie(String s, Trie root){
        char[] chs = s.toCharArray();
        for(char c : chs){
            if(root.child[c -'a'] == null){
                root.child[c -'a'] = new Trie();
            }
            root = root.child[c - 'a'];
        }
        root.isWord = true;
    }
    private void find(Trie og, String s, StringBuilder sb){
        char[] chs = s.toCharArray();
        Trie root = og;
        for(int i = 0; i < chs.length; ++i){
            root = root.child[chs[i] - 'a'];
            if(root == null) return;
            sb.append(chs[i]);
            if(i == chs.length - 1 && root.isWord){
                ans.add(sb.toString());
                return;
            }
            if(root.isWord){
                StringBuilder temp = new StringBuilder(sb);
                sb.append(" ");
                find(og, s.substring(i + 1), sb);
                sb = temp;
            }
        }
    }
}


//Best Solution: Trie increment is 1 assure that every node must have word
class Solution {
    class TrieNode{
        String word;
        TrieNode[] kids = new TrieNode[26];
    }
    TrieNode root = new TrieNode();
    String ans = "";
    public String longestWord(String[] words) {
        for(String word : words){
            add(word);
        }
        
        for(TrieNode node : root.kids){
            if(node == null || node.word == null) continue;
            find(node);
        }
        return ans;
    }
    
    private void add(String word){
        TrieNode node = root;
        char[] chs = word.toCharArray();
        for(char c : chs){
            if(node.kids[c - 'a'] == null){
                node.kids[c - 'a'] = new TrieNode();
            }
            node = node.kids[c - 'a'];
        }
        node.word = word;
    }
    
    private void find(TrieNode node){
        if(node.word.length() > ans.length() || node.word.length() == ans.length() && ans.compareTo(node.word) > 0){
            ans = node.word;
        }
        for(TrieNode n : node.kids){
            if(n == null || n.word == null) continue;
            find(n);
        }
    }
}
//My Solution: Union Found
class Solution {
    int[] parent;
    String[] words;
    public String longestWord(String[] words) {
        int len = words.length;
        parent = new int[len];
        this.words = words;
        for(int i = 0; i < len; ++i){
            parent[i] = i;
        }
        for(int i = 0; i < len; ++i){
            for(int j = i + 1; j < len; ++j){
                if(find(i) == find(j) || !match(words[i], words[j])) continue;
                union(j, i);
            }
        }
        String ans = "";
        for(int i = 0; i < len; ++i){
            int p = find(i);
            if(words[p].length() != 1 || ans.length() > words[i].length()) continue;
            
            if(ans.length() == words[i].length()){
                ans = ans.compareTo(words[i]) < 0? ans : words[i];
            }else{
                ans = words[i];
            }
        }
        return ans;
    }
    private boolean match(String prev, String latt){
        if(prev.length() > latt.length()){
            String tmp = prev;
            prev = latt;
            latt = tmp;
        }
        
        if(latt.length() - prev.length() != 1) return false;
        
        int pIdx = 0;
        int lIdx = 0;
        boolean misMatch = false;
        while(pIdx < prev.length()){
            if(prev.charAt(pIdx++) != latt.charAt(lIdx++)){
                if(misMatch) return false;
                misMatch = true;
                --pIdx;
            }
        }
        
        return true;
    }
    private int find(int x){
        if(x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }
    
    private void union(int x, int y){
        int px = find(x);
        int py = find(y);
        if(words[px].length() > words[py].length()){
            parent[x] = py;
        }else{
            parent[y] = px;
        }
    }
}
//My Solution: BFS with pruning visited set
class Solution {
    int length = 0;
    String ans = "";
    List<String>[] collector = new List[1001];
    HashMap<String, Integer> dp = new HashMap();
    public String longestWord(String[] words) {
        Set<String> visited = new HashSet();
        Queue<String> q = new LinkedList();
        for(String word : words){
            int len = word.length();
            if(collector[len] == null){
                collector[len] = new ArrayList();
            }
            collector[len].add(word);
            if(len == 1){
                q.offer(word);
                visited.add(word);
            }
        }
        while(!q.isEmpty()){
            String word = q.poll();
            int len = word.length();

            if(ans.length() == len){
                ans = ans.compareTo(word) < 0? ans : word;
            }else if(ans.length() < len){
                ans = word;
            }
            if(collector[len + 1] == null) continue;
            for(String nWord : collector[len + 1]){
                if(!match(word, nWord) || !visited.add(nWord)) continue;
                q.offer(nWord);
            }
        }
        
        return ans;
    }
    
    private boolean match(String src, String tar){
        boolean misMatch = false;
        int tIdx = 0;
        int sIdx = 0;
        while(sIdx < src.length()){
            if(src.charAt(sIdx++) != tar.charAt(tIdx++)){
                if(misMatch) return false;
                misMatch = true;
                --sIdx;
            }
        }
        
        return true;
    }
}
//My Solution: DFS to find and dp to record
class Solution {
    int length = 0;
    String ans = "";
    List<String>[] collector = new List[1001];
    HashMap<String, Integer> dp = new HashMap();
    public String longestWord(String[] words) {
        for(String word : words){
            int len = word.length();
            if(collector[len] == null){
                collector[len] = new ArrayList();
            }
            collector[len].add(word);
        }
        collector[0] = new ArrayList();
        collector[0].add(ans);
        
        Arrays.sort(words, (a, b) -> a.length() == b.length() ? a.compareTo(b) : a.length() - b.length());
        for(int i = words.length - 1; 0 <= i; --i){
            if(words[i].length() < length) break;
            find(words[i]);
        }
        
        return ans;
    }
    
    private int find(String word){
        if(dp.containsKey(word)) return dp.get(word);
        int len = word.length();
        if(len == 0) return 0;
        if(collector[len - 1] == null) return -1;
        
        int ansLen = -1;
        for(String nWord : collector[len - 1]){
            if(!match(word, nWord)) continue;
            int nLength = find(nWord);
            if(nLength == -1) continue;
            ansLen = Math.max(ansLen, nLength + 1);
            if(length <= nLength + 1){
                ans = word;
                length = nLength + 1;
            }
        }
        dp.put(word, ansLen);
        return ansLen;
    }
    
    private boolean match(String og, String nWord){
        boolean misMatch = false;
        int oIdx = 0;
        int nIdx = 0;
        while(nIdx < nWord.length()){
            if(og.charAt(oIdx++) != nWord.charAt(nIdx++)){
                if(misMatch) return false;
                misMatch = true;
                --nIdx;
            }
        }
        
        return true;
    }
}

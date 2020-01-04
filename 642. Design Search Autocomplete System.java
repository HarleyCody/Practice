____________________________________Best Solution(Trie and implement Comparable<Trie>__________________________________________
class AutocompleteSystem {
    class TrieNode implements Comparable<TrieNode> {
        TrieNode[] children;
        String s;
        int times;
        // hot set is node not string;
        // node cause it need to be compared by times first
        List<TrieNode> hot;
        
        public TrieNode() {
            children = new TrieNode[128];
            s = null;
            times = 0;
            hot = new ArrayList<>();
        }
        // sort by time othwerise by string;
        public int compareTo(TrieNode o) {
            if (this.times == o.times) {
                return this.s.compareTo(o.s);
            }
            
            return o.times - this.times;
        }
        // update hot list only contains three record;
        public void update(TrieNode node) {
            if (!this.hot.contains(node)) {
                this.hot.add(node);
            }
            
            Collections.sort(hot);
            
            if (hot.size() > 3) {
                hot.remove(hot.size() - 1);
            }
        }
    }
    
    TrieNode root;
    TrieNode cur;
    StringBuilder sb;
    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        cur = root;
        sb = new StringBuilder();
        
        for (int i = 0; i < times.length; i++) {
            add(sentences[i], times[i]);
        }
    }
    
    
    public void add(String sentence, int t) {
        TrieNode tmp = root;
        
        List<TrieNode> visited = new ArrayList<>();
        for (char c : sentence.toCharArray()) {
            if (tmp.children[c] == null) {
                tmp.children[c] = new TrieNode();
            }
            tmp = tmp.children[c];
            visited.add(tmp);
        }
        // tmp is end of record, contains s for sort later
        tmp.s = sentence;
        tmp.times += t;
        // update for every node in the path to build s;
        for (TrieNode node : visited) {
            node.update(tmp);
        }
    }
    
    public List<String> input(char c) {
        List<String> res = new ArrayList<>();
        if (c == '#') {
            add(sb.toString(), 1);
            sb = new StringBuilder();
            cur = root;
            return res;
        }
        
        sb.append(c);
        if (cur != null) {
            cur = cur.children[c];
        }
        
        if (cur == null) return res;
        for (TrieNode node : cur.hot) {
            // return string not node
            res.add(node.s);
        }
        return res;
    }
}

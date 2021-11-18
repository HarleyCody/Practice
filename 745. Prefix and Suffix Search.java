//Best Solution: TrieNode contains the list of node (index, word)
//find the trie node and traverse the list of node from head to next, 
//head always has the largest index as it was connected by build order
//Find the first word that is end with suffix then there is the word
class TrieNode {
    TrieNode[] children;
    ListNode head;
    
    public TrieNode() {
        children = new TrieNode[26];
        head = null;
    }
}

class ListNode {
    ListNode next;
    String word;
    int index;
    
    public ListNode(String word, int index) {
        this.word = word;
        this.index = index;
    }
}

class WordFilter {

    private final TrieNode root;
    
    public WordFilter(String[] words) {
        root = new TrieNode();
        for (int i = 0; i < words.length; ++i)
            insertWord(words[i], i);
    }
    
    private void insertWord(String word, int index) {
        TrieNode trieNode = root;
        for (char c : word.toCharArray()) {
            if (trieNode.children[c - 'a'] == null)
                trieNode.children[c - 'a'] = new TrieNode();
            
            trieNode = trieNode.children[c - 'a'];
            ListNode listNode = new ListNode(word, index);
            listNode.next = trieNode.head;
            trieNode.head = listNode;
        }
    }
    
    public int f(String prefix, String suffix) {
        TrieNode trieNode = root;
        for (char c : prefix.toCharArray()) {
            if (trieNode.children[c - 'a'] == null)
                return -1;
            
            trieNode = trieNode.children[c - 'a'];
        }
        
        ListNode listNode = trieNode.head;
        while (listNode != null) {
            if (listNode.word.endsWith(suffix))
                return listNode.index;
            
            listNode = listNode.next;
        }
        
        return -1;
    }
}
//My Solution: Trie to find list of suffix and prefix. find the common max in two list
class WordFilter {
    class TrieNode{
        TrieNode[] kids = new TrieNode[26];
        List<Integer> suffix = new ArrayList<Integer>();
        List<Integer> prefix = new ArrayList<Integer>();
    }
    
    TrieNode root = new TrieNode();
    public WordFilter(String[] words) {
        for(int i = 0; i < words.length; ++i){
            buildPrefix(words[i], i);
            buildSuffix(words[i], i);
        }
    }
    
    public int f(String prefix, String suffix) {
        List<Integer> pList = findPrefix(prefix);
        List<Integer> sList = findSuffix(suffix);
        return findMax(pList, sList);
    }
    
    private void buildPrefix(String word, int wIdx){
        TrieNode cur = root;
        char[] chs = word.toCharArray();
        for(int i = 0; i < chs.length; ++i){
            int cIdx = chs[i] - 'a';
            if(cur.kids[cIdx] == null){
                cur.kids[cIdx] = new TrieNode();
            }
            cur = cur.kids[cIdx];
            cur.prefix.add(wIdx);
        }
    }
    
    private void buildSuffix(String word, int wIdx){
        TrieNode cur = root;
        char[] chs = word.toCharArray();
        for(int i = chs.length - 1; 0 <= i; --i){
            int cIdx = chs[i] - 'a';
            if(cur.kids[cIdx] == null){
                cur.kids[cIdx] = new TrieNode();
            }
            cur = cur.kids[cIdx];
            cur.suffix.add(wIdx);
        }
    }
    private List<Integer> findPrefix(String word){
        TrieNode cur = root;
        char[] chs = word.toCharArray();
        for(int i = 0; i < chs.length; ++i){
            int cIdx = chs[i] - 'a';
            if(cur.kids[cIdx] == null || cur.kids[cIdx].prefix == null) return null;
            cur = cur.kids[cIdx];
        }
        return cur.prefix;
    }
    private List<Integer> findSuffix(String word){
        TrieNode cur = root;
        char[] chs = word.toCharArray();
        for(int i = chs.length - 1; 0 <= i; --i){
            int cIdx = chs[i] - 'a';
            if(cur.kids[cIdx] == null || cur.kids[cIdx].suffix == null) return null;
            cur = cur.kids[cIdx];
        }
        return cur.suffix;
    }
    
    private int findMax(List<Integer> pList, List<Integer> sList){
        if(pList == null || sList == null) return -1;
        int pIdx = pList.size() - 1;
        int sIdx = sList.size() - 1;
        while(pIdx >= 0 && sIdx >= 0){
            int pVal = pList.get(pIdx);
            int sVal = sList.get(sIdx);
            if(pVal == sVal) return pVal;
            if(pVal > sVal){
                --pIdx;
            }else{
                --sIdx;
            }
        }
        return -1;
    }
}

/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter obj = new WordFilter(words);
 * int param_1 = obj.f(prefix,suffix);
 */

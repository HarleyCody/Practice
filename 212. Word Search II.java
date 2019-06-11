class Solution {
    class TrieNode{
        // record next entry to find word;
        TrieNode[] next = new TrieNode[26];
        // word has been made up;
        String word;
    }
    
    public List<String> findWords(char[][] board, String[] words) {
        List<String> ans = new ArrayList();
        TrieNode root = buildTrie(words);
        for(int i = 0; i < board.length; ++i){
            for(int j = 0; j < board[0].length; ++j)
                backtrace(board, i, j, root, ans);
        }
        return ans;
    }
    
    public void backtrace(char[][]board, int i, int j, TrieNode p, List<String> ans){
        char c = board[i][j];
        
        // nowhere to go, current char has been used or no next entry
        if(c == '#' || p.next[c - 'a'] == null) return;
        p = p.next[c - 'a'];
        if(p.word != null){
            ans.add(p.word);
            // in case of finding same word;
            p.word = null;
        }
        // in case of use same char repeatedly;
        board[i][j] = '#';
        // dfs travel
        if(i > 0) backtrace(board, i - 1, j, p, ans);
        if(j > 0) backtrace(board, i, j - 1 , p, ans);
        if(i < board.length - 1) backtrace(board, i + 1, j, p, ans);
        if(j < board[0].length - 1) backtrace(board, i, j + 1, p, ans);
        
        //backtrace
        board[i][j] = c;
    }
    
    // build tree based on candidate words in String[] words
    public TrieNode buildTrie(String[] words){
        TrieNode root = new TrieNode();
        for(String word : words){
            TrieNode p = root;
            for(char c : word.toCharArray()){
                int next = c -'a';
                if(p.next[next] == null) p.next[next] = new TrieNode();
                p = p.next[next];
            }
            p.word = word;
        }
        return root;
    }
}

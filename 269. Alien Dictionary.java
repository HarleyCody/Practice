_______________________________________________________________Best Solution____________________________________________________________
class Solution {
    // build graph record relation between chars can c1 reach c2
    // dfs graph to get ans 2 visiting(circle) 3 visited will be ignored in second time visiting 1 unvisited
    private final int N = 26;
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0)
            return "";
        
        boolean[][] adj = new boolean[N][N];
        int[] visited = new int[N];
        buildGraph(words, adj, visited);
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            if (visited[i] == 1) {
                if (!helper(adj, visited, sb, i))
                    return "";
            }
        }
        
        return sb.reverse().toString();
    }
    
    private boolean helper(boolean[][] adj, int[] visited, StringBuilder sb, int i) {
        visited[i] = 2;
        for (int j = 0; j < N; j++) {
            //cycle
            if (adj[i][j]) {
                if (visited[j] == 2)
                    return false;
                if (visited[j] == 1) {
                    if (!helper(adj, visited, sb, j))
                        return false;
                }
            }
        }
        
        visited[i] = 3;
        sb.append((char) (i + 'a'));
        return true;
    }
    
    
    
    private void buildGraph(String[] words, boolean[][] adj, int[] visited) {
        //Arrays.fill(visited, -1);
        for (int i = 0; i < words.length; i++) {
            for (char ch : words[i].toCharArray())
                visited[ch - 'a'] = 1;
            
            if (i > 0) {
                String word1 = words[i - 1];
                String word2 = words[i];
                
                
                // For the case: The input can contain words followed by their prefix, for example, abcd and then ab. These cases will never result in a valid alphabet (because in a valid alphabet, prefixes are always first). You'll need to make sure your solution detects these cases correctly.
                if (!word1.equals(word2) && word1.startsWith(word2)) {
                    Arrays.fill(visited, 0);
                    break;
                }
                
                for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                    char ch1 = word1.charAt(j);
                    char ch2 = word2.charAt(j);
                    
                    if (ch1 != ch2) {
                        adj[ch1 - 'a'][ch2 - 'a'] = true;
                        break;
                    }
                }
            }
        }
    }
}

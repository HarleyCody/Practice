//Best Solution: find all palindrome substring, record them and add by dfs
class Solution {
    int n;
    boolean[][] dp;
    String s;
    String[][] sdp;
    
    public List<List<String>> partition(String s) {
        n = s.length();
        dp = new boolean[n][n];
        sdp = new String[n][n];
        this.s = s;
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) dp[i][i + 1] = true;
        }
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1];
                }
            }
        }
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = i; j < n; j++) {
                sb.append(s.charAt(j));
                sdp[i][j] = sb.toString();
            }
        }
        
        
        LinkedList<List<String>> ans = new LinkedList<>();
        dfs(0, ans, new LinkedList<>());
        return ans;
    }
    
    public void dfs(int start, LinkedList<List<String>> ans, LinkedList<String> ls) {
        if (start >= n) ans.offer(new ArrayList(ls));
        for (int end = start;  end < n; end++) {
            if (dp[start][end]) {
                ls.offer(sdp[start][end]);
                dfs(end + 1, ans, ls);
                ls.removeLast();
            }
        }
    }
}

//My Solution: DFS to get substring and continue to test if current is palindrome
class Solution {
    HashSet<String> palindromes = new HashSet();
    LinkedList<List<String>> ans = new LinkedList();
    public List<List<String>> partition(String s) {
        partition(s, 0, new LinkedList<String>());
        
        return ans;
    }
    
    private void partition(String str, int start, LinkedList<String> cur){
        if(start == str.length()){
            ans.offer(new LinkedList(cur));
            return;
        }
        for(int s = start; s < str.length(); ++s){
            String curString = str.substring(start, s + 1);
            if(!isPalindrome(curString)) continue;
            
            cur.offer(curString);
            partition(str, s + 1, cur);
            cur.removeLast();
        }
    }
    
    private boolean isPalindrome(String s){
        if(palindromes.contains(s)) return true;
        int left = 0;
        int right = s.length() - 1;
        while(left < right && s.charAt(left) == s.charAt(right)){
            ++left;
            --right;
        }
        if(left < right) return false;
        return palindromes.add(s);
    }
}

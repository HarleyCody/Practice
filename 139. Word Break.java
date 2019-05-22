class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        return dfs(s, wordDict, 0, new int[s.length()]);
    }

    private boolean dfs(String s, List<String> dict, int begin, int[] memo) {
        
        if (begin >= s.length() || memo[begin] == -1) return false;// cut redundant calculation
        for (int i = 0; i < dict.size(); ++i) {
            int end = isSubStr(s, begin, dict.get(i));
            if (end != -1) {
                if (end == s.length() - 1 || dfs(s, dict, end + 1, memo)) return true; // end + 1 renews begin postion 
            }
        }
        memo[begin] = -1;// after tested all words, start at this begin position cannot succeed;
        return false;
    }
    
    //check is p is substring starting at specific postion of s and return the end postion of p in s
    private int isSubStr(String s, int begin, String p) {
        if (begin + p.length() > s.length()) return -1;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) != s.charAt(begin + i)) return -1;
        }
        return begin + p.length() - 1;
    }
}

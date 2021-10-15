// My Solution: DFS while recording maxLength
public class Solution {
    Set<String> ansSet;
    int maxLength = 0;
    public List<String> removeInvalidParentheses(String s) {
        char[] chs = s.toCharArray();

        ansSet = new HashSet<String>();
        dfs(chs, 0, 0, new StringBuilder());
        List<String> ans = new ArrayList<String>();
        for(String str : ansSet){
            ans.add(str);
        }
        return ans;
    }

    private void dfs(char[] chs, int idx, int balance, StringBuilder sb){
        if(balance < 0) return;
        if(idx == chs.length){
            if(balance != 0) return;
            String path = sb.toString();
            if(maxLength < path.length()){
                ansSet.clear();
                maxLength = path.length();
            }
            if(path.length() == maxLength) ansSet.add(path);
            return;
        }
        StringBuilder path = new StringBuilder(sb);
        dfs(chs, idx + 1, balance, path);

        if(chs[idx] == '(') ++balance;
        else if(chs[idx] == ')') --balance;
        path.append(chs[idx]);
        dfs(chs, idx + 1, balance, path);
    }
}

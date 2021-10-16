/* 
naive way for only one valid pair, delete extra ) from left to right and delete extra ( from right to left, ans = chs[start to end]
*/

//Best Solution: Delete from left to right, delete from right to left, do recursion when the balance is negative
// Only remove from lastRemove to avoid duplicate.
// Do recursion only when the char is not the right target
public class Solution {
    List<String> ans;
    char[] par = {'(', ')'};
    public List<String> removeInvalidParentheses(String s) {
        ans = new ArrayList<String>();
        dfs(s, 0, 0, '(', ')');

        return ans;
    }
    private void dfs(String s, int start, int lastRemove, char pl, char pr) {
        int balance = 0;
        for(int i = start; i < s.length(); ++i){
            if(s.charAt(i) == pl) ++balance;
            if(s.charAt(i) == pr) --balance;
            if(balance >= 0) continue;
            for(int j = lastRemove; j <= i; ++j){
                if(s.charAt(j) == pr && (j == lastRemove || s.charAt(j - 1) != pr)) 
                    dfs(s.substring(0, j) + s.substring(j + 1), i, j, pl, pr);
            }
            return;
        }
        String rev = new StringBuilder(s).reverse().toString();
        if(pl == '('){
            dfs(rev, 0, 0, pr, pl);
        }else{
            ans.add(rev);
        }
    }
}

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

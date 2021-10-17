//My Solution: DFS: record previous value and previous sum:
// next sum: '+' sum + val; '-' sum - val; '*' sum + (val - 1) * preNum
// preNum:       val            -val           val * preNum
public class Solution {
    private static int T;
    private static char[] chs;
    private List<String> ans; 
    public List<String> addOperators(String num, int target) {
        T = target;
        chs = num.toCharArray();
        ans = new ArrayList<String>();
        StringBuilder path = new StringBuilder();
        int val = 0;
        for(int i = 0; i < chs.length; ++i){
            val = val * 10 + chs[i] - '0';
            path.append(chs[i]);
            dfs(i + 1, val, val, path);
            if(chs[0] == '0') break;
        }

        return ans;
    }

    private void dfs(int idx, int preNum, int sum, StringBuilder path){
        if(idx == chs.length){
            if(sum == T){
                ans.add(path.toString());
            }
            return;
        }
        int val = 0;
        StringBuilder nPath;
        for(int i = idx; i < chs.length; ++i){
            val = val * 10 + chs[i] - '0';
            nPath = new StringBuilder(path);
            nPath.append('+');
            nPath.append(val);
            dfs(i + 1, val, sum + val, nPath);

            nPath = new StringBuilder(path);
            nPath.append('-');
            nPath.append(val);
            dfs(i + 1, -val, sum - val, nPath);

            nPath = new StringBuilder(path);
            nPath.append("*");
            nPath.append(val);
            dfs(i + 1, val * preNum, sum + (val - 1) * preNum, nPath);

            if(chs[idx] == '0') return;
        }
    }
}

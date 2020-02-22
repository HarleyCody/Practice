_____________________________________________________________________Back Trace__________________________________________________________
// Author: Huahua
// Running time: 16 ms (<99.17%)
class Solution {
  private List<String> ans;
  // digits
  private char[] num;
  // expressions
  private char[] exp;
  private int target;
  
  public List<String> addOperators(String num, int target) {
    this.num = num.toCharArray();
    this.ans = new ArrayList<>();
    this.target = target;
    this.exp = new char[num.length() * 2];
    dfs(0, 0, 0, 0);
    return ans;
  }
  
    // pos is the pointer to the index of the given String num.
    // len is the pointer to the index of the expression array.
  private void dfs(int pos, int len, long prev, long curr) {
    if (pos == num.length) {      
      if (curr == target)
        ans.add(new String(exp, 0, len));
      return;
    }
    
    int s = pos;
    int l = len; // Points to the location to add operator
    if (s != 0) ++len; // Increment one to skip operator location.
    
    long n = 0;
    while (pos < num.length) {
      if (num[s] == '0' && pos - s > 0) break; // 0X...
      n = n * 10 + (int)(num[pos] - '0');      
      if (n > Integer.MAX_VALUE) break; // too long
      exp[len++] = num[pos++]; // copy number to expression
      if (s == 0) {
        dfs(pos, len, n, n);
        continue;
      }
      exp[l] = '+'; dfs(pos, len, n, curr + n);
      exp[l] = '-'; dfs(pos, len, -n, curr - n);
      exp[l] = '*'; dfs(pos, len, prev * n, curr - prev + prev * n);
    }
  }
}
________________________________________________________________Naive Backtrace_________________________________________________________
public class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> rst = new ArrayList<String>();
        if(num == null || num.length() == 0) return rst;
        helper(rst, "", num, target, 0, 0, 0);
        return rst;
    }
    public void helper(List<String> rst, String path, String num, int target, int pos, long eval, long multed){
        if(pos == num.length()){
            if(target == eval)
                rst.add(path);
            return;
        }
        for(int i = pos; i < num.length(); i++){
            if(i != pos && num.charAt(pos) == '0') break;
            long cur = Long.parseLong(num.substring(pos, i + 1));
            if(pos == 0){
                helper(rst, path + cur, num, target, i + 1, cur, cur);
            }
            else{
                helper(rst, path + "+" + cur, num, target, i + 1, eval + cur , cur);
                
                helper(rst, path + "-" + cur, num, target, i + 1, eval -cur, -cur);
                
                helper(rst, path + "*" + cur, num, target, i + 1, eval - multed + multed * cur, multed * cur );
            }
        }
    }
}

_____________________________________________________________________________________Shorter but slower Solution___________________________________________________________________________
public class Solution {
    // only recursion might be slower than my solution as it does not mem
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '-' || c == '+' || c == '*') {
                String a = input.substring(0, i);
                String b = input.substring(i + 1);
                List<Integer> al = diffWaysToCompute(a);
                List<Integer> bl = diffWaysToCompute(b);
                for (int x : al) {
                    for (int y : bl) {
                        if (c == '-') {
                            res.add(x - y);
                        } else if (c == '+') {
                            res.add(x + y);
                        } else if (c == '*') {
                            res.add(x * y);
                        }
                    }
                }
            }
        }
        if (res.size() == 0) res.add(Integer.valueOf(input));
        return res;
    }
}
______________________________________________________________________________________My Solution___________________________________________________________________________
class Solution {
    // recursion + mem
    //treat as tree
    //left rlt opr right rlt
    //return rlt for cur range
    char[] in;
    int len;
    List<Integer>[] mem;
    public List<Integer> diffWaysToCompute(String input) {
        in = input.toCharArray();
        len = in.length;
        mem = new ArrayList[len * len + 1];
        
        return compute(0, len);
    }
    
    private List<Integer> compute(int s, int e){
        if(s == e){
            return new ArrayList();
        }
        
        int hash = s * len + e;
        if(mem[hash] != null){
            return mem[hash];
        }
        
        int n = s;
        int num = 0;
        while(n < e && '0' <= in[n] && '9' >= in[n]){
            num = num * 10 + in[n++] - '0';
        }
        
        List<Integer> ans = new ArrayList();
        if(n == e){
            ans.add(num);
            return ans;
        }

        for(int i = s + 1; i < e; ++i){
            if(in[i] == '+' || in[i] == '-' || in[i] == '*'){
                List<Integer> left = compute(s, i);
                List<Integer> right = compute(i + 1, e);
                ans.addAll(combine(left, right, in[i]));
            }
        }

        mem[hash] = ans;
        return ans;
    }
    
    private List<Integer> combine(List<Integer> left, List<Integer> right, char opr){
        List<Integer> ans = new ArrayList();
        for(int l : left){
            for(int r : right){
                if(opr == '*'){
                    ans.add(l * r);
                }else if(opr == '+'){
                    ans.add(l + r);
                }else{
                    ans.add(l - r);
                }
            }
        }
        return ans;
    }
}

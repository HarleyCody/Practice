class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList();
        backtrack(ans, "", 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, String cur, int open, int close, int max){
        if (cur.length() == max * 2) {
            ans.add(cur);
            return;
        }

        if (open < max)
            backtrack(ans, cur+"(", open+1, close, max);//cur+"("， cur并未受到影响,在下一层的时候还是cur;所以是回溯,左括号小于最大数量，添加左，open+1
        if (close < open)
            backtrack(ans, cur+")", open, close+1, max);//回溯用这一层的cur,不是cur+")"，右括号少于左括号，添加右，close+1
    }
}

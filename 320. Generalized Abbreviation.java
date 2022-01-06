//Best Solution: dfs recorder skipped length, keep skip or stop skip, stop skip when have ever skipped
class Solution {
    public List<String> generateAbbreviations(String word) {
        List<String> res = new ArrayList<>();
        backtrack(res, new StringBuilder(), word.toCharArray(), 0, 0);
        return res;
    }
    private void backtrack(List<String> res, StringBuilder sb, char[] word, int index, int num) {
        if (index == word.length) {
            if (num != 0) sb.append(num);
            res.add(sb.toString());
            return;
        }
        int len = sb.length();
        backtrack(res, sb, word, index + 1, num + 1);
        sb.setLength(len);
        
        if (num != 0) sb.append(num);
        backtrack(res, sb.append(word[index]), word, index + 1, 0);
        sb.setLength(len);
    }
}
//My Solution: DFS: add number and word alternativly
class Solution {
    List<String> ans = new ArrayList();
    public List<String> generateAbbreviations(String word) {
        construct(word, 0, new StringBuilder(), true);
        construct(word, 0, new StringBuilder(), false);
        return ans;
    }
    
    private void construct(String word, int start, StringBuilder sb, boolean isNumber) {
        if(start == word.length()){
            ans.add(sb.toString());
            return;
        }
        
        StringBuilder next;
        if(isNumber){
            for(int i = start; i < word.length(); ++i){
                next = new StringBuilder(sb);
                next.append(i - start + 1);
                construct(word, i + 1, next, false);
            }
        }else{
            for(int i = start; i < word.length(); ++i){
                next = new StringBuilder(sb);
                next.append(word.substring(start, i + 1));
                construct(word, i + 1, next, true);
            }
        }
    }
}

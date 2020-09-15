_____________________________________________________________________________Best Solution_____________________________________________________________________________
public class Solution {
    // from left to right delete extra )
    // from right to left delete extra (
    // add answer;
    // two pass;
    public List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        remove(s, ans, 0, 0, new char[]{'(', ')'});
    return ans;
}

    public void remove(String s, List<String> ans, int last_i, int last_j,  char[] par) {
        for (int stack = 0, i = last_i; i < s.length(); ++i) {
            if (s.charAt(i) == par[0]) stack++;
            if (s.charAt(i) == par[1]) stack--;
            if (stack >= 0) continue;
            for (int j = last_j; j <= i; ++j)// ) is more than (
                if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1]))//delete first ) or next ) after consecutive ) 
                    remove(s.substring(0, j) + s.substring(j + 1, s.length()), ans, i, j, par);// recursive two divide paths.
            return;
        }// complete deleting redudant ) (from left to right)
        String reversed = new StringBuilder(s).reverse().toString();
        if (par[0] == '(') // finished left to right     reuse function to delete ( in case of (()
            remove(reversed, ans, 0, 0, new char[]{')', '('});//complete deleting redundant ( (from right to left)
        else // finished right to left
            ans.add(reversed); // reversed has been reversed twice, due to left to right and right to left recursive. So the answer is in right order.
    }
}
_____________________________________________________________________________My Solution_____________________________________________________________________________
// try every combination and filter results
class Solution {
    HashSet<String> total = new HashSet();
    int maxLen = 0;
    public List<String> removeInvalidParentheses(String s) {
        build(s.toCharArray(), 0, 0, new StringBuilder());
        
        List<String> ans = new ArrayList();
        for(String str : total){
            if(str.length() == maxLen){
                ans.add(str);
            }
        }
        
        return ans;
    }
    
    private void build(char[] chs, int i, int cnt, StringBuilder sb){
        if(cnt < 0){
            return;
        }
        while(i < chs.length && chs[i] != ')' && chs[i] != '('){
            sb.append(chs[i++]);
        }
        if(i == chs.length){
            if(cnt == 0){
                String ansStr = sb.toString();
                maxLen = Math.max(ansStr.length(), maxLen);
                total.add(sb.toString());
            }
            return;
        }
        StringBuilder next = new StringBuilder(sb);
        build(chs, i + 1, cnt, next);
        if(chs[i] == ')'){
            next = new StringBuilder(sb);
            next.append(chs[i]);
            build(chs, i + 1, cnt - 1, next);
        }else{
            next = new StringBuilder(sb);
            next.append(chs[i]);
            build(chs, i + 1, cnt + 1, next);
        }
    }
}

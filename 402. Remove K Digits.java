__________________________________________________________________My Solution______________________________________________________________
class Solution {
    public String removeKdigits(String num, int k) {
        if(num.length() == k) return "0";
        int n = num.length();
        char[] chs = num.toCharArray();
        char[] ans = new char[n - k];
        int c = 0;
        ans[c++] = chs[0];
        for(int i = 1; i < n; ++i){
            // number of the rest usable digits, not include chs[i]
            int rest = n - i - 1;
            // if chs[i] < ans[c - 1] push it forward, 
            // meanwhile assure rest digits can fulfill request number of digits, which is ans.length - c; as it will be put in c - 1 so it has to be larger than ans.length - c - 1;
            while(0 < c && ans[c - 1] > chs[i] && rest >= ans.length - c ){
                --c;
            }
            if(c < ans.length){
                ans[c++] = chs[i];
            }
        }
        // trim head 0;
        boolean isHead = true;
        StringBuilder ansStr = new StringBuilder();
        for(int i = 0; i < ans.length; ++i){
            if(ans[i] != '0' || !isHead){
                ansStr.append(ans[i]);
                isHead = false;
            }
        }
        return ansStr.length() == 0? "0" : ansStr.toString();
    }
}

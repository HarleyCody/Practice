class Solution {
    // two pass
    // first pass fill ) for (;
    // second pass fill ( for );
    public int minAddToMakeValid(String S) {
        char[] chs = S.toCharArray();
        int len = chs.length;
        int ans = 0;
        int open = 0;
        
        // 
        for(int i = 0; i < len; ++i){
            if(chs[i] == '('){
                open++;
            }else{
                --open;
                // cur = ')'
                if(i + 1 == len || chs[i + 1] == '('){
                    if(open < 0){
                        ans += -open;
                        open = 0;
                    }
                }
            }
        }
        open = 0;
        for(int i = len - 1; 0 <= i; --i){
            if(chs[i] == ')'){
                open++;
            }else{
                // cur = '('
                --open;
                if(i == 0 || chs[i - 1] ==')'){
                    if(open < 0){
                        ans += -open;
                        open = 0;
                    }
                }
            }
        }
        return ans;
    }
}

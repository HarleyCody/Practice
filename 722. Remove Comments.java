class Solution {
    // clean line by line, also add line by line
    // curline record content of current line
    // and line will integrate curLine with previous line
    // add line to ans; only when there is no /* before and line is not empty
    private boolean start = false;
    private List<String> ans = new ArrayList();
    private StringBuilder line = new StringBuilder();
    
    public List<String> removeComments(String[] source) {
        
        for(String src : source){
            clean(src);
        }
        
        return ans;
    }
    
    private void clean(String src){
        int cur = 0, len = src.length();
        char[] chs = src.toCharArray();
        StringBuilder curLine = new StringBuilder();
        
        while(cur < len){
            if(!start){
                if(cur < len - 1 && chs[cur] == '/'){
                    if(chs[cur + 1] == '/'){
                        break;
                    }else if (chs[cur + 1] == '*'){
                        start = true;
                        ++cur;
                    }else{
                        curLine.append(chs[cur]);
                    }
                }else{
                    curLine.append(chs[cur]);
                }
            }else{
                while(cur < len - 1 && (chs[cur] != '*' || chs[cur + 1] != '/')){
                    ++cur;
                }
                if(cur < len - 1){
                    start = false;
                    ++cur;
                }
            }
            ++cur;
        }
        line.append(curLine);
        if(!start && line.length() != 0){
            ans.add(line.toString());
            line = new StringBuilder();
        }
    }
}

//My Solution: if no more question, then answer is found
//             if True then just try to find left part;
//             else find the position of next expresion by record pair of '?' and ':', only when number of ? match number of : then right part is found
class Solution {
    public String parseTernary(String expression) {
        char[] chs = expression.toCharArray();
        int len = chs.length;
        int i = 0;
        while(i < len){
            if(i + 1 == len || chs[i + 1] != '?'){
                return expression.substring(i, i + 1);
            }
            
            i += 2;
            if(chs[i - 2] == 'F'){
                int numQuestion = 1;
                while(numQuestion != 0){
                    if(chs[i] == ':'){
                        --numQuestion;
                    }else if(chs[i] == '?'){
                        ++numQuestion;
                    }
                    ++i;
                }
            }
        }
        return "";
    }
}

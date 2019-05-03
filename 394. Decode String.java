_________________________________________________________My Solution_______________________________________________________________

class Solution {
    public String decodeString(String s) {
        Stack<StringBuilder> sta = new Stack<>();
        StringBuilder sb = new StringBuilder(s);
        StringBuilder ans = new StringBuilder();
        StringBuilder times = new StringBuilder();
        for(int i = 0; i < sb.length(); ++i){
            if('0' <= sb.charAt(i) && sb.charAt(i) <= '9'){
                times.append(sb.charAt(i));
            }
            else if(sb.charAt(i) == '['){
                
                // store prefix of string
                sta.push(ans);
                
                // stored ans should not be changed, get new space for new ans othewise ans in the stack will change with change of ans in outer.
                ans = new StringBuilder();
                
                // store times of current level of string
                sta.push(times);
                // set new timer for next string
                times = new StringBuilder();
            }
            else if(sb.charAt(i) == ']'){
                StringBuilder pre = new StringBuilder();
                
                // ans will be repeated and repeated string should not be changed during concate.
                StringBuilder concat = new StringBuilder(ans);
                
                //pop times of current string
                int repeat = Integer.parseInt(sta.pop().toString());
                
                // ans has got one of repeated string so start from 1
                for(int r = 1; r < repeat; ++r){
                    ans.append(concat);
                }
                // concate prefix if ans has prefix in this pair of []
                if(sta.size() != 0){
                    pre = sta.pop();
                    ans = pre.append(ans);
                }
            }
            else{// normal chars || concate tail of string
                ans.append(sb.charAt(i));
            }
        }
        return ans.toString();
    }
}
_____________________________________________________________Better Solution_______________________________________________________________
class Solution {
    int i= 0;
    public String decodeString(String s) {
        if(s == null || s.length() == 0) return "";
        
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        while(i < s.length()){
            char c = s.charAt(i++);
            if('0'<=c && c<= '9'){
                counter = 10*counter + (c-'0');
            }else if(c == '['){
                String sub = decodeString(s); // global variable i changed in the recursive function. So it will be the next char after '['
                for(int i=0;i<counter;i++){
                    sb.append(sub);
                }
                counter = 0;
            }else if(c == ']'){
                return sb.toString();
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }
}

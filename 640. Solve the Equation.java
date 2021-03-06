__________________________________________________________________Best Solution___________________________________________________________
class Solution {
    public String solveEquation(String equation) {
        int xPreFactor = 0;
        int tmp = 0;
        int constant = 0;
        boolean left = true;
        int sign = 1;
        for (int i = 0; i < equation.length(); i++){
            char ch = equation.charAt(i);
            //calculate num
            if (Character.isDigit(ch)){
                tmp = 10 * tmp + (ch - '0');
            }else if (ch == 'x'){
                //collect coefficient
                if (tmp == 0){
                    if (i > 0 && equation.charAt(i - 1) == '0') tmp = 0;
                    else xPreFactor = left? xPreFactor + sign * 1: xPreFactor - sign * 1;
                }else{
                    xPreFactor = left? xPreFactor + sign * tmp: xPreFactor - sign * tmp;
                    tmp = 0;
                }
            }else if (ch == '+' || ch == '-'){
                constant = left? constant + sign * tmp: constant - sign * tmp;
                tmp = 0;
                sign = ch == '+'? 1: -1;
            }else{
                left = false;
                constant += sign * tmp;
                sign = 1;
                tmp = 0;
            }
        }
        constant -= sign * tmp;
        if (xPreFactor == 0 && constant == 0) return "Infinite solutions";
        else if (xPreFactor == 0) return "No solution";
        int ret = (-1) * constant / xPreFactor;
        return "x=" + String.valueOf(ret);
    }
}
____________________________________________________________________My Solution___________________________________________________________
class Solution {
    // read from left to right;
    
    // result: x = -val/coefficient;
    // left: num will be as it, right: num will be -num;
    // no solution(no x == coefficient == 0);
    // infinite(no val but coefficient > 0);
    public String solveEquation(String equation) {
        int len = equation.length();
        if(len == 0) return "No solution";
        
        int coefficient = 0, val = 0, signal = 1;
        boolean right = false;
        
        int i = 0;
        
        while(i < len){
            if(equation.charAt(i) == '+'){
                signal = right ? -1 : 1;
                ++i;
            }else if(equation.charAt(i) == '-'){
                signal = right ? 1 : -1;
                ++i;
            }else if(equation.charAt(i) == '='){
                right = true;
                signal = -1;
                ++i;
            }else{
                // X or number;
               int start = i, n = 0;
                while(i < len && '0' <= equation.charAt(i) && equation.charAt(i) <= '9'){
                    n = n * 10 + equation.charAt(i) - '0';
                    ++i;
                }
                int num = signal * (start == i ? 1 : n);
                // coefficient
                if(i < len && equation.charAt(i) == 'x'){
                    coefficient += num;
                    ++i;
                }else{
                    val += num;
                }
            }
        }
        if (val == 0){
            if(coefficient == 0)return "Infinite solutions";
            else return "x=0";
        }else{
            if(coefficient == 0)return "No solution";
            else return "x=" + -val/coefficient;
        }
    }
}

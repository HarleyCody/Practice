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

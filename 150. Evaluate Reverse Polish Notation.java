______________________________________________________Best Solution____________________________________________________________
class Solution {
    public int evalRPN(String[] tokens) {
        // changed value can be stored by use value in array
        int[] index = {tokens.length-1};
        return eval(tokens, index);
    }
    // recursion by operators
    private int eval(String[] tokens, int[] index){
        // recursion by operators, numbers of operator determines how many times caculation is proceeding
        if( tokens[index[0]].equals("+")){
            index[0]--;
            int v1 = eval(tokens, index);
            int v2 = eval(tokens, index);
            return v2 + v1;
        } else if ( tokens[index[0]].equals("-")){
            index[0]--;
            // find first valid number, retrun by caculation or number;
            int v1 = eval(tokens, index);
            // find second valid number, index changed here by recursion in v1
            int v2 = eval(tokens, index);
            return v2 - v1;
        }else if ( tokens[index[0]].equals("*")){
            index[0]--;
            int v1 = eval(tokens, index);
            int v2 = eval(tokens, index);
            return v2 * v1;
        }else if ( tokens[index[0]].equals("/")){
            index[0]--;
            int v1 = eval(tokens, index);
            int v2 = eval(tokens, index);
            return v2 / v1;
        } else {
            int v = Integer.parseInt(tokens[index[0]]);
            index[0]--;
            return v;
        }
    }
}
______________________________________________________My Solution______________________________________________________________
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> num = new Stack();
        int ans = 0;
        for(String s: tokens){// numbers here
            if(s.charAt(s.length() - 1) >= '0'){
                num.push(Integer.parseInt(s));
            }
            else{// operators here
                //once meet pop two numbers from stack to operate accordingly, push rlt to stack
                int num2 = num.pop();
                int num1 = num.pop();
                switch(s){
                    case "+":
                        num1 += num2;
                        num.push(num1);
                        break;
                    case "-":
                        num1 -= num2;
                        num.push(num1);
                        break;
                    case "*":
                        num1 *= num2;
                        num.push(num1);
                        break;
                    case "/":
                        num1 /= num2;
                        num.push(num1);
                        break;
                    default:
                        break;
                }
            }
        }
        return num.pop();
    }
}

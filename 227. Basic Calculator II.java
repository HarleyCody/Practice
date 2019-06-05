_____________________________________________________________Better Solution___________________________________________________________
class Solution {
    public int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] a = new char[s.length()];
        int n = 0;
        for (char c : s.toCharArray()) {
            if (c != ' ') a[n++] = c;
        }
        int i = 0, num = 0;
        while (i < n && '0' <= a[i] && a[i] <= '9') {
            num = num * 10 + a[i++] - '0';
        }
        int[] stack = new int[n];
        stack[0] = num;
        int stackLen = 1;
        while (i < n) {
            int c = a[i++];
            num = 0;
            while (i < n && '0' <= a[i] && a[i] <= '9') {
                num = num * 10 + a[i++] - '0';
            }
            switch (c) {
                case '+':
                    stack[stackLen++] = num;
                    break;
                case '-':
                    stack[stackLen++] = -num;
                    break;
                case '*':
                    stack[stackLen - 1] = stack[stackLen - 1] * num;
                    break;
                case '/':
                    stack[stackLen - 1] = stack[stackLen - 1] / num;
                    break;
            }
        }
        int sum = 0;
        for (int x : stack) sum += x;
        return sum;
    }
}

_____________________________________________________________My Solution_________________________________________________________________
class Solution {
    public int calculate(String s) {
        Deque<Integer> numbers = new ArrayDeque();
        Deque<Character> operators = new ArrayDeque();
        int digit = 0, ans = 0;
        char oper = ' ';
        boolean signMinus = false;
        for(int i = 0; i < s.length(); ++i){
            if(s.charAt(i) == ' ') continue;
            
            while(i < s.length() && '0' <= s.charAt(i) && s.charAt(i) <= '9'){
                digit = digit * 10 + s.charAt(i++) - '0';
            }
            
            while(i < s.length() && ' ' == s.charAt(i)) ++i;
            if(signMinus){
                numbers.add(-digit);
                operators.add('+');
            }
            else
                numbers.add(digit);
            digit = 0;
            
            if(i < s.length() && (s.charAt(i) == '*' || s.charAt(i)== '-' 
                                  || s.charAt(i) == '+' || s.charAt(i) == '/')){
                if(s.charAt(i) == '-') signMinus = true;
                else{
                    operators.add(s.charAt(i));
                    signMinus = false;
                }
            }
        }
        operators.add(' ');
        while(!numbers.isEmpty()){
            ans = numbers.pollFirst();
            oper = operators.pollFirst();
            if((oper == '+' || oper == '-') && (operators.peekFirst() == '*' || operators.peekFirst() == '/')){
                numbers.add(ans);
                operators.add(oper);
            }
            else if(oper == ' '){
                if(operators.isEmpty())return ans;
                numbers.add(ans);
                operators.add(oper);
            }
            else{
                if(oper == '-') ans -= numbers.pop();
                else if(oper == '*') ans *= numbers.pop();
                else if(oper == '+') ans += numbers.pop();
                else if(oper == '/') ans /= numbers.pop();
                numbers.addFirst(ans);
            }
        }
        return ans;
    }
}

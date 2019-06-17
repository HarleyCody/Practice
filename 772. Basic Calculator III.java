class Solution {
    public int calculate(String s) {
        return helper(s, 0)[0];// first element in int[]helper which is result + pre.
    }
    
    int[] helper(String s, int start) {
        int index = start;
        char sign = '+';
        int result = 0;
        int pre = 0;
        while(index < s.length()) {
            char c = s.charAt(index);
            if(Character.isDigit(c)) {// digits
                int num = 0;
                while(index<s.length() && Character.isDigit(s.charAt(index))) {
                    num = num * 10 + (s.charAt(index)-'0');
                    ++index;
                }
                // get result and previous number
                int[] temp = setPreAndResult(pre, result, num, sign, index);
                pre = temp[0];
                result = temp[1];
            } else if(c == ' ') {
                index++;
            } else if(c == '(') {
                int[] temp = helper(s, ++index);
                int[] sum = setPreAndResult(pre, result, temp[0], sign, index);
                pre = sum[0];
                result = sum[1];
                index = temp[1];// update index after recursive;
            } else if(c == ')') {
                index++;
                break;// go back to last layer
            } else {
                index++;
                sign = c;
            }
        }
        // index is value transfer, so will not change during recursive, transfer index as out to update index after parenthese
        //final result can be added by +
        return new int[]{result+pre, index};
    }
    
    int[] setPreAndResult(int pre, int result, int num, int sign,int index) {
        if(sign == '+') {
            result += pre;
            pre = num;
        } else if(sign == '-') { // + a negative number
            result += pre;
            pre = -num;
        } else if(sign == '/') { // / and * has priority, so caculate pre with / and *, final result can be added by +
            pre /= num;
        } else if(sign == '*') {
            pre *= num;
        }
        System.out.print(" at" + index + " result=" + result + " pre=" + pre);
        return new int[]{pre, result};
    }
}

_________________________________________________Best Solution(DFS)____________________________________________________________
class Solution {
    // low high record range of val
    // lowest value is 0
    // when it comes to * --low: * is ')' and ++high * is '('
    // if the final range contains 0, then it will be true
    public boolean checkValidString(String s) {
        int low = 0;
        int high = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                // range increase
                ++low;
                ++high;
            } else if (s.charAt(i) == ')') {
                // range decrease
                if (low > 0) {
                    --low;
                }
                --high;
            } else {
                // meet * maintain low and high respectively. --low; ++high
                if (low > 0) {
                    --low;
                }
                ++high;
            }
            if (high < 0) {
                return false;
            }
        }
        return low == 0;
    }
} 
___________________________________________________My Solution(DFS)____________________________________________________________
class Solution {
    public boolean checkValidString(String s) {
        if(s.length() == 0) return true;
        char[] chs = s.toCharArray();
        return check(chs, 0, 0); 
    }
    private boolean check(char[] chs, int start, int val){
        int j = start;
        while(j < chs.length){
            if(val < 0) return false;
            if(chs[j] == '(') ++val;
            else if(chs[j] == ')')--val;
            else break;
            ++j;
        }
        if(j == chs.length) return val == 0;
        return check(chs, j + 1, val + 1) || check(chs, j + 1, val - 1) || check(chs, j + 1, val);
    }
} 

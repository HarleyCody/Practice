class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] stack = new int[temperatures.length];
        // begining of stack, - 1 means empty;
        int top = -1;
        int[] ret = new int[temperatures.length];
        for(int i = 0; i < temperatures.length; i++) {
            // stack is NOT empty and current temperature is larger than last largest one, update days of last one;
            while(top > -1 && temperatures[i] > temperatures[stack[top]]) {
                int idx = stack[top--];
                ret[idx] = i - idx;
            }
            // save current one to stack for later comparation.
            stack[++top] = i;
        }
        return ret;
    }
}

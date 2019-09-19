class Solution {
    // based on number of tabs to determine level of file
    // number of tabs == indexOfLast("\t") + 1; (contiguious occers in the beginning);
    // level = number of tabs + 1, as length start at 0(stored in stack in advance); dir in level 1;
    // stack only store path to current file. pop() used to find parent of current file.
    // stack store length of path including /
    // if current file contains . it is end file, calculate length;
    public int lengthLongestPath(String input) {
        Stack<Integer> sta = new Stack();
        sta.push(0);
        int ans = 0;
        for(String str : input.split("\n")){
            int pos = str.lastIndexOf("\t");
            int numOfTabs = pos + 1;
            int level = numOfTabs + 1;
            while(level < sta.size()){
                sta.pop();
            }
            int curLen = sta.peek() + str.length() - numOfTabs + 1;
            sta.push(curLen);
            if(str.contains(".")){
                ans = Math.max(curLen - 1, ans);
            }
        }
        return ans;
    }
}

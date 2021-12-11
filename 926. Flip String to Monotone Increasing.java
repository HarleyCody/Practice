//Best Solution: Dynamic programming, curStatus depends one the previous
// Improve from best
// if cur == 1; curMinStepEndAtOne = Math.min(prevStepsEndAtZero, prevStepsEndAtOne); curMinStepEndAtZero = prevStepsEndAtZero + 1; 1 could be curChar - '0'
// if cur == 0; curMinStepEndAtOne = Math.min(prevStepsEndAtZero, prevStepsEndAtOne) + 1; curMinStepEndAtZero = prevStepsEndAtZero; 1 cound be '1' - curChar;
class Solution {
    public int minFlipsMonoIncr(String s) {
        char[] chs = s.toCharArray();
        int len = chs.length;
        int endAtOne = 0;
        int endAtZero = 0;
        for(char c : chs){
            endAtOne = Math.min(endAtOne, endAtZero);
            endAtZero += c  - '0';
            endAtOne += '1' - c;
        }
        
        return Math.min(endAtOne, endAtZero);
    }
}
//Best Solution: Dynamic programming, curStatus depends one the previous
// if cur == 1; curMinStepEndAtOne = Math.min(prevStepsEndAtZero, prevStepsEndAtOne); curMinStepEndAtZero = prevStepsEndAtZero + 1; 1 could be curChar - '0'
// if cur == 0; curMinStepEndAtOne = Math.min(prevStepsEndAtZero, prevStepsEndAtOne) + 1; curMinStepEndAtZero = prevStepsEndAtZero; 1 cound be '1' - curChar;
class Solution {
    public int minFlipsMonoIncr(String s) {
        char[] chs = s.toCharArray();
        int len = chs.length;
        int endAtOne = 0;
        int endAtZero = 0;
        for(char c : chs){
            endAtOne = Math.min(endAtOne, endAtZero);
            if(c == '1'){
                ++endAtZero;
            }else{
                ++endAtOne;
            }
        }
        
        return Math.min(endAtOne, endAtZero);
    }
}


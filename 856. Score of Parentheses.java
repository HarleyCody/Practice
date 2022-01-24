//My Solution:DFS, calculate result layer by layer, contiue count until meet ')' for next level

class Solution{
	char[] chs;
	int idx = 0;
	public int scoreOfParentheses(String s){
        chs  = s.toCharArray();
        int ans = getScore();
        return ans;
    }

    private int getScore(){
        int score = 0;
        int count = 0;
        boolean first = true;
        while(idx < chs.length && count >= 0){
            if(chs[idx] == '('){
                ++idx;
                ++count;
                if(chs[idx] == '('){
                    score += 2 * getScore();
                }else{
                    score += getScore();
                }
            }else{
                --count;
            }
            if(count == -1){
                --idx;
            }else{
                ++idx;
            }
        }
        return score == 0 ? 1 : score;
    }
}

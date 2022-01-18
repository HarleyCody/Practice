/*
My Solution:
count number of remaining letter
count number of letter in stack.
push the current letter into stack until it is bigger than previous, and remaining + current letter >= repetition && k - sta.size() < length - i && repetition - current letter <= k.sta.size();
*/
class Solution{
	public String smallestSubsequence(String s, int k, char letter, int repetition){
		char[] chs = s.toCharArray();
		int len = chs.length;
	    int numLetter = 0;
		for(char c : chs){
            if(c == letter){
	            ++numLetter;
            }	
        }
        char[] ans = new char[k];
	    int idx = -1;
	    int numLetterStack = 0;
	    for(int i = 0; i < len; ++i){
	        while(0 <= idx && chs[i] < ans[idx] && numLetterStack + numLetter - (ans[idx] == letter ? 1 : 0) >= repetition && 
                  k - idx <= len - i && repetition - numLetterStack <= len - i){
	            if(ans[idx] == letter){
	                --numLetterStack;
                }
	            --idx;
            }
            if(idx < k - 1){
                ans[++idx] = chs[i];
                if(chs[i] == letter){
                    ++numLetterStack;
                }
            }

            if(chs[i] == letter){
                --numLetter;
            }
        }
        idx = k - 1;
        while(numLetterStack < repetition){
	        if(ans[idx] != letter){
	            ans[idx] = letter;
	            ++numLetterStack;
            }
            --idx;
        }
        return new String(ans);
    }
}

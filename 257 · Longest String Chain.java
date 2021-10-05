//My Solution: from long word to short word do not need to try a - z to compare, just delete one from long word
//             dp to record the result
public class Solution {
    public int longestStrChain(String[] words) {
        List[] wordsArr = new ArrayList[17];
        int maxCol = 0;
        for(String word : words){
            int len = word.length();
            if(wordsArr[len] == null){
                wordsArr[len] = new ArrayList<String>();
            }
            wordsArr[len].add(word);
            maxCol = Math.max(maxCol, wordsArr[len].size());
        }
        int[][] dp = new int[17][maxCol];
        int ans = 0;
        for(int r = 16; r > 0; --r){
            if(wordsArr[r] == null) continue;
            for(int c = 0; c < wordsArr[r].size(); ++c){
                dp[r][c] = Math.max(dp[r][c], 1);
                ans = Math.max(ans, dp[r][c]);
                if(wordsArr[r - 1] == null)continue;

                String curWord = (String)wordsArr[r].get(c);
                for(int w = 0; w < wordsArr[r - 1].size(); ++w){
                    if(isChain((String)wordsArr[r - 1].get(w), curWord)){
                        dp[r - 1][w] = Math.max(dp[r - 1][w], dp[r][c] + 1); 
                    }
                }
            }
        }
        return ans;
    }
    private boolean isChain(String shortWord, String longWord){
        StringBuilder change;
        for(int i = 0; i < longWord.length(); ++i){
            change = new StringBuilder(longWord.substring(0, i));
            change.append(longWord.substring(i + 1));
            if(shortWord.equals(change.toString())) return true;
        }
        return false;
    }
}

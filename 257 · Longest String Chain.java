//Best Solution: sort by length to make it 1 dimensions and do not need to compare word by deleting, using two pointers.
public class Solution {
    public int longestStrChain(String[] words) {
        int [] dp = new int[words.length];
        int ans = 0;

        Arrays.sort(words,new Comparator<String>() {
            public int compare(String s1,String s2) {
                return s1.length() - s2.length();
            }
        });

        for(int i = 1; i < words.length; i++) {
            for(int j = 0; j < i; j++)
                if(pre_word(words[j],words[i])) {
                    dp[i]=Math.max(dp[i],dp[j]+1);
                    ans=Math.max(ans,dp[i]);
                }
        }
        return ans + 1;
    }
    
    private boolean pre_word(String a, String b){
        if(a.length() + 1 != b.length())
            return false;
        int i = 0, j = 0;
        while(i < a.length() && j < b.length()){
            if(a.charAt(i)==b.charAt(j)){
                i++;
            }
            j++;
        }
        if(i == a.length())
            return true;
        return false;
    }
}
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

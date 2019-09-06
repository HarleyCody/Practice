______________________________________________________________Best Solution_______________________________________________________________
class Solution {
    // previous word is same with later word with omitting one letter
    private boolean match(String pred, String word) {
        if (word.length() - pred.length() != 1) {
            return false;
        }
        int i = 0;
        int j = 0;
        boolean inserted = false;
        while (j < pred.length()) {
            if (word.charAt(i) != pred.charAt(j)) {
                if (inserted) {
                    // has omitted once
                    return false;
                }
                // omit once;
                inserted = true;
                i++;
            } else {
                // check next letter of each letter in pred and word.
                i++;
                j++;
            }
        }
        return true;
    }
    // start from word s to find possbile longest chain
    private int test(String s, int idx, List<List<String>> strings) {
        // in case of next our of boundary
        if (idx == 15) {
            return 1;
        }
        // list with longer length
        List<String> nexts = strings.get(idx + 1);
        // begin from length 1. Basic scenario.
        int ans = 1;
        for (String next: nexts) {
            // comparing current string with strings in list with longer length;
            if (match(s, next)) {
                // s can be next with adding one letter.
                // recursively get max length, accumulate from bottom to top, if no match add 1 from 1
                ans = Math.max(ans, test(next, idx + 1, strings) + 1);
            }
        }
        // if there is no match, return 1;
        return ans;
    }
    
    public int longestStrChain(String[] words) {
        // collect words with same length;
        List<List<String>> strings = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            strings.add(new ArrayList<>());
        }
        for (String word: words) {
            strings.get(word.length() - 1).add(word);
        }
        
        int ans = 0;
        for (int i = 0; i < 16; i++) {
            
            // imppsoible to get larger length with starting from next word list. 15 - i rest length 
            if (ans > 15 - i) {
                break;
            }
            List<String> curs = strings.get(i);
            for (String cur: curs) {
                // 
                ans = Math.max(ans, test(cur, i, strings));
            }
        }
        return ans;
    }
}
______________________________________________________________My Solution_________________________________________________________________
class Solution {

    // candidates
    HashSet<String> recorder = new HashSet();
    //record max Chain for every starting word in words
    HashMap<String, Integer> dp = new HashMap();
    int curMax = 0;
    public int longestStrChain(String[] words) {
        int ans = 0;
        
        // initialize candidate
        for(String str : words){
            recorder.add(str);
        }
        // start from every word
        for(String str : words){
            getLongestChain(str, 1);
            // recorder max Chain start with word str;
            dp.put(str, curMax);
            // update ans
            ans = Math.max(ans, curMax);
            curMax = 1;
        }
        return ans;
    }
    // check substring of word recursivly
    private void getLongestChain(String word, int cur){
        // try every possible substring with omittming one letter
        for(int i = 0; i < word.length(); i++){
        
            // cut words with omitting one letter
            StringBuilder newStr = new StringBuilder(word.substring(0, i));
            newStr = newStr.append(word.substring(i + 1));
            // speed up, substring exist and max chain of it has been calcualted
            if(dp.containsKey(newStr.toString())){
                // get maxLength, it can comparing multiple times as different substring has different max chain length.
                curMax = Math.max(cur + dp.get(newStr.toString()), curMax);
            }
            // maxLength of current substring has not been calculate before, recursivly calculate it.
            else if(recorder.contains(newStr.toString())){
                getLongestChain(newStr.toString(), cur + 1);
            }
        }
        // update max chain length of current starting word.
        curMax = Math.max(cur, curMax);
    }
}

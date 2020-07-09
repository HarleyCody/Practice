____________________________________________________________________Best Solution(Improved by cache)___________________________________________________________________________________
class Solution {
    // improved by get checked string directly
    public int numMatchingSubseq(String S, String[] words) {
        int len = S.length();
        int ans = 0;
        HashMap<String, Boolean> cache = new HashMap();

        for(String word : words){
            if(word.length() > len){
                continue;
            }
            if(cache.containsKey(word)){
                if(cache.get(word)){
                    ++ans;
                }
                continue;
            }
            int prev = -1;
            boolean isSub = true;
            for(char c : word.toCharArray()){
                int next = S.indexOf(c, prev);
                if(next < prev){
                    cache.put(word, false);
                    isSub = false;
                    break;
                }
                prev = next + 1;
            }
            if(isSub){
                cache.put(word, true);
                ++ans;
            }
        }
        
        return ans;
    }
}
______________________________________________________________________My Solution___________________________________________________________________________________
class Solution {
    // O(N) use array to record next position of char in S
    public int numMatchingSubseq(String S, String[] words) {
        int len = S.length();
        int ans = 0;
        int[][] recorder = new int[26][len];
        
        for(int l = len - 1; 0 <= l; --l){
            char c = S.charAt(l);
            for(int i = 0; i < 26; ++i){
                if(l < len - 1){
                    recorder[i][l] = recorder[i][l + 1];
                }
            }
            recorder[c - 'a'][l] = l;
        }
        
        for(String word : words){
            int prev = -1;
            boolean isSub = true;
            for(char c : word.toCharArray()){
                if(prev == len - 1){
                    isSub = false;
                    break;
                }
                int next = recorder[c - 'a'][prev + 1];
                if(next <= prev){
                    isSub = false;
                    break;
                }
                prev = next;
            }
            if(isSub){
                ++ans;
            }
        }
        
        return ans;
    }
}
______________________________________________________________________My Solution___________________________________________________________________________________
class Solution {
    // test word one by one, find chars one by one could be O(MN)
    public int numMatchingSubseq(String S, String[] words) {
        int len = S.length();
        int ans = 0;
        
        for(String word : words){
            int prev = -1;
            boolean isSub = true;
            for(char c : word.toCharArray()){
                int next = S.indexOf(c, prev);
                if(next == -1){
                    isSub = false;
                    break;
                }
                prev = next + 1;
            }
            if(isSub){
                ++ans;
            }
        }
        
        return ans;
    }
}

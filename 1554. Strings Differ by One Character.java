//Best Solution: use rolling hash to record for string
// try to remove one of char from hash if hash is still unique then there is no one differ string otherwise return true
class Solution {
    public boolean differByOne(String[] dict) {
        int n = dict.length;
        if (n <= 1){
            return false;
        }
        long[] nums = new long[n];
        for (int i = 0; i < n; i ++){
            long temp = 0;
            for (char ch : dict[i].toCharArray()){
                temp = temp * 26 + ch - 'a';
            }
            nums[i] = temp;
        }
        int m = dict[0].length();
        long base = 1;
        for (int j = m - 1; j >= 0; j --){
            Set<Long> set = new HashSet<>();
            for (int i = 0; i < n; i ++){

                if (!set.add(nums[i] - (long)(dict[i].charAt(j) -'a') * base )){
                    return true;
                }
            }
            base *= 26;
        }
        return false;
    }
}
//My Solution: Trie and dfs to find one differ string
class Solution{
	class TrieNode{
		TrieNode[] kids = new TrieNode[26];
    }

    TrieNode root = new TrieNode();
    public boolean differByOne(String[] dict){
        for(String word : dict){
            build(word.toCharArray());
        }
        for(String word : dict){
            if(found(word.toCharArray(), root, 0, false)){
                return true;
            }
        }

        return false;
    }
    private void build(char[] chs){
        TrieNode cur = root;
        for(char c : chs){
            if(cur.kids[c - 'a'] == null){
                cur.kids[c - 'a'] = new TrieNode();
            }
            cur = cur.kids[c - 'a'];
        }
    }
    private boolean found(char[] chs, TrieNode prev, int idx, boolean missed){
        if(idx == chs.length && missed) return true;
        if(missed && prev.kids[chs[idx] - 'a'] == null){
            return false;
        }
        
        for(int i = 0; i < 26; ++i){
            if(prev.kids[i] == null || missed && i != chs[idx] - 'a'){
                continue;
            }
            if(found(chs, prev.kids[i], idx + 1, missed | i != chs[idx] - 'a')){         
                return true;
            }
        }
        return false;
    }
}

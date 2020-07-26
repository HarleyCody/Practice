_______________________________________________________________________________Fastest Solution________________________________________________________________________________
class Solution {
    // O(Clogc) C is number of chars in dictionary list
    // map1 group words with same start letter and end letter;
    // abbreviation is determined by max same char from start between cur word and next word(longer) also previous word
    // it should be max of them. leftWord < curWord < rightWord
    public List<String> wordsAbbreviation(List<String> dict) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : dict) {
            String abbr = getAbbreviation(s, 1);
            List<String> list = map.getOrDefault(abbr, new ArrayList<>());
            if (list.isEmpty()) {
                map.put(abbr, list);
            }
            list.add(s);
        }
        Map<String, String> map2 = new HashMap<>();
        map.forEach((abbr, list) -> {
            if (list.size() == 1) {
                map2.put(list.get(0), abbr);
            } else {
                Collections.sort(list);
                int n = list.get(0).length();
                int prevIndex = 0;
                for (int i = 0; i < list.size(); i++) {
                    String cur = list.get(i);
                    if (i + 1 == list.size()) {
                        // unique word
                        map2.put(cur, getAbbreviation(cur, prevIndex + 1));
                    } else {
                        String next = list.get(i + 1);
                        int j = 0;
                        // as in group with same start and end, also words are distinct, there is no word can overflow length
                        while (cur.charAt(j) == next.charAt(j)) {
                            j++;
                        }
                        // abbreviation is determined by max same char from start between cur word and next word(longer) also previous word
                        // it should be max of them. leftWord < curWord < rightWord
                        int index = Math.max(j, prevIndex);
                        prevIndex = j;
                        map2.put(cur, getAbbreviation(cur, index + 1));
                    }
                }
            }
        });
        List<String> res = new ArrayList<>();
        for (String s : dict) {
            res.add(map2.get(s));
        }
        return res;
    }

    private String getAbbreviation(String s, int start) {
        if (start >= s.length() - 2) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s.substring(0, start));
        sb.append(s.length() - 1 - start).append(s.charAt(s.length() -1));
        return sb.toString();
    }
}
________________________________________________________________________________My Solution________________________________________________________________________________
// use Trie to find idx of first unique char in a string and do abbrievation
// !! trick, root should be different for build and search as they differs in length and last letter
// use 2d array to store different root; roots[i][j] == root for word with length i and end with char(j + 'a');
// O(C) C is number of chars in dictionary list
class Solution {
    class TrieNode{
        TrieNode[] child = new TrieNode[26];
        int count = 0;
    }
    TrieNode[][] roots;
    public List<String> wordsAbbreviation(List<String> dict) {
        roots = new TrieNode[401][26];
        for(String word : dict){
            build(word);
        }
        
        List<String> ans = new ArrayList();
        for(String word: dict){
            int len = word.length();
            if(len < 4){
                ans.add(word);
            }else{
                int uPoint = search(word);
                int numOfAbbr = len - uPoint - 2;
                if(numOfAbbr < 2){
                    ans.add(word);
                }else{
                    String rlt = buildWord(word, uPoint, numOfAbbr);
                    ans.add(rlt);
                }
            }
        }
        
        return ans;
    }
    
    private void build(String word){
        char[] chs = word.toCharArray();
        int len = chs.length;
        int rootIdx = chs[len - 1] - 'a';
        
        if(roots[len][rootIdx] == null){
            roots[len][rootIdx] = new TrieNode();
        }
        TrieNode cur = roots[len][rootIdx];
        for(int i = 0; i < len; ++i){
            int idx = chs[i] - 'a'; 
            if(cur.child[idx] == null){
                cur.child[idx] = new TrieNode();
            }
            cur = cur.child[idx];
            ++cur.count;
        }
    }
    
    private int search(String word){   
        char[] chs = word.toCharArray();
        int len = chs.length;
        int rootIdx = chs[len - 1] - 'a';
        
        TrieNode cur = roots[len][rootIdx];
        for(int i = 0; i < chs.length; ++i){
            int idx = chs[i] - 'a';
            cur = cur.child[idx];
            if(cur.count == 1){
                return i;
            }
        }
        return chs.length;
    }
    
    private String buildWord(String word, int preIdx, int num){
        StringBuilder sb = new StringBuilder();
        char[] chs = word.toCharArray();
        for(int i = 0; i <= preIdx; ++i){
            sb.append(chs[i]);
        }
        sb.append(num);
        sb.append(chs[chs.length - 1]);
        
        return sb.toString();
    }
}

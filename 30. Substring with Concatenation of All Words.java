___________________________________________________________Best Solution________________________________________________________________
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> list = new ArrayList<>();
        int n = words.length;
        if (n == 0) {
            return list;
        }

        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        
        // window: comb length
        int size = words[0].length(), window = size * n;
        //O(n)
        for (int i = 0; i < size; ++i) {
            int start = i;
            //O(length / size)
            while (start + window <= s.length()) {
                String sub = s.substring(start, start + window);
                Map<String, Integer> temp = new HashMap<>();
                int j = n;
                // O(size)
                while (j > 0) {
                    // j = num of words, size = length of words
                    // put every words
                    String word = sub.substring(size * (j - 1), size * j);
                    int count = temp.getOrDefault(word, 0) + 1;
                    // check in the middle faster
                    if (count > map.getOrDefault(word, 0)) {
                        break;
                    }
                    temp.put(word, count);
                    --j;
                }
                if (j == 0) {
                    list.add(start);
                }
                // j > 0; skip word[j] check after;
                // start will be reset by i so it O(len/size); 
                start += size * Math.max(j, 1);
            }
        }
        return list;
    }
}

________________________________________________________Basic Solution___________________________________________________________________
class Solution {
    // comapre two hashmap, one is words the other is words in substring(ie, comb)
    public List<Integer> findSubstring(String s, String[] words) {
        if (s == null || words == null || s.length() == 0 || words.length == 0) {
            return new ArrayList<>();
        }
        Map<String, Integer> counts = new HashMap<>();
        for (String word : words) {
            counts.put(word, counts.getOrDefault(word, 0) + 1);
        }
        
        List<Integer> r = new ArrayList<>();
        int sLen = s.length();
        int num = words.length;
        int wordLen = words[0].length();

        for (int i = 0; i <= sLen - num * wordLen; i++) {
            // sub is string with combLen
            String sub = s.substring(i, i + num * wordLen);
            if (isConcat(sub, counts, wordLen)) {
                r.add(i);
            }
        }
        return r;
    }
    
    private boolean isConcat(String sub, Map<String, Integer> counts, int wordLen) {
        Map<String, Integer> seen = new HashMap<>();
        for (int i = 0; i < sub.length(); i += wordLen) {
            String sWord = sub.substring(i, i + wordLen);
            seen.put(sWord, seen.getOrDefault(sWord, 0) + 1);
        }
        return seen.equals(counts);
    }
}
_________________________________________________________My Solution(Find by Rabin_Karp(TLE))____________________________________________
class Solution {
    HashSet<Long> combs = new HashSet();
    boolean[] used;
    int wordsLen, combLen;
    List<Integer> ans = new ArrayList();
    public List<Integer> findSubstring(String s, String[] words) {
        if(words.length == 0 ||s.length() < words.length * words[0].length()) return new ArrayList();
        
        wordsLen = words.length;
        combLen = wordsLen * words[0].length();
        used = new boolean[words.length];
        
        getCombs(words, new StringBuilder());
        rabinKarpSearch(s);
        return ans;
    }
    private void getCombs(String []words, StringBuilder cur){
        if(cur.length() == combLen){
            long hval = 0;
            for(int i = 0; i < combLen; ++i){
                hval = (hval * 26 + cur.charAt(i)) % 1000000001;
            }
            combs.add(hval);
            return;
        }
        for(int i = 0; i < words.length; ++i){
            if(used[i]) continue;
            used[i] = true;
            StringBuilder tem = new StringBuilder(cur);
            getCombs(words, tem.append(words[i]));            
            used[i] = false;
        }
    }
    private void rabinKarpSearch(String s){
        long curHash = 0;
        long headA = 1;
        int i = 0;
        for(; i < combLen; ++i){
            curHash = (curHash * 26 + s.charAt(i)) % 1000000001;
            headA = (headA * 26) % 1000000001;
        }
        if(combs.contains(curHash)){
            ans.add(0);
        }
        for(; i < s.length(); ++i){
            curHash = (curHash * 26 - s.charAt(i - combLen) * headA % 1000000001 + 1000000001) % 1000000001;
            curHash = (curHash + s.charAt(i)) % 1000000001;
            if(combs.contains(curHash)){
                ans.add(i - combLen + 1);
            }
        }
    }
}

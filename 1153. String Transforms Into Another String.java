__________________________________________________________________________Best Solution(TLE)________________________________________________________________________
https://leetcode.com/problems/string-transforms-into-another-string/discuss/399352/Complete-Logical-Thinking-(This-is-why-only-check-if-str2-has-unused-character)
class Solution {
    // Core: Store mapping relation between chsS1[i] and chsS2[i]
    // if one char in s1 maps two different chars in s2, can not transform;
    // so relation must be one to one or one to null(as chsS1[i] may transfer to chsS1[j] and then transfer to chsS3[i])
    // ie keySize >= valueSize
    // as we need chsS1[j] to transfer, so in the values there must be at least one letter beused for transfer
    // otherwise val == 26 key = 26 and they are one to one, means there must be a cycle and cannot break(no letter can be used as mediator)
    // in this situation false;
    // in the situation that one char in s1 map two diff char in s2 return false;
    public boolean canConvert(String s1, String s2) {
        if (s1.equals(s2)) return true;
        Map<Character, Character> dp = new HashMap<>();
        for (int i = 0; i < s1.length(); ++i) {
            if (dp.getOrDefault(s1.charAt(i), s2.charAt(i)) != s2.charAt(i))
                return false;
            dp.put(s1.charAt(i), s2.charAt(i));
        }
        return new HashSet<Character>(dp.values()).size() < 26;
    }
}____________________________________________________________________________My Solution(TLE)________________________________________________________________________
class Solution {
    //dfs to find answer
    public boolean canConvert(String str1, String str2) {
        if(str1.equals(str2)){
            return true;
        }
        
        HashSet<Character> can = new HashSet();
        can.add('{');
        for(char c : str2.toCharArray()){
            can.add(c);
        }
        
        
        HashSet<String> visited = new HashSet();
        Queue<String> q = new LinkedList();
        q.offer(str1);
        
        int len = str1.length();
        while(!q.isEmpty()){
            String cur = q.poll();
            boolean[] changed = new boolean[27];
            for(int i = 0; i < len; ++i){
                char c = cur.charAt(i);
                if(changed[c - 'a']) continue;
                for(char nc = 'a'; nc <= '{'; ++nc){
                    if(!can.contains(nc)){
                        continue;
                    }
                    String nStr = cur.replace(c, nc);
                    if(nStr.equals(str2)){
                        return true;
                    }
                    if(visited.add(nStr)){
                        q.offer(nStr);
                    }
                }
                changed[c - 'a'] = true;
            }
        }
        return false;
    }
}

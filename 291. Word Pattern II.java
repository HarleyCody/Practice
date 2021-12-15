//My Solution: BackTracking, match char in pattern and try to run the match to the end of s, return true immediately
//             one string match one char, record match string and record mapping
class Solution {
    public boolean wordPatternMatch(String pattern, String s) {
        return isValid(new String[26], pattern.toCharArray(), 0, s.toCharArray(), 0, new HashSet<String>());
    }
    
    private boolean isValid(String[] maps, char[] p, int pIdx, char[] s, int sIdx, Set<String> match){     
        if(pIdx == p.length) return sIdx == s.length;
        if(sIdx >= s.length) return false;
        int key = p[pIdx] - 'a';
        if(maps[key] != null){
            if(!canMove(maps[key].toCharArray(), s, sIdx)) return false;
            return isValid(maps, p, pIdx + 1, s, sIdx + maps[key].length(), match);
        }
        StringBuilder sb = new StringBuilder();
        for(int i = sIdx; i <= s.length - (p.length - pIdx); ++i){
            sb.append(s[i]);
            maps[key] = sb.toString();
            if(!match.add(maps[key])) continue;
            if(isValid(maps, p, pIdx + 1, s, i + 1, match)) return true;
            match.remove(maps[key]);
        }
        maps[key] = null;
        return false;
    }
    
    private boolean canMove(char[] tar, char[] src, int s){
        int len = tar.length;
        if(s + tar.length > src.length) return false;
        int tIdx = 0;
        int sIdx = s;
        while(tIdx < len){
            if(src[sIdx++] != tar[tIdx++]) return false;
        }
        return true;
    }
}

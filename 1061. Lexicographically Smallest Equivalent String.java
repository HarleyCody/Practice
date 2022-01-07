//My Solution: Union find, Union to smaller parent group
class Solution {
    char[] parent = new char[256];
    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        int len = s1.length();
        for(char i = 'a'; i <= 'z'; ++i){
            parent[i] = i;
        }
        for(int i = 0; i < len; ++i){
            union(s1.charAt(i), s2.charAt(i));
        }
        
        char[] ans = baseStr.toCharArray();
        for(int i = 0; i < ans.length; ++i){
            ans[i] = find(parent[ans[i]]);
        }
        
        return new String(ans);
    }
    
    private char find(char a){
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }
    
    private void union(char a, char b){
        char pa = find(a);
        char pb = find(b);
        if(pa < pb){
            parent[pb] = pa;
        }else{
            parent[pa] = pb;
        }
    }
}

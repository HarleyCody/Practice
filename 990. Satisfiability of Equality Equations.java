//My solution: union find
//Union equal first then check inequal equations
//Instead of calling union
//just update parent when finding parent for two varaible in equations, so donot need to make a new function to union
class Solution {
    int[] parent = new int[26];
    public boolean equationsPossible(String[] equations) {
        for(int i = 0; i < 26; ++i){
            parent[i] = i;
        }
        
        int a = 0;
        int b = 0;
        for(String eq : equations){
            if(eq.charAt(1) == '!') continue;
            a = find(eq.charAt(0) - 'a');
            b = find(eq.charAt(3) - 'a');
            parent[a] = b;
        }
        
        for(String eq : equations){
            if(eq.charAt(1) == '=') continue;
            a = eq.charAt(0) - 'a';
            b = eq.charAt(3) - 'a';
            if(find(a) == find(b)) return false;
        }
        return true;
    }
        
    private int find(int a){
        if(a == parent[a]) return a;
        int pa = find(parent[a]);
        return parent[a] = pa;
    }
}
